package com.example.dukaan.views

import android.os.Bundle
import com.example.dukaan.R
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_change_language.*

class ChangeLanguage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_language)
        btnSaveChanges.setOnClickListener {
            finish()
        }
    }
}