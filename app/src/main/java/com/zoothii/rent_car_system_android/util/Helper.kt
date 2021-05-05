package com.zoothii.rent_car_system_android.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Suppress("UNCHECKED_CAST")
class Helper {

    companion object {

        //@Inject lateinit var applicationContext2: ApplicationContext // TODO Execution failed for task ':app:kaptDebugKotlin'
        lateinit var applicationContext: Context

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
                //Log.d("Context", applicationContext2.toString())

                showToastMessage(applicationContext, message, true)
                Log.d(jsonError.getString("Type"), message.toString())
            } else {
                val message = jsonError.getString("message")
                //Log.d("Context", applicationContext2.toString())

                showToastMessage(applicationContext, message, true)
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

        fun showToastMessage(context: Context, message: String, isLong: Boolean) {
            val duration: Int = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
            Toast.makeText(context, message, duration).show()
        }
    }
}
