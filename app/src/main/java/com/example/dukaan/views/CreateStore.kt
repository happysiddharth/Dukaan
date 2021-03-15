package com.example.dukaan.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.fragments.OTPFragment
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.localDatabase.StoreEntity
import com.example.dukaan.localDatabase.UsersEntity
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.UsersViewModelFactory
import kotlinx.android.synthetic.main.activity_create_store2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateStore : AppCompatActivity() {
    companion object {
        const val STORE_ID = "store_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_store2)

        val database = DukaanRoomDatabase.getDatabaseContext(applicationContext)
        val dao = database.getDukaan()
        val viewModelFactory = UsersViewModelFactory(dao)
        val usersViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UsersViewModel::class.java)

        btnAddStore.setOnClickListener {
            if (etBusinessName.text.toString().isNotEmpty() && etBusinessCategories.text.toString()
                    .isNotEmpty()
            ) {

                val phoneNumber: String? = PreferenceHelper.getStringFromPreference(
                    applicationContext,
                    OTPFragment.PHONE_KEY
                )
                val userID: Int = PreferenceHelper.getIntFromPreference(
                    applicationContext,
                    OTPFragment.PHONE_USER_ID
                )

                CoroutineScope(Dispatchers.IO).launch {
                    usersViewModel.insertStore(
                        StoreEntity(
                            etBusinessName.text.toString(),
                            userID,
                            "",
                            etBusinessCategories.text.toString()
                        )
                    )

                    val storeEntity = usersViewModel.fetchParticularStore(userID)
                    PreferenceHelper.writeIntToPreference(
                        this@CreateStore,
                        STORE_ID,
                        storeEntity.id!!
                    )

                    val usersEntity = UsersEntity("sid", phoneNumber!!,
                        is_created_first_store = true,
                        is_created_first_product = false,
                        imageUrl = "",
                        userType = "Seller"
                    )
                    usersEntity.id = userID
                    usersViewModel.updateUser(usersEntity)
                    CoroutineScope(Dispatchers.Main).launch {
                        finish()
                    }
                }
            }
        }

    }
}