package com.example.dukaan.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dukaan.R
import com.example.dukaan.fragments.OrdersFragment

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        launchFragment()
    }

    fun launchFragment(){
        val OrderFragment = OrdersFragment()
        supportFragmentManager.beginTransaction().add(R.id.flContainer1, OrderFragment, "OrderFragment").commit()
    }

}