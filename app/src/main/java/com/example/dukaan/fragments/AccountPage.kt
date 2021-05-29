package com.example.dukaan.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.sharedpreference.PreferenceHelper

import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.UsersViewModelFactory
import com.example.dukaan.views.*
import kotlinx.android.synthetic.main.fragment_account_page.*

class AccountPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_page, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvEditBusiness.setOnClickListener(View.OnClickListener {
            val intent = Intent(context!!, EditBusinessDetails::class.java)
            startActivity(intent)
        })

        val database = DukaanRoomDatabase.getDatabaseContext(context!!)
        val dao = database.getDukaan()
        val viewModelFactory = UsersViewModelFactory(dao)
        val usersViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UsersViewModel::class.java)

        usersViewModel.getStoreDetails(
            PreferenceHelper.getIntFromPreference(
                context!!,
                OTPFragment.PHONE_USER_ID
            )
        ).observe(this,
            Observer {
                if (it.isNotEmpty()) {
                    tvStoreName.text = it[0].store_name
                } else {
                    Toast.makeText(context, "Create a store first", Toast.LENGTH_SHORT).show()
                }

            })



        tvSignOut.setOnClickListener {
            PreferenceHelper.writeStringToPreference(context!!, OTPFragment.PHONE_KEY, "")
            val intent = Intent(context!!, phone_login_activity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        tvShareDukaanApp.setOnClickListener {
            val intent = Intent(context!!, ShareDukaanApp::class.java)
            startActivity(intent)
        }
        tvGetOwnApp.setOnClickListener {
            val intent = Intent(context!!, GetYourOwnApp::class.java)
            startActivity(intent)
        }

        tvChangeLangauge.setOnClickListener {
            val intent = Intent(context!!, ChangeLanguage::class.java)
            startActivity(intent)
        }

        tvEditBusiness.setOnClickListener {
            val intent = Intent(context!!, EditBusinessDetails::class.java)
            startActivity(intent)
        }

    }
}