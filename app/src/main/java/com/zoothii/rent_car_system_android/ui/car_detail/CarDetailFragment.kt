package com.zoothii.rent_car_system_android.ui.car_detail

/*import com.zoothii.rent_car_system_android.view_and_factory.car.CarViewModelFactory*/
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.zoothii.rent_car_system_android.adapter.CarDetailAdapter
import com.zoothii.rent_car_system_android.databinding.FragmentCarDetailBinding
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.model.CarImage
import com.zoothii.rent_car_system_android.util.Helper
import com.zoothii.rent_car_system_android.view_and_factory.car_image.CarImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarDetailFragment : Fragment() {

    private val carImageViewModel: CarImageViewModel by viewModels()
    private lateinit var carImagesList: List<CarImage>
    private lateinit var carDetailAdapter: CarDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val fragmentCarDetailBinding = FragmentCarDetailBinding.inflate(
            inflater,
            container,
            false
        )
        carDetailAdapter = CarDetailAdapter(fragmentCarDetailBinding.root.context)
        val viewPager2 = fragmentCarDetailBinding.viewPager2
        val circleIndicator = fragmentCarDetailBinding.circleIndicator

        if (Helper.data is CarDetail) {
            val carDetail = Helper.data as CarDetail

            fragmentCarDetailBinding.carDetailDescription.text = carDetail.description
            fragmentCarDetailBinding.carDetailColorName.text = carDetail.colorName
            fragmentCarDetailBinding.carDetailModelYear.text = carDetail.modelYearFormatted
            fragmentCarDetailBinding.carDetailDailyPrice.text = "\$ ${carDetail.dailyPrice} \nDaily"

            carImageViewModel.getCarImagesByCarId(carDetail.id).observe(
                viewLifecycleOwner,
                { responseCarDetailData ->
                    if (responseCarDetailData.success) {

                        carImagesList = responseCarDetailData.data.toCollection(ArrayList())
                        viewPager2.adapter = carDetailAdapter
                        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                        carDetailAdapter.setCarDetails(carImagesList)
                        circleIndicator.setViewPager(viewPager2)

                    } else {
                        Log.d("Message", responseCarDetailData.message.toString())
                        Log.d("Success", responseCarDetailData.success.toString())
                    }
                })
        }

        return fragmentCarDetailBinding.root
    }
}