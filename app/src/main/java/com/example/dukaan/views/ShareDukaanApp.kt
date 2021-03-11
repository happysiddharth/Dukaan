package com.example.dukaan.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dukaan.R
import kotlinx.android.synthetic.main.activity_share_dukaan_app.*

class ShareDukaanApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_dukaan_app)

        btnShareApp.setOnClickListener {
            finish()
        }

        btnShareApk.setOnClickListener {
            finish()
        }
    }
}