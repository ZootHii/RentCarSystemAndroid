package com.zoothii.rent_car_system_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.zoothii.rent_car_system_android.databinding.CarDetailCardViewItemBinding
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.util.Helper


class CarsDetailAdapter(
    @NonNull private val context: Context, // I am not sure why should use this instead of parent ViewGroup
    private val clickListener: (CarDetail) -> Unit
) : RecyclerView.Adapter<CarsDetailAdapter.CarsDetailViewHolder>() {

    private var carDetailList: List<CarDetail> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsDetailViewHolder {
        val carDetailCardViewItemBinding = CarDetailCardViewItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return CarsDetailViewHolder(carDetailCardViewItemBinding)

    }

    override fun onBindViewHolder(holder: CarsDetailViewHolder, position: Int) {
        val currentCarDetailItem: CarDetail = carDetailList[position]
        holder.bindItems(currentCarDetailItem)
        holder.itemView.setOnClickListener { clickListener(currentCarDetailItem) }
    }

    override fun getItemCount(): Int {
        return carDetailList.size
    }

    fun setCarDetails(carDetailList: List<CarDetail>) {
        this.carDetailList = carDetailList
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
            carDetailCardViewItemBinding.carCardModelYear.text = Helper.formatDateTimeString(
                currentCarDetailItem.modelYear,
                "yyyy"
            )
            carDetailCardViewItemBinding.carCardDescription.text = currentCarDetailItem.description
            carDetailCardViewItemBinding.carCardDailyPrice.text = "\$ ${currentCarDetailItem.dailyPrice} \nDaily"
            carDetailCardViewItemBinding.carCardImage.setImageBitmap(Helper.base64StringToBitmap(currentCarDetailItem.previewFirstImage))
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
