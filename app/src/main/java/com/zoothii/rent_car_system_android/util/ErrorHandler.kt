package com.zoothii.rent_car_system_android.util

import android.util.Log
import org.json.JSONObject

class ErrorHandler {

    companion object {
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
    }
}