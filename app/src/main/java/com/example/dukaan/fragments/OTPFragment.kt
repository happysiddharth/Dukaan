package com.example.dukaan.fragments

import android.R.attr.key
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.localDatabase.UsersEntity
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.example.dukaan.viewModels.LoginViewModel
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ViewModelFactory
import com.example.dukaan.views.Homescreen
import kotlinx.android.synthetic.main.fragment_o_t_p.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OTPFragment : Fragment() {

    private lateinit var phone: String
    var is_store_created: Boolean = false
    var is_product_created: Boolean = false

    companion object{
        final val PHONE_KEY = "phone"
        final val PHONE_USER_ID = "user_id"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_o_t_p, container, false)
    }

    override fun onStart() {
        super.onStart()

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            phone = bundle.getString("phoneNumber", "defaultValue")
            tvPhoneNumber.text = "OTP sent to ${phone}"
            val loginRepository = ViewModelProviders.of(this)
                .get(LoginViewModel::class.java)

            btnSubmit.setOnClickListener(View.OnClickListener {
                if (etOTP.text.toString().length > 3) {
                    if (loginRepository.login(phone, etOTP.text.toString())) {
                        PreferenceHelper.writeStringToPreference(context!!,PHONE_KEY,phone)
                        val intent = Intent(context, Homescreen::class.java)
                        intent.putExtra("phone", phone)
                        startActivity(intent)
                    } else {
                        etOTP.setError("Something went wrong")

                    }
                } else {
                    etOTP.setError("Enter correct otp")
                }
            })
        } else {
            tvPhoneNumber.text = "OTP sent to "
        }
    }

}