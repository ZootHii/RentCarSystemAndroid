package com.zoothii.rent_car_system_android.model.response_model

class DataResponseModel<T>(success: Boolean, message: Boolean, val data: Array<T>) :
    ResponseModel(success, message)