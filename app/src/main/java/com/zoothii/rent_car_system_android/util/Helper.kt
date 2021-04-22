package com.zoothii.rent_car_system_android.util


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

import android.util.Log
import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import com.zoothii.rent_car_system_android.R
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.reflect.KClass


@Suppress("UNCHECKED_CAST")
class Helper {
    //private lateinit var progressBar: ContentLoadingProgressBar

    companion object {


        var data: Any = Any()


        fun formatDateTimeString(dateTime: String, pattern: String): String {
            val localDateTime = LocalDateTime.parse(dateTime)
            val formatter = DateTimeFormatter.ofPattern(pattern)
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

        fun progressBarShow(view: View, show: Boolean){
            val progressBar = view.findViewById<ContentLoadingProgressBar>(R.id.progress_bar)
            if (show){
                progressBar.show()
            }
            if (!show){
                progressBar.hide()
            }
        }

        fun base64StringToBitmap(base64String: String): Bitmap {
            /*if (base64String.isEmpty() || base64String.isBlank()){



                val imageBytes = Base64.decode(defaultBase64String.toByteArray(), Base64.DEFAULT)
                return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            }*/
            val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }
        //val defaultBase64String = "            \"iVBORw0KGgoAAAANSUhEUgAAAFgAAABSCAYAAADQDhNSAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAADASURBVHhe7dAxAQAwDAShSnn/JlMVtzFggLft6AiOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JjgmOCY4JTu0+5EuoCxuSR1YAAAAASUVORK5CYII=\"\n"
    }

}
