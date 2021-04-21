package com.zoothii.rent_car_system_android.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.zoothii.rent_car_system_android.model.Brand
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.model.response.SingleDataResponseModel
import com.zoothii.rent_car_system_android.remote.RetrofitService
import com.zoothii.rent_car_system_android.remote.service.IBrandsService
import com.zoothii.rent_car_system_android.util.ErrorHandler
import com.zoothii.rent_car_system_android.util.Helper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BrandRepository {

    companion object Factory {
        fun create(): BrandRepository = BrandRepository()
    }

    private val brandService: IBrandsService by lazy {
        RetrofitService.buildService(IBrandsService::class.java)
    }

    val brandDataResponse: MutableLiveData<DataResponseModel<Brand>> = MutableLiveData()
    val brandSingleDataResponse: MutableLiveData<SingleDataResponseModel<Brand>> = MutableLiveData()
    val brandResponse: MutableLiveData<ResponseModel> = MutableLiveData()

    fun getAllBrands(): MutableLiveData<DataResponseModel<Brand>> {
        brandService.getAllBrands().enqueue(object : Callback<DataResponseModel<Brand>> {
            override fun onResponse(
                call: Call<DataResponseModel<Brand>>,
                response: Response<DataResponseModel<Brand>>
            ) {
                if (response.isSuccessful) {
                    brandDataResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<DataResponseModel<Brand>>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return brandDataResponse
    }

    fun getBrandById(id: Int): MutableLiveData<SingleDataResponseModel<Brand>> {
        brandService.getBrandById(id).enqueue(object : Callback<SingleDataResponseModel<Brand>> {
            override fun onResponse(
                call: Call<SingleDataResponseModel<Brand>>,
                response: Response<SingleDataResponseModel<Brand>>
            ) {
                if (response.isSuccessful) {
                    brandSingleDataResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<SingleDataResponseModel<Brand>>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return brandSingleDataResponse
    }

    fun addBrand(brand: Brand): MutableLiveData<ResponseModel> {
        brandService.addBrand(brand).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {
                    brandResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return brandResponse
    }

    fun updateBrand(brand: Brand): MutableLiveData<ResponseModel> {
        brandService.updateBrand(brand).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {
                    brandResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return brandResponse
    }

    fun deleteBrand(brand: Brand): MutableLiveData<ResponseModel> {
        brandService.deleteBrand(brand).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {
                    brandResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return brandResponse
    }
}