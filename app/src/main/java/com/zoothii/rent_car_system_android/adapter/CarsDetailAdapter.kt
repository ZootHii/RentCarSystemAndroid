package com.zoothii.rent_car_system_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.zoothii.rent_car_system_android.databinding.CarDetailCardViewItemBinding
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.util.Helper
import java.util.*
import kotlin.collections.ArrayList

class CarsDetailAdapter(
    @NonNull private val context: Context,
    @Nullable private val clickListener: ((CarDetail, ArrayList<CarDetail>) -> Unit)? = null
) : RecyclerView.Adapter<CarsDetailAdapter.CarsDetailViewHolder>(), Filterable {

    private val carsDetailList: ArrayList<CarDetail> = ArrayList()
    private val carsDetailListFull: ArrayList<CarDetail> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsDetailViewHolder {
        val carDetailCardViewItemBinding = CarDetailCardViewItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return CarsDetailViewHolder(carDetailCardViewItemBinding)
    }

    override fun onBindViewHolder(holder: CarsDetailViewHolder, position: Int) {
        val currentCarDetailItem: CarDetail = carsDetailList[position]
        holder.bindItems(currentCarDetailItem)
        if (clickListener != null) {
            holder.itemView.setOnClickListener {
                clickListener.invoke(
                    currentCarDetailItem,
                    carsDetailList
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return carsDetailList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredCarDetailList: ArrayList<CarDetail> = ArrayList()

                if (constraint.isNullOrEmpty()) {
                    filteredCarDetailList.addAll(carsDetailListFull)
                } else {
                    val filterPattern: String = constraint.toString().toLowerCase(Locale.ROOT)
                        .trim()

                    carsDetailListFull.forEach { carDetail ->
                        if (carDetail.description.toLowerCase(Locale.ROOT).contains(filterPattern)
                            || carDetail.brandName.toLowerCase(Locale.ROOT).contains(filterPattern)
                            || carDetail.colorName.toLowerCase(Locale.ROOT).contains(filterPattern)
                            || carDetail.dailyPrice.toString().contains(filterPattern)
                            || carDetail.modelYearFormatted.contains(filterPattern)
                        ) {
                            filteredCarDetailList.add(carDetail)
                        }
                    }

                }
                return FilterResults().apply {
                    values = filteredCarDetailList
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                carsDetailList.clear()
                val filteredCarDetailList: ArrayList<CarDetail> =
                    results?.values as ArrayList<CarDetail>
                setCarDetails(filteredCarDetailList)
            }

        }
    }

    fun setCarDetails(carDetailList: ArrayList<CarDetail>) {
        this.carsDetailList.clear()
        this.carsDetailList.addAll(carDetailList)
        if (carsDetailListFull.isEmpty()) {
            carsDetailListFull.addAll(carDetailList)
        }
        this.notifyDataSetChanged()
    }

    inner class CarsDetailViewHolder(
        private val carDetailCardViewItemBinding: CarDetailCardViewItemBinding,
    ) : RecyclerView.ViewHolder(
        carDetailCardViewItemBinding.root
    ) {

        fun bindItems(currentCarDetailItem: CarDetail) {
            carDetailCardViewItemBinding.carCardBrandName.text = currentCarDetailItem.brandName
            carDetailCardViewItemBinding.carCardColorName.text = currentCarDetailItem.colorName
            carDetailCardViewItemBinding.carCardModelYear.text =
                currentCarDetailItem.modelYearFormatted
            carDetailCardViewItemBinding.carCardDescription.text = currentCarDetailItem.description
            carDetailCardViewItemBinding.carCardDailyPrice.text =
                "\$ ${currentCarDetailItem.dailyPrice} \nDaily"
            carDetailCardViewItemBinding.carCardImage.setImageBitmap(
                Helper.base64StringToBitmap(
                    currentCarDetailItem.previewFirstImage
                )
            )
        }
    }
}
