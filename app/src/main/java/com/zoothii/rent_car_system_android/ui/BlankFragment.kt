package com.zoothii.rent_car_system_android.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zoothii.rent_car_system_android.R
import com.zoothii.rent_car_system_android.databinding.FragmentBlankBinding

class BlankFragment : Fragment(R.layout.fragment_blank) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentBlankBinding.bind(view)
        arguments?.let {
            binding.text.text = BlankFragmentArgs.fromBundle(it).value + " yeah it works"
        }
    }
}