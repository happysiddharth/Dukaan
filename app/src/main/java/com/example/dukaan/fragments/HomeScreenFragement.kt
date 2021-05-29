package com.example.dukaan.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.localDatabase.UsersEntity
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.UsersViewModelFactory
import com.example.dukaan.views.AddProductActivity
import com.example.dukaan.views.CreateStore
import kotlinx.android.synthetic.main.activity_create_store2.*
import kotlinx.android.synthetic.main.fragment_homescreen_fragement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeScreenFragement : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homescreen_fragement, container, false)
    }

    override fun onResume() {
        super.onResume()
        val database = DukaanRoomDatabase.getDatabaseContext(context!!)
        val dao = database.getDukaan()
        val viewModelFactory = UsersViewModelFactory(dao)
        val usersViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UsersViewModel::class.java)
        val phoneNumber: String =
            PreferenceHelper.getStringFromPreference(context!!, OTPFragment.PHONE_KEY)!!
        CoroutineScope(Dispatchers.IO).launch {
            if (usersViewModel.isUserExists(phoneNumber)) {
                CoroutineScope(Dispatchers.Main).launch {
                    usersViewModel.fetchUser(phoneNumber)
                        .observe(this@HomeScreenFragement, Observer {
                            if (!it[0].is_created_first_store) {
                                CoroutineScope(Dispatchers.Main).launch {
                                    btnCreateStore.setOnClickListener {
                                        val intent = Intent(context, CreateStore::class.java)
                                        startActivity(intent)

                                    }
                                }
                                btnAddProdcut.background =
                                    ContextCompat.getDrawable(context!!, R.drawable.disable_btn)
                            } else if (!it[0].is_created_first_product) {

                                ivCompletion.setImageResource(R.drawable.percent_complete)

                                tvStoreAdd.visibility = View.GONE

                                ivStoreAddDone.visibility = View.VISIBLE

                                btnCreateStore.background =
                                    ContextCompat.getDrawable(context!!, R.drawable.disable_btn)

                                btnAddProdcut.background =
                                    ContextCompat.getDrawable(context!!, R.drawable.border)

                                btnAddProdcut.setOnClickListener {
                                    val intent = Intent(context, AddProductActivity::class.java)
                                    startActivity(intent)
                                }

                            } else {
                                ivCompletion.setImageResource(R.drawable.percent_complete_66)


                                tvShareOnWhatsapp.setTextColor(resources.getColor(R.color.black))

                                tvShareOnWhatsapp.setOnClickListener {
                                    val sendIntent: Intent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                                        type = "text/plain"
                                    }

                                    val shareIntent = Intent.createChooser(sendIntent, null)
                                    startActivity(shareIntent)
                                }

                                tvStoreAdd.visibility = View.GONE

                                ivStoreAddDone.visibility = View.VISIBLE

                                btnCreateStore.background =
                                    ContextCompat.getDrawable(context!!, R.drawable.disable_btn)

                                btnAddProdcut.background =
                                    ContextCompat.getDrawable(context!!, R.drawable.border)


                                tvAddProduct.visibility = View.GONE

                                ivProductAddDone.visibility = View.VISIBLE

                                btnCreateStore.background =
                                    ContextCompat.getDrawable(context!!, R.drawable.disable_btn)

                                btnAddProdcut.background =
                                    ContextCompat.getDrawable(context!!, R.drawable.disable_btn)

                            }
                        })
                }
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    btnAddProdcut.background =
                        ContextCompat.getDrawable(context!!, R.drawable.disable_btn)
                }

                CoroutineScope(Dispatchers.Main).launch {
                    btnCreateStore.setOnClickListener {
                        val intent = Intent(context, CreateStore::class.java)
                        startActivity(intent)
                    }
                }
                usersViewModel.addNewuser(
                    UsersEntity(
                        "", phoneNumber,
                        is_created_first_store = false,
                        is_created_first_product = false,
                        imageUrl = "",
                        userType = "Seller"
                    )
                )

                CoroutineScope(Dispatchers.Main).launch {
                    usersViewModel.fetchUser(phoneNumber)
                        .observe(this@HomeScreenFragement, Observer {
                            PreferenceHelper.writeStringToPreference(
                                context!!,
                                OTPFragment.PHONE_KEY,
                                phoneNumber
                            )
                            PreferenceHelper.writeIntToPreference(
                                context!!,
                                OTPFragment.PHONE_USER_ID,
                                it[0].id!!
                            )
                        })
                }
            }
        }
    }

}