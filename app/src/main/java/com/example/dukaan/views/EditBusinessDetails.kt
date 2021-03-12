package com.example.dukaan.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.fragments.OTPFragment
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ViewModelFactory
import kotlinx.android.synthetic.main.activity_edit_business_details.*

class EditBusinessDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_business_details)

        val database = DukaanRoomDatabase.getDatabaseContext(applicationContext)
        val dao = database.getDukaan()
        val viewmodelFactory = ViewModelFactory(dao)
        val usersViewModel = ViewModelProviders.of(this, viewmodelFactory)
            .get(UsersViewModel::class.java)

        usersViewModel.getStoreDetails(PreferenceHelper.getIntFromPreference(applicationContext,OTPFragment.PHONE_USER_ID).toInt()).observe(this,
            Observer {
                etBusinessNameDetails.setText(it[0].store_name)
                etBusinessNameLink.setText(it[0].store_name + "54232")
                etBusinessCategory.setText(it[0].categories)
            })

    }
}