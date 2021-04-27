package com.zoothii.rent_car_system_android.adapter

import android.content.Context
import android.util.Log
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
    private var sortFlag = true

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
        Log.d("SORT", "TEKRAR AMK")

        this.carsDetailList.clear()
        this.carsDetailList.addAll(carDetailList)
        if (carsDetailListFull.isEmpty()) {
            carsDetailListFull.addAll(carDetailList)
        }
        this.notifyDataSetChanged()
    }

    fun sortCarsBy(string: String){
        if (sortFlag && string == Helper.SORT_BY_DAILY_PRICE){
            carsDetailList.sortBy { carDetail -> carDetail.dailyPrice }
            sortFlag = false
        }
        else if (!sortFlag && string == Helper.SORT_BY_DAILY_PRICE){
            carsDetailList.sortByDescending { carDetail -> carDetail.dailyPrice }
            sortFlag = true
        }
        else if (sortFlag && string == Helper.SORT_BY_MODEL_YEAR){
            carsDetailList.sortBy { carDetail -> carDetail.modelYear }
            sortFlag = false
        }
        else if (!sortFlag && string == Helper.SORT_BY_MODEL_YEAR){
            carsDetailList.sortByDescending { carDetail -> carDetail.modelYear }
            sortFlag = true
        }
        notifyDataSetChanged()
    }

    inner class CarsDetailViewHolder(
        private val itemBinding: CarDetailCardViewItemBinding,
    ) : RecyclerView.ViewHolder(
        itemBinding.root
    ) {

        fun bindItems(currentCarDetailItem: CarDetail) {
            itemBinding.apply {
                carCardBrandName.text = currentCarDetailItem.brandName
                carCardColorName.text = currentCarDetailItem.colorName
                carCardModelYear.text = currentCarDetailItem.modelYearFormatted
                carCardDescription.text = currentCarDetailItem.description
                carCardDailyPrice.text = "\$ ${currentCarDetailItem.dailyPrice} \nDaily"
                carCardImage.setImageBitmap(
                    Helper.base64StringToBitmap(currentCarDetailItem.previewFirstImage)
                )
            }
        }
    }
}
