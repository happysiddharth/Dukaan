package com.example.dukaan.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dukaan.R
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.example.dukaan.views.phone_login_activity
import kotlinx.android.synthetic.main.activity_account_page.*

class AccountPage : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_page, container, false)
    }

    companion object {
        fun newInstance():AccountPage{
            return AccountPage()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvSignOut.setOnClickListener(View.OnClickListener {
            PreferenceHelper.writeStringToPreference(context!!,OTPFragment.PHONE_KEY,"")
            val intent = Intent(context!!,phone_login_activity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        })

    }
}