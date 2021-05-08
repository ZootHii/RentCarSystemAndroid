package com.zoothii.rent_car_system_android.model.response_model

class SingleDataResponseModel<T>(success: Boolean, message: Boolean, val data: T) :
    ResponseModel(success, message)