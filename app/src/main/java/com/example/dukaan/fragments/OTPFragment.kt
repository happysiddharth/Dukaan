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
import com.example.dukaan.viewModels.LoginViewModel
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.UsersViewModelFactory
import com.example.dukaan.views.HomeScreen
import com.example.dukaan.views.OrderNowActivity
import com.example.dukaan.views.RegistrationActivity
import kotlinx.android.synthetic.main.fragment_o_t_p.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OTPFragment : Fragment() {

    private lateinit var phone: String
    lateinit var usersViewModel: UsersViewModel

    companion object {
        const val PHONE_KEY = "phone"
        const val PHONE_USER_ID = "user_id"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_o_t_p, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            phone = bundle.getString("phoneNumber", "defaultValue")
            tvPhoneNumber.text = "OTP sent to $phone"
            val loginRepository = ViewModelProviders.of(this)
                .get(LoginViewModel::class.java)

            btnSubmit.setOnClickListener {
                if (etOTP.text.toString().length > 3) {
                    if (loginRepository.login(phone, etOTP.text.toString())) {
                        PreferenceHelper.writeStringToPreference(context!!, PHONE_KEY, phone)
                        checkUserAlreadyExits()
                    } else {
                        etOTP.error = "Something went wrong"
                    }
                } else {
                    etOTP.error = "Enter correct otp"
                }
            }
        } else {
            tvPhoneNumber.text = "OTP sent to "
        }

        val database = DukaanRoomDatabase.getDatabaseContext(context!!)
        val dao = database.getDukaan()
        val viewmodelFactory = UsersViewModelFactory(dao)
        usersViewModel = ViewModelProviders.of(this, viewmodelFactory)
            .get(UsersViewModel::class.java)
    }

    private fun checkUserAlreadyExits() {
        CoroutineScope(Dispatchers.Main).launch {
            usersViewModel.fetchUser(phone).observe(this@OTPFragment, Observer {
                val totalUser = it.size
                var count = 0
                var user = ""
                for (i in 0 until totalUser) {
                    if (it[i].phone == phone && it[i].userType == "Buyer") {
                        count = 1
                        user = it[i].userType
                    }
                    if (it[i].phone == phone && it[i].userType == "Seller") {
                        count = 1
                        user = it[i].userType
                    }
                }
                if (count == 1) {
                    if (user == "Buyer") {
                        val intent = Intent(context, OrderNowActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(context, HomeScreen::class.java)
                        startActivity(intent)
                    }
                } else {
                    val intent = Intent(context, RegistrationActivity::class.java)
                    startActivity(intent)
                }
            })
        }
    }
}