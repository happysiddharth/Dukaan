package com.example.dukaan.fragments

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dukaan.R
import com.example.dukaan.views.Homescreen
import com.example.dukaan.views.RegistrationActivity
import kotlinx.android.synthetic.main.fragment_buyer.*
import kotlinx.android.synthetic.main.fragment_seller.*

class SellerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_seller, container, false)
    }

    companion object {
        fun newInstance(): SellerFragment {
            return SellerFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        BtnRegisterSeller.setOnClickListener {
            if (isDataValid()){
                val mobileNo = EtvSellerMobileNo.text.toString()
                val intent = Intent(context, Homescreen::class.java)
                intent.putExtra("phone", mobileNo)
                startActivity(intent)
            }
        }
    }

    private fun isDataValid(): Boolean {
        if (EtvSellerMobileNo.text.toString().length != 10) {
            EtvSellerMobileNo.setError("Invalid mobile no")
            return false
        }
        return true
    }
}