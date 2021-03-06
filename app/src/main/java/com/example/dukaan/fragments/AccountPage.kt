package com.example.dukaan.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.sharedpreference.PreferenceHelper

import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ViewModelFactory
import com.example.dukaan.views.*
import kotlinx.android.synthetic.main.fragment_account_page.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountPage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_page, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountPage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvEditBusiness.setOnClickListener(View.OnClickListener {
            val intent = Intent(context!!,EditBusinessDetails::class.java)
            startActivity(intent)
        })

        val database = DukaanRoomDatabase.getDatabaseContext(context!!)
        val dao = database.getDukaan()
        val viewModelFactory = ViewModelFactory(dao)
        val usersViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UsersViewModel::class.java)

        usersViewModel.getStoreDetails(PreferenceHelper.getIntFromPreference(context!!,OTPFragment.PHONE_USER_ID)).observe(this,
            Observer {
                tvStoreName.text = it[0].store_name
            })



        tvSignOut.setOnClickListener(View.OnClickListener {
            PreferenceHelper.writeStringToPreference(context!!, OTPFragment.PHONE_KEY, "")
            val intent = Intent(context!!, phone_login_activity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        })

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