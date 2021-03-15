package com.example.dukaan.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.fragments.OTPFragment
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.UsersViewModelFactory
import kotlinx.android.synthetic.main.activity_edit_business_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditBusinessDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_business_details)

        val database = DukaanRoomDatabase.getDatabaseContext(applicationContext)
        val dao = database.getDukaan()
        val viewmodelFactory = UsersViewModelFactory(dao)
        val usersViewModel = ViewModelProviders.of(this, viewmodelFactory)
            .get(UsersViewModel::class.java)

        usersViewModel.getStoreDetails(PreferenceHelper.getIntFromPreference(applicationContext,OTPFragment.PHONE_USER_ID).toInt()).observe(this,
            Observer {
                etBusinessNameDetails.setText(it[0].store_name)
                etBusinessNameLink.setText(it[0].store_name + "54232")
                etBusinessCategory.setText(it[0].categories)
            })

        btnSaveBusiness.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                var storeEntity = usersViewModel.fetchParticularStore(PreferenceHelper.getIntFromPreference(applicationContext,OTPFragment.PHONE_USER_ID))
                storeEntity.categories = etBusinessCategory.text.toString()
                storeEntity.store_name = etBusinessNameDetails.text.toString()

                usersViewModel.updateStore(storeEntity)

            }
            Toast.makeText(this, "Changes saved successfully", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}