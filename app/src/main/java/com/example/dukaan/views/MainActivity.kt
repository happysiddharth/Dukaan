package com.example.dukaan.views


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dukaan.R
import com.example.dukaan.fragments.AllOrderOperationsFragment
import com.example.dukaan.fragments.OTPFragment
import com.example.dukaan.fragments.OrdersFragment
import com.example.dukaan.fragments.ViewPagerAdapter
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule



    class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_main)

            Timer("settingup").schedule(500) {
                if (!PreferenceHelper.getStringFromPreference(
                        this@MainActivity,
                        OTPFragment.PHONE_KEY
                    ).isNullOrEmpty()
                ) {
                    val intent = Intent(this@MainActivity, Homescreen::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {

                    val intent = Intent(this@MainActivity, phone_login_activity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }


    }
}