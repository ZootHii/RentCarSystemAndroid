package com.zoothii.rent_car_system_android.ui.car_detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.zoothii.rent_car_system_android.R
import com.zoothii.rent_car_system_android.adapter.CarDetailAdapter
import com.zoothii.rent_car_system_android.adapter.CarsDetailAdapter
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.model.CarImage
import com.zoothii.rent_car_system_android.repository.CarImageRepository
import com.zoothii.rent_car_system_android.repository.CarRepository
import com.zoothii.rent_car_system_android.util.Helper
import com.zoothii.rent_car_system_android.view_and_factory.car.CarViewModel
/*import com.zoothii.rent_car_system_android.view_and_factory.car.CarViewModelFactory*/
import com.zoothii.rent_car_system_android.view_and_factory.car_image.CarImageViewModel
import com.zoothii.rent_car_system_android.view_and_factory.car_image.CarImageViewModelFactory
import me.relex.circleindicator.CircleIndicator3

class CarDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CarDetailFragment()
    }

    private lateinit var carImageViewModel: CarImageViewModel
    private lateinit var carImagesList: List<CarImage>
    private lateinit var carDetailAdapter: CarDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_car_detail, container, false)

        val descriptionText: TextView = root.findViewById(R.id.car_detail_description)
        val colorNameText: TextView = root.findViewById(R.id.car_detail_color_name)
        val modelYearText: TextView = root.findViewById(R.id.car_detail_model_year)
        val dailyPriceText: TextView = root.findViewById(R.id.car_detail_daily_price)

        val carImageRepository = CarImageRepository()
        val carImageViewModelFactory = CarImageViewModelFactory(carImageRepository)
        carImageViewModel = ViewModelProvider(this, carImageViewModelFactory).get(CarImageViewModel::class.java)

/*        recyclerView.adapter = carCardAdapter
        recyclerView.layoutManager = LinearLayoutManager(root.context)*/


        if (Helper.data is CarDetail) {
            val carDetail = Helper.data as CarDetail
            descriptionText.text = carDetail.description
            colorNameText.text = carDetail.colorName
            modelYearText.text = carDetail.modelYearFormatted
            dailyPriceText.text = "\$ ${carDetail.dailyPrice} \nDaily"


            carImageViewModel.getCarImagesByCarId(carDetail.id).observe(
                viewLifecycleOwner,
                { responseCarDetailData ->
                    if (responseCarDetailData.success) {
                        carImagesList = responseCarDetailData.data.toCollection(ArrayList())

                        carImagesList.forEach { carImage ->
                            Log.d("CAR IMAGE TESTING ", "carId: " + carImage.carId + "imageId: " + carImage.id)
                        }

                        carDetailAdapter = CarDetailAdapter(root.context) {

                        }

                        val viewPager2 = root.findViewById<ViewPager2>(R.id.view_pager_2)
                        viewPager2.adapter = carDetailAdapter
                        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

                        val circleIndicator = root.findViewById<CircleIndicator3>(R.id.circle_indicator)

                        carDetailAdapter.setCarDetails(carImagesList)
                        circleIndicator.setViewPager(viewPager2)
                        /*imageView1.setImageBitmap(Helper.base64StringToBitmap(carImagesList[0].imagePath))
                        imageView2.setImageBitmap(Helper.base64StringToBitmap(carImagesList[1].imagePath))*/

                    } else {
                        Log.d("Message", responseCarDetailData.message.toString())
                        Log.d("Success", responseCarDetailData.success.toString())
                    }
                })
        }

        return root
    }

}