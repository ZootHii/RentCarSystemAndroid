package com.zoothii.rent_car_system_android.ui.car_detail

/*import com.zoothii.rent_car_system_android.view_and_factory.car.CarViewModelFactory*/
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.zoothii.rent_car_system_android.R
import com.zoothii.rent_car_system_android.adapter.CarDetailAdapter
import com.zoothii.rent_car_system_android.databinding.FragmentCarDetailBinding
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.model.CarImage
import com.zoothii.rent_car_system_android.util.Helper
import com.zoothii.rent_car_system_android.view_and_factory.car_image.CarImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator3

@AndroidEntryPoint
class CarDetailFragment : Fragment(R.layout.fragment_car_detail) {

    private val carImageViewModel: CarImageViewModel by viewModels()
    private lateinit var carImagesList: List<CarImage>
    private lateinit var carDetailAdapter: CarDetailAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var circleIndicator3: CircleIndicator3

    /*override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
*/
/*        val fragmentCarDetailBinding = FragmentCarDetailBinding.inflate(
            inflater,
            container,
            false
        )*/

    /*carDetailAdapter = CarDetailAdapter(fragmentCarDetailBinding.root.context)
    fragmentCarDetailBinding.apply {
        viewPager2 = viewPager2CarDetail
        circleIndicator3 = circleIndicator3CarDetail
    }
    viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    viewPager2.adapter = carDetailAdapter

    if (Helper.data is CarDetail) {
        val carDetail = Helper.data as CarDetail

        fragmentCarDetailBinding.apply {
            carDetailDescription.text = carDetail.description
            carDetailColorName.text = carDetail.colorName
            carDetailModelYear.text = carDetail.modelYearFormatted
            carDetailDailyPrice.text = "\$ ${carDetail.dailyPrice} \nDaily"
        }

        carImageViewModel.getCarImagesByCarId(carDetail.id).observe(
            viewLifecycleOwner,
            { responseCarDetailData ->
                if (responseCarDetailData.success) {

                    carImagesList = responseCarDetailData.data.toCollection(ArrayList())
                    carDetailAdapter.setCarDetails(carImagesList)
                    circleIndicator3.setViewPager(viewPager2)

                } else {
                    Log.d("Message", responseCarDetailData.message.toString())
                    Log.d("Success", responseCarDetailData.success.toString())
                }
            })
    }

    return fragmentCarDetailBinding.root*/
    /*}*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentCarDetailBinding = FragmentCarDetailBinding.bind(view)

        fragmentCarDetailBinding.apply {
            carDetailAdapter = CarDetailAdapter(requireContext())
            viewPager2 = viewPager2CarDetail.apply {
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                adapter = carDetailAdapter
            }
            circleIndicator3 = circleIndicator3CarDetail

        }

        if (Helper.data is CarDetail) {
            val carDetail = Helper.data as CarDetail

            fragmentCarDetailBinding.apply {
                carDetailDescription.text = carDetail.description
                carDetailColorName.text = carDetail.colorName
                carDetailModelYear.text = carDetail.modelYearFormatted
                carDetailDailyPrice.text = "\$ ${carDetail.dailyPrice} \nDaily"
            }

            carImageViewModel.getCarImagesByCarId(carDetail.id).observe(
                viewLifecycleOwner
            ) { responseCarDetailData ->
                if (responseCarDetailData.success) {

                    carImagesList = responseCarDetailData.data.toCollection(ArrayList())
                    carDetailAdapter.setCarDetails(carImagesList)
                    circleIndicator3.setViewPager(viewPager2)

                } else {
                    Log.d("Message", responseCarDetailData.message.toString())
                    Log.d("Success", responseCarDetailData.success.toString())
                }
            }
        }
    }
}