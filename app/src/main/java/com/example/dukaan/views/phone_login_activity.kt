package com.example.dukaan.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dukaan.R
import com.example.dukaan.fragments.OTPFragment
import com.example.dukaan.fragments.Phone_number_enter_field_fragment
import com.example.dukaan.interfaces.LoginWIthPhoneInterface
import kotlinx.android.synthetic.main.fragment_phone_number_enter_field_fragment.*

class phone_login_activity : AppCompatActivity(),LoginWIthPhoneInterface {
        val fragmentManager = supportFragmentManager

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_login_activity)
        setFragment()


    }

    private fun setFragment() {
        val fragmentTrasaction = fragmentManager.beginTransaction()
        val Phone_number_enter_field_fragment = Phone_number_enter_field_fragment(this)
        fragmentTrasaction.add(R.id.phoneFragement,Phone_number_enter_field_fragment,"phone").commit()
    }

    override fun handelOnClick(to: String,phone:String) {
        val fragmentTrasaction = fragmentManager.beginTransaction()
        var bundel = Bundle()
        bundel.putString("phoneNumber",phone)
        val OTPFragment = OTPFragment()
        OTPFragment.arguments = bundel
        fragmentTrasaction.replace(R.id.phoneFragement,OTPFragment,"otp").addToBackStack("otp").commit()

    }
}