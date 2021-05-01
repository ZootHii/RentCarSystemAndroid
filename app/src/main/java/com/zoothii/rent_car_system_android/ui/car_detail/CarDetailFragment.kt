package com.zoothii.rent_car_system_android.ui.car_detail

/*import com.zoothii.rent_car_system_android.view_and_factory.car.CarViewModelFactory*/
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.zoothii.rent_car_system_android.R
import com.zoothii.rent_car_system_android.adapter.CarDetailAdapter
import com.zoothii.rent_car_system_android.databinding.FragmentCarDetailBinding
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.model.CarImage
import com.zoothii.rent_car_system_android.ui.notifications.NotificationsFragment
import com.zoothii.rent_car_system_android.util.Helper
import com.zoothii.rent_car_system_android.view_and_factory.car_image.CarImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator3
import java.util.*
import kotlin.collections.ArrayList

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
            carDetailRentButton.apply {
                text = "100"
                setOnClickListener {
                    val fragment = NotificationsFragment()
                    val bundle = Bundle()
                    bundle.putString("value", (Helper.data as CarDetail).description)
                    if (savedInstanceState == null) {
                        val ft: FragmentTransaction =
                            requireActivity().supportFragmentManager.beginTransaction()
                        ft.replace(R.id.car_detail_fragment_container, fragment)
                        ft.addToBackStack(null)
                        fragment.arguments = bundle
                        ft.commit()
                    }
                }
            }
/*            carDetailRentButton.setOnClickListener {
*//*                val intent = Intent(activity, CarDetailActivity::class.java)
                Helper.data = 1
                startActivity(intent)*//*



            }*/
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
        val dateTimePickerFragment = DateTimePickerFragment()
        var yeart: Int = 0
        var montht: Int = 0
        var dayt: Int = 0
        var hourt: Int = 0
        var minutet: Int = 0
        var rentSet = false
        val cal: Calendar = Calendar.getInstance()

        fragmentCarDetailBinding.carDetailReturnDateButton.isEnabled = false
        val onTime = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            if (rentSet){
                //fragmentCarDetailBinding.carDetailReturnDateButton.text = String.format("%d-%02d-%02dT%02d:%02d:00",yeart,montht,dayt,hourOfDay,minute)
                fragmentCarDetailBinding.carDetailReturnDateButton.text = String.format("%02d.%02d.%d/%02d:%02d",dayt,montht,yeart,hourOfDay,minute)
            }
            if (!rentSet){
                //fragmentCarDetailBinding.carDetailRentDateButton.text = String.format("%d-%02d-%02dT%02d:%02d:00",yeart,montht,dayt,hourOfDay,minute)
                fragmentCarDetailBinding.carDetailRentDateButton.text = String.format("%02d.%02d.%d/%02d:%02d",dayt,montht,yeart,hourOfDay,minute)
                fragmentCarDetailBinding.carDetailReturnDateButton.isEnabled = true

                Helper.date.add(yeart)
                Helper.date.add(montht)
                Helper.date.add(dayt)
                rentSet = true
            }
        }

        val onDate = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
/*            cal.set(year,month,dayOfMonth)
            view.minDate = cal.timeInMillis*/
            yeart = year
            montht = month
            dayt = dayOfMonth
            dateTimePickerFragment.setTimeCallBack(onTime)
        }

        fragmentCarDetailBinding.carDetailRentDateButton.apply {

            dateTimePickerFragment.getDateTimeCalender()
            //text = dateTimePickerFragment.savedDateTime
            setOnClickListener {
                //dateTimePickerFragment.pickDateTime()
                dateTimePickerFragment.setDateCallBack(onDate)
                dateTimePickerFragment.showNow(childFragmentManager, "DateTimePickerForRent")
            }
        }

        fragmentCarDetailBinding.carDetailReturnDateButton.apply {

            dateTimePickerFragment.getDateTimeCalender()
            //text = dateTimePickerFragment.savedDateTime
            setOnClickListener {
                //dateTimePickerFragment.pickDateTime()
                dateTimePickerFragment.setDateCallBack(onDate)
                //dateTimePickerFragment.
                dateTimePickerFragment.showNow(childFragmentManager, "DateTimePickerForReturn")
            }
        }

    }
}