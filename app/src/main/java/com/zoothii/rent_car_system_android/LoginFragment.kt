package com.zoothii.rent_car_system_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zoothii.rent_car_system_android.databinding.FragmentLoginBinding
import com.zoothii.rent_car_system_android.model.CustomerResponse
import com.zoothii.rent_car_system_android.model.UserLogin
import com.zoothii.rent_car_system_android.ui.activity.MainActivity
import com.zoothii.rent_car_system_android.util.Helper
import com.zoothii.rent_car_system_android.view_model.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val authViewModel: AuthViewModel by viewModels()
    lateinit var customerResponse: CustomerResponse

    private lateinit var binding: FragmentLoginBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.apply {
            buttonLogin.setOnClickListener {
                loginCustomer()
            }
            textRegisterNow.setOnClickListener { // TODO should open register fragment
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun loginCustomer(){
        val userEmail = binding.inputEmail.text.toString()
        val userPassword = binding.inputPassword.text.toString()
        val userLogin = UserLogin(userEmail, userPassword)
        authViewModel.loginCustomer(userLogin)
            .observe(viewLifecycleOwner, { responseCustomerResponse ->
                if (responseCustomerResponse.success) {
                    Log.d("Response", responseCustomerResponse.message.toString())
                    customerResponse = responseCustomerResponse.data
                    val userName = "${customerResponse.firstName} ${customerResponse.lastName}"
                    Helper.showToastMessage(requireContext(), "Welcome $userName", false)
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.d("Response", responseCustomerResponse.message.toString())
                }
            })
    }
}