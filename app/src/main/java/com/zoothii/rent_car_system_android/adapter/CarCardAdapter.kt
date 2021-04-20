package com.zoothii.rent_car_system_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zoothii.rent_car_system_android.databinding.CardviewCarItemBinding
import com.zoothii.rent_car_system_android.model.Car


class CarCardAdapter(private val carList: List<Car>): RecyclerView.Adapter<CarCardAdapter.CarCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarCardViewHolder {
        val itemBinding = CardviewCarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarCardViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: CarCardViewHolder, position: Int) {
        val currentCarItem: Car = carList[position]
        holder.bind(currentCarItem)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    class CarCardViewHolder(private val itemBinding: CardviewCarItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(currentCarItem: Car){
            itemBinding.carCardBrandName.text = currentCarItem.brandId.toString()
            itemBinding.carCardColorName.text = currentCarItem.colorId.toString()
            itemBinding.carCardModelYear.text = currentCarItem.modelYear
            itemBinding.carCardDescription.text = currentCarItem.description
            itemBinding.carCardDailyPrice.text = currentCarItem.dailyPrice.toString()
        }
    }


}

/*    class PaymentAdapter(private val paymentList: List<PaymentBean>) : RecyclerView.Adapter<PaymentAdapter.PaymentHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
            val itemBinding = RowPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PaymentHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
            val paymentBean: PaymentBean = paymentList[position]
            holder.bind(paymentBean)
        }

        override fun getItemCount(): Int = paymentList.size

        class PaymentHolder(private val itemBinding: RowPaymentBinding) : RecyclerView.ViewHolder(itemBinding.root) {
            fun bind(paymentBean: PaymentBean) {
                itemBinding.tvPaymentInvoiceNumber.text = paymentBean.invoiceNumber
                itemBinding.tvPaymentAmount.text = paymentBean.totalAmount
            }
        }
    }
*/