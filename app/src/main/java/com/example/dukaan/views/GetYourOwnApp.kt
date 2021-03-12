package com.example.dukaan.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dukaan.R
import kotlinx.android.synthetic.main.activity_get_your_own_app.*

class GetYourOwnApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_your_own_app)

        btnDownloadApp.setOnClickListener {
            finish()
        }
    }
}