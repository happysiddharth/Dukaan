package com.example.dukaan.viewModels

import androidx.lifecycle.ViewModel
import com.example.dukaan.repository.LoginRepository

class LoginViewModel: ViewModel() {
    val loginRepository = LoginRepository()

    fun login(phone:String,otp:String): Boolean {
        return loginRepository.otpLogin(phone,otp)
    }
}