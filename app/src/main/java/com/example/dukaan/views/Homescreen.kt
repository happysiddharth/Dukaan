package com.example.dukaan.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dukaan.R
import com.example.dukaan.fragments.HomescreenFragement

class Homescreen : AppCompatActivity() {
    val fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)
        setHomescreenFragment()
    }

    private fun setHomescreenFragment() {
        val transaction = fragmentManager.beginTransaction()
        val homescreenFragement = HomescreenFragement()
        transaction.add(R.id.flHomescreen,homescreenFragement,"homescreen").commit()

    }
}