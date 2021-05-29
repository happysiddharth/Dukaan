package com.example.dukaan.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dukaan.R
import com.example.dukaan.fragments.OTPFragment
import com.example.dukaan.fragments.Phone_number_enter_field_fragment
import com.example.dukaan.interfaces.LoginWIthPhoneInterface

class phone_login_activity : AppCompatActivity(), LoginWIthPhoneInterface {
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_login_activity)
        setFragment()
    }

    private fun setFragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val phoneNumberEnterFieldFragment = Phone_number_enter_field_fragment(this)
        fragmentTransaction.add(R.id.phoneFragement, phoneNumberEnterFieldFragment, "phone")
            .commit()
    }

    override fun handelOnClick(to: String, phone: String) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("phoneNumber", phone)
        val otpFragment = OTPFragment()
        otpFragment.arguments = bundle
        fragmentTransaction.replace(R.id.phoneFragement, otpFragment, "otp").addToBackStack("otp")
            .commit()

    }
}