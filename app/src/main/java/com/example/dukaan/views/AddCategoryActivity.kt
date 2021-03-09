package com.example.dukaan.views

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dukaan.R
import kotlinx.android.synthetic.main.activity_add_category.*
import kotlinx.android.synthetic.main.activity_add_product_details.*

class AddCategoryActivity : AppCompatActivity() {

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        cvAddCategoryImageAddCategory.setOnClickListener {
        }

        btnCreateAddCategory.setOnClickListener {
            if (checkValidity()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }


    private fun checkValidity(): Boolean {
        if (etCategoryNameAddCategory.text.toString().isEmpty()) {
            etCategoryNameAddCategory.error = "Can not be empty"
            return false
        }
        return true
    }
}