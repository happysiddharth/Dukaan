package com.example.dukaan.views

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
    companion object{
        final val STORE_ID = "store_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_store2)

        val database = DukaanRoomDatabase.getDatabaseContext(applicationContext)
        val dao = database.getDukaan()
        val viewModelFactory = ViewModelFactory(dao)
        val usersViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UsersViewModel::class.java)

        btnAddStore.setOnClickListener(View.OnClickListener {
            if (etBusinessName.text.toString().isNotEmpty() && etBusinessCategories.text.toString()
                    .isNotEmpty()
            ) {

                val phone_number: String? = PreferenceHelper.getStringFromPreference(
                    applicationContext,
                    OTPFragment.PHONE_KEY
                )
                val userID: Int? = PreferenceHelper.getIntFromPreference(
                    applicationContext,
                    OTPFragment.PHONE_USER_ID
                )

                CoroutineScope(Dispatchers.IO).launch {
                    usersViewModel.insertStore(
                        StoreEntity(
                            etBusinessName.text.toString(),
                            userID!!,
                            "",
                            etBusinessCategories.text.toString()
                        )
                    )

                    val storeEntity = usersViewModel.fetchParticularStore(userID)
                    PreferenceHelper.writeIntToPreference(this@CreateStore,CreateStore.STORE_ID,storeEntity.id!!)

                    var usersEntity = UsersEntity("sid",phone_number!!,true,false,"","Seller")
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