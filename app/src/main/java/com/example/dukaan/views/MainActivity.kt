package com.example.dukaan.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dukaan.R
import com.example.dukaan.fragments.AllOrderOperationsFragment
import com.example.dukaan.fragments.OrdersFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Accept.setOnClickListener {
            val intent = Intent(this,AcceptOrderActivity::class.java)
            startActivity(intent)
        }
        //launchFragment()
    }
}