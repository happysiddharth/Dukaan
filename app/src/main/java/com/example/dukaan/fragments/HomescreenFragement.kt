package com.example.dukaan.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.localDatabase.UsersEntity
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ViewModelFactory
import com.example.dukaan.views.AddProductActivity
import com.example.dukaan.views.CreateStore
import kotlinx.android.synthetic.main.activity_create_store2.*
import kotlinx.android.synthetic.main.fragment_homescreen_fragement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomescreenFragement : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homescreen_fragement, container, false)
    }

    override fun onResume() {
        super.onResume()
        val database = DukaanRoomDatabase.getDatabaseContext(context!!)
        val dao = database.getDukaan()
        val viewmodelFactory = ViewModelFactory(dao)
        val usersViewModel = ViewModelProviders.of(this, viewmodelFactory)
            .get(UsersViewModel::class.java)
        val phone_number:String = PreferenceHelper.getStringFromPreference(context!!,OTPFragment.PHONE_KEY)!!
        CoroutineScope(Dispatchers.IO).launch {
            if (usersViewModel.isUserExists(phone_number)) {
              CoroutineScope(Dispatchers.Main).launch {
                  usersViewModel.fetchUser(phone_number).observe(this@HomescreenFragement, Observer {
                      if (it[0].is_created_first_store==false){
                          CoroutineScope(Dispatchers.Main).launch {
                              btnCreateStore.setOnClickListener(View.OnClickListener {
                                  val intent = Intent(context,CreateStore::class.java)
                                  startActivity(intent)

                              })
                          }
                          btnAddProdcut.background = ContextCompat.getDrawable(context!!,R.drawable.disable_btn)
                      }else{

                          tvStoreAdd.visibility = View.GONE

                          ivStoreAddDone.visibility = View.VISIBLE

                          btnCreateStore.background = ContextCompat.getDrawable(context!!,R.drawable.disable_btn)

                          btnAddProdcut.background = ContextCompat.getDrawable(context!!,R.drawable.border)

                          btnAddProdcut.setOnClickListener(View.OnClickListener {
                              val intent = Intent(context,AddProductActivity::class.java)
                              startActivity(intent)
                          })

                      }
                  })
              }
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    btnAddProdcut.background = ContextCompat.getDrawable(context!!,R.drawable.disable_btn)
                }

                CoroutineScope(Dispatchers.Main).launch {
                    btnCreateStore.setOnClickListener(View.OnClickListener {
                        val intent = Intent(context,CreateStore::class.java)
                        startActivity(intent)

                    })
                }
                usersViewModel.addNewuser(UsersEntity("", phone_number, false, false, "", "","Seller"))

                CoroutineScope(Dispatchers.Main).launch {
                        usersViewModel.fetchUser(phone_number).observe(this@HomescreenFragement, Observer {
                            PreferenceHelper.writeStringToPreference(context!!,OTPFragment.PHONE_KEY,phone_number)
                            PreferenceHelper.writeIntToPreference(context!!,OTPFragment.PHONE_USER_ID,it[0].id!!)
                        })
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}