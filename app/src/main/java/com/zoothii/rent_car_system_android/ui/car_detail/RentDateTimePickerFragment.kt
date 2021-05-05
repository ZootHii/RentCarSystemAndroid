package com.zoothii.rent_car_system_android.ui.car_detail

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class RentDateTimePickerFragment : DialogFragment() {

    private lateinit var onDateSet: DatePickerDialog.OnDateSetListener
    private lateinit var onTimeSet: TimePickerDialog.OnTimeSetListener
    var savedDateTime: String = ""
    private val calendar: Calendar = Calendar.getInstance()

    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0
    private var hour: Int = 0
    private var minute: Int = 0

    fun setDateCallBack(onDateSetListener: DatePickerDialog.OnDateSetListener) {
        onDateSet = onDateSetListener
    }

    fun setTimeCallBack(onTimeSetListener: TimePickerDialog.OnTimeSetListener) {
        onTimeSet = onTimeSetListener
        TimePickerDialog(requireActivity(), onTimeSet, hour, minute, true).show()
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        getDateTimeCalender()
        val datePickerDialog = DatePickerDialog(requireActivity(), onDateSet, year, month, day)
        calendar.set(year, month, day)
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        return datePickerDialog
    }


    private fun getDateTimeCalender() {
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        hour = calendar.get(Calendar.HOUR_OF_DAY)
        minute = calendar.get(Calendar.MINUTE)
        savedDateTime = String.format("%02d-%02d-%d/%02d:%02d", day, month + 1, year, hour, minute)
    }
}