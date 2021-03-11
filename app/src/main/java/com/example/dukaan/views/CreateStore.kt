package com.example.dukaan.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.fragments.OTPFragment
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.localDatabase.StoreEntity
import com.example.dukaan.localDatabase.UsersEntity
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ViewModelFactory
import kotlinx.android.synthetic.main.activity_create_store2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateStore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_store2)

        val database = DukaanRoomDatabase.getDatabaseContext(applicationContext)
        val dao = database.getDukaan()
        val viewmodelFactory = ViewModelFactory(dao)
        val usersViewModel = ViewModelProviders.of(this, viewmodelFactory)
            .get(UsersViewModel::class.java)

        btnAddStore.setOnClickListener(View.OnClickListener {
            if (etBusinessName.text.toString().isNotEmpty() && etBusinessCategories.text.toString().isNotEmpty()){

                val phone_number:String? = PreferenceHelper.getStringFromPreference( applicationContext, OTPFragment.PHONE_KEY)
                val userID:Int? = PreferenceHelper.getIntFromPreference(applicationContext,OTPFragment.PHONE_USER_ID)

                CoroutineScope(Dispatchers.IO).launch {
                    usersViewModel.insertStore(StoreEntity(etBusinessName.text.toString(),userID!!,"",etBusinessCategories.text.toString()))
                    var usersEntity = UsersEntity("sid",phone_number!!,true,false,"","","")
                    usersEntity.id = userID!!
                    usersViewModel.updateUser(usersEntity)
                    CoroutineScope(Dispatchers.Main).launch {
                        finish()
                    }
                }
            }
        })




    }
}