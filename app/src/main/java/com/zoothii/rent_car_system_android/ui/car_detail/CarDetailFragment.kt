package com.zoothii.rent_car_system_android.ui.car_detail

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
import com.zoothii.rent_car_system_android.model.Rental
import com.zoothii.rent_car_system_android.ui.notifications.NotificationsFragment
import com.zoothii.rent_car_system_android.util.Helper
import com.zoothii.rent_car_system_android.view_and_factory.RentalViewModel
import com.zoothii.rent_car_system_android.view_and_factory.car_image.CarImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator3
import java.time.LocalDateTime
import kotlin.math.ceil

@AndroidEntryPoint
class CarDetailFragment : Fragment(R.layout.fragment_car_detail) {

    private val carImageViewModel: CarImageViewModel by viewModels()
    private val rentalViewModel: RentalViewModel by viewModels()
    private lateinit var carImagesList: List<CarImage>
    private lateinit var carDetailAdapter: CarDetailAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var circleIndicator3: CircleIndicator3
    private lateinit var fragmentCarDetailBinding: FragmentCarDetailBinding
    private val rentDateTimePickerFragment = RentDateTimePickerFragment()
    private val returnDateTimePickerFragment = ReturnDateTimePickerFragment()
    private var yearValue: Int = 0
    private var monthValue: Int = 0
    private var dayValue: Int = 0
    private var isRentDateTimeSet = false
    private var rentLocalDateTime: LocalDateTime = LocalDateTime.MAX
    private var returnLocalDateTime: LocalDateTime = LocalDateTime.MAX
    private var returnJsonTimeString: String = ""
    private var rentJsonTimeString: String = ""
    private var hourDiff: Double? = null
    private var dayDiffCeil: Double? = null
    private var minutesDiff: Double? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentCarDetailBinding = FragmentCarDetailBinding.bind(view)

        fragmentCarDetailBinding.apply {
            carDetailAdapter = CarDetailAdapter(requireContext())
            viewPager2 = viewPager2CarDetail.apply {
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                adapter = carDetailAdapter
            }
            circleIndicator3 = circleIndicator3CarDetail
            carDetailRentButton.apply {
                if (Helper.data is CarDetail) {
                    val carDetail = Helper.data as CarDetail
                    text = "Rent The ${carDetail.brandName}"
                }
            }
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
        setDateTimeSetListenerButtons()
    }

    private fun setDateTimeSetListenerButtons() {
        fragmentCarDetailBinding.carDetailRentDateButton.apply {
            setOnClickListener {
                rentDateTimePickerFragment.setDateCallBack(onDateSetListenerForRent())
                rentDateTimePickerFragment.showNow(childFragmentManager, "DateTimePickerForRent")
            }
        }

        fragmentCarDetailBinding.carDetailReturnDateButton.apply {
            setOnClickListener {
                returnDateTimePickerFragment.setDateCallBack(onDateSetListenerForReturn())
                returnDateTimePickerFragment.showNow(
                    childFragmentManager,
                    "DateTimePickerForReturn"
                )
            }
        }
    }

    private fun onDateSetListenerForReturn(): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            yearValue = year
            monthValue = month + 1
            dayValue = dayOfMonth
            returnDateTimePickerFragment.setTimeCallBack(onTimeSetListenerForReturn())
        }
    }

    private fun onTimeSetListenerForReturn(): TimePickerDialog.OnTimeSetListener {
        return TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            returnJsonTimeString =
                String.format(
                    "%d-%02d-%02dT%02d:%02d:00",
                    yearValue,
                    monthValue,
                    dayValue,
                    hourOfDay,
                    minute
                )
            returnLocalDateTime = LocalDateTime.parse(returnJsonTimeString)
            fragmentCarDetailBinding.carDetailReturnDateButton.text =
                String.format(
                    "%02d.%02d.%d/%02d:%02d",
                    dayValue,
                    monthValue,
                    yearValue,
                    hourOfDay,
                    minute
                )
            calculateDayDifference()
            setRentButton()
        }
    }

    private fun onDateSetListenerForRent(): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            yearValue = year
            monthValue = month + 1
            dayValue = dayOfMonth
            rentDateTimePickerFragment.setTimeCallBack(onTimeSetListenerForRent())
        }
    }

    private fun onTimeSetListenerForRent(): TimePickerDialog.OnTimeSetListener {
        return TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            rentJsonTimeString = String.format(
                "%d-%02d-%02dT%02d:%02d:00",
                yearValue,
                monthValue,
                dayValue, hourOfDay, minute
            )
            rentLocalDateTime = LocalDateTime.parse(rentJsonTimeString)
            fragmentCarDetailBinding.carDetailRentDateButton.text = String.format(
                "%02d.%02d.%d/%02d:%02d",
                dayValue,
                monthValue,
                yearValue, hourOfDay, minute
            )
            if (isRentDateTimeSet) {
                calculateDayDifference()
                setRentButton()
            }
            isRentDateTimeSet = true
        }
    }

    private fun calculateDayDifference() {
        minutesDiff = java.time.Duration.between(rentLocalDateTime, returnLocalDateTime).toMinutes()
            .toDouble()
        hourDiff = minutesDiff!! / 60.0
        dayDiffCeil = ceil(ceil(minutesDiff!! / 60.0) / 24)
    }

    private fun setRentButton() {
        fragmentCarDetailBinding.carDetailRentButton.apply {
            if (Helper.data is CarDetail) {
                val carDetail = Helper.data as CarDetail
                val wrongInterval = (minutesDiff!! < 0)
                val rentAtLeast2Hours = (hourDiff!! < 2 && hourDiff!! >= 0)
                text = when {
                    wrongInterval -> {
                        "Please select a correct time interval"
                    }
                    rentAtLeast2Hours -> {
                        "You can rent for at least 2 hours"
                    }
                    else -> {
                        "Rent The ${carDetail.brandName} for ${dayDiffCeil!!.toInt()} days for $${dayDiffCeil!! * carDetail.dailyPrice}"
                    }
                }
                setOnClickListener {

                    when {
                        rentAtLeast2Hours -> {
                            Log.d("Time 2 saat", "2 saatten az")
                        }
                        wrongInterval -> {
                            Log.d("Time yanlış aralık", "aralık yanlış")
                        }
                        else -> {
                            val rental: Rental = Rental(0,carDetail.id,12, rentJsonTimeString, returnJsonTimeString);
                            Log.d("rental", rental.rentDate)
                            Log.d("rental", rental.returnDate)
                            Log.d("rental", rental.id.toString())
                            Log.d("rental", rental.customerId.toString())
                            Log.d("rental", rental.carId.toString())
                            rentalViewModel.checkIfCarCanBeRented(rental).observe(
                                viewLifecycleOwner
                            ) { responseRental ->
                                if (responseRental.success) {

                                    Log.d("Message", responseRental.message.toString())
                                    Log.d("Success", responseRental.success.toString())

                                    /*val fragment = NotificationsFragment()
                                    val bundle = Bundle()
                                    bundle.putString(
                                        "value",
                                        "Rent The ${carDetail.brandName} for ${dayDiffCeil!!.toInt()} days for $${dayDiffCeil!! * carDetail.dailyPrice}"
                                    )
                                    val ft: FragmentTransaction =
                                        requireActivity().supportFragmentManager.beginTransaction()
                                    ft.replace(R.id.car_detail_fragment_container, fragment)
                                    ft.addToBackStack(null)
                                    fragment.arguments = bundle
                                    ft.commit()*/


                                } else {
                                    Log.d("Message", responseRental.message.toString())
                                    Log.d("Success", responseRental.success.toString())
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}
