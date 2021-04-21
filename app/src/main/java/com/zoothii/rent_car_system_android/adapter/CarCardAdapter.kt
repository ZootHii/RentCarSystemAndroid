package com.zoothii.rent_car_system_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.zoothii.rent_car_system_android.databinding.CardviewCarItemBinding
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.util.Helper


class CarCardAdapter(
    @NonNull context: Context,
    private val listener: (CarDetail) -> Unit
/*private val carDetailList: List<CarDetail>*/ /*, private val carImageList: List<CarImage>*/
) : RecyclerView.Adapter<CarCardAdapter.CarCardViewHolder>() {

    /*var onItemClick: ((CarDetail) -> Unit)? = null*/
    private var carDetailList: List<CarDetail> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarCardViewHolder {
        val itemBinding = CardviewCarItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CarCardViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: CarCardViewHolder, position: Int) {
        val currentCarDetailItem: CarDetail = carDetailList[position]
        /*val currentCarImageItem: CarImage = carImageList[position]*/
        holder.bind(currentCarDetailItem/*, currentCarImageItem*/)
        holder.itemView.setOnClickListener { listener(currentCarDetailItem) }
    }

    override fun getItemCount(): Int {
        // image and car size equal
        return carDetailList.size
    }

    fun setCarDetails(carDetailList: List<CarDetail>) {
        this.carDetailList = carDetailList
        this.notifyDataSetChanged()
    }

    inner class CarCardViewHolder(
        private val itemBinding: CardviewCarItemBinding,
        /*private val carDetailItem: View?*/
    ) : RecyclerView.ViewHolder(
        itemBinding.root
    ) {


        fun bind(currentCarDetailItem: CarDetail/*, currentCarImageItem: CarImage*/) {
            itemBinding.carCardBrandName.text = currentCarDetailItem.brandName
            itemBinding.carCardColorName.text = currentCarDetailItem.colorName
            itemBinding.carCardModelYear.text = Helper.dateTimeStringFormat(
                currentCarDetailItem.modelYear,
                "yyyy"
            )
            itemBinding.carCardDescription.text = currentCarDetailItem.description
            itemBinding.carCardDailyPrice.text = "\$ ${currentCarDetailItem.dailyPrice} \nDaily"
            itemBinding.carCardImage.setImageBitmap(Helper.base64StringToBitmap(currentCarDetailItem.previewFirstImage))
            /*carDetailItem?.setOnClickListener {
                listener.invoke(currentCarDetailItem)
            }
            */
/*            itemBinding.root.setOnClickListener {
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