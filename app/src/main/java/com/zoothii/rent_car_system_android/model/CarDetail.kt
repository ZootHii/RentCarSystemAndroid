package com.zoothii.rent_car_system_android.model

import android.os.Parcelable
import com.zoothii.rent_car_system_android.util.Helper
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarDetail(
    val id: Int,
    val brandId: Int,
    val colorId: Int,
    val modelYear: String,
    val dailyPrice: Double,
    val description: String,
    val brandName: String,
    val colorName: String,
    val previewFirstImage: String, // PreviewFirstImage
): Parcelable{
    val modelYearFormatted: String
        get() = Helper.formatDateTimeString(modelYear, "yyyy")
}
