package com.zoothii.rent_car_system_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.zoothii.rent_car_system_android.databinding.CarDetailViewItemBinding
import com.zoothii.rent_car_system_android.model.CarImage
import com.zoothii.rent_car_system_android.util.Helper

class CarDetailAdapter(
    @NonNull private val context: Context,
    @Nullable private val clickListener: ((CarImage) -> Unit)? = null
) : RecyclerView.Adapter<CarDetailAdapter.CarDetailViewHolder>() {

    private var carImagesList: List<CarImage> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarDetailViewHolder {
        val carDetailViewItemBinding = CarDetailViewItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return CarDetailViewHolder(carDetailViewItemBinding)
    }

    override fun onBindViewHolder(holder: CarDetailViewHolder, position: Int) {
        val currentCarDetailItem: CarImage = carImagesList[position]
        holder.bindItems(currentCarDetailItem)
        if (clickListener != null) {
            holder.itemView.setOnClickListener { clickListener.invoke(currentCarDetailItem) }
        }
    }

    override fun getItemCount(): Int {
        return carImagesList.size
    }

    fun setCarDetails(carDetailList: List<CarImage>) {
        this.carImagesList = carDetailList
        this.notifyDataSetChanged()
    }

    inner class CarDetailViewHolder(
        private val carDetailViewItemBinding: CarDetailViewItemBinding,
    ) : RecyclerView.ViewHolder(
        carDetailViewItemBinding.root
    ) {

        fun bindItems(currentCarDetailItem: CarImage) {
            carDetailViewItemBinding.carDetailImage.setImageBitmap(
                Helper.base64StringToBitmap(
                    currentCarDetailItem.imagePath
                )
            )
        }
    }
}