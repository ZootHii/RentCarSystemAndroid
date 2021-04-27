package com.zoothii.rent_car_system_android.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.core.widget.ContentLoadingProgressBar
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Suppress("UNCHECKED_CAST")
class Helper {

    companion object {

        var data: Any = Any()

        fun formatDateTimeString(dateTime: String, dateTimeFormatterPattern: String): String {
            val localDateTime = LocalDateTime.parse(dateTime)
            val formatter = DateTimeFormatter.ofPattern(dateTimeFormatterPattern)
            return formatter.format(localDateTime)
        }

        fun handleError(errorBody: String) {
            val jsonError = JSONObject(errorBody)
            if (!jsonError.isNull("Type")) {
                val message = jsonError.getString("Message")
                Log.d(jsonError.getString("Type"), message.toString())
            } else {
                val message = jsonError.getString("message")
                Log.d("Error", message.toString())
            }
        }

        fun progressBarShow(progressBar: ContentLoadingProgressBar, show: Boolean) {
            if (show) {
                progressBar.show()
            }
            if (!show) {
                progressBar.hide()
            }
        }

        fun base64StringToBitmap(base64String: String): Bitmap {
            val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }
    }
}
