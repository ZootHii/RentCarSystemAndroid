package com.zoothii.rent_car_system_android.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.zoothii.rent_car_system_android.databinding.CarDetailCardViewItemBinding
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.util.Helper

class CarsDetailAdapter(
    @NonNull private val context: Context, // I am not sure why should use this instead of parent ViewGroup
    private val clickListener: (CarDetail) -> Unit
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
        holder.itemView.setOnClickListener { clickListener(currentCarDetailItem) }
    }

    override fun getItemCount(): Int {
        return carsDetailList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            val filteredCarDetailList: ArrayList<CarDetail> = ArrayList()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.isNullOrEmpty()) {
                    filteredCarDetailList.addAll(carsDetailListFull)
                } else {
                    val filterPattern: String = constraint.toString().toLowerCase().trim()

                    carsDetailListFull.forEach { carDetail ->
                        when {
                            carDetail.description.toLowerCase().contains(filterPattern) -> {
                                filteredCarDetailList.add(carDetail)
                            }
                            carDetail.brandName.toLowerCase().contains(filterPattern) -> {
                                filteredCarDetailList.add(carDetail)
                            }
                            carDetail.colorName.toLowerCase().contains(filterPattern) -> {
                                filteredCarDetailList.add(carDetail)
                            }
                            carDetail.dailyPrice.toString().contains(filterPattern) -> {
                                filteredCarDetailList.add(carDetail)
                            }
                            carDetail.modelYearFormatted.contains(filterPattern) -> {
                                filteredCarDetailList.add(carDetail)
                            }
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
                val list: ArrayList<CarDetail> = results?.values as ArrayList<CarDetail>
                carsDetailList.addAll(list)

                notifyDataSetChanged()
            }

        }
    }

    fun setCarDetails(carDetailList: ArrayList<CarDetail>) {
        this.carsDetailList.addAll(carDetailList)
        carsDetailListFull.addAll(carDetailList) // todo carDetailListFull = carDetailList does not work IDK why AAJHHHH
        Log.d("Search ALL", carDetailList.size.toString())
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
            /*carDetailItem?.setOnClickListener {
                listener.invoke(currentCarDetailItem)
            }
            */
            /*itemBinding.root.setOnClickListener {
                listener.invoke(currentCarDetailItem)
            }*/

        }


        /*init {
            itemBinding.root.setOnClickListener {
                onItemClick?.invoke(carDetailList[bindingAdapterPosition])
            }
        }*/

    }
}
