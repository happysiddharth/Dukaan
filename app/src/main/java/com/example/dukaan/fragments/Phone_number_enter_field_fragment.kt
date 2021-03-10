package com.example.dukaan.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dukaan.R
import com.example.dukaan.interfaces.LoginWIthPhoneInterface
import kotlinx.android.synthetic.main.fragment_phone_number_enter_field_fragment.*


class Phone_number_enter_field_fragment(var loginWIthPhoneInterface:LoginWIthPhoneInterface) : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_phone_number_enter_field_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnToOtp.setOnClickListener(View.OnClickListener {
            loginWIthPhoneInterface.handelOnClick("phonenumberOtp",editTextPhone.text.toString())
        })
    }


}