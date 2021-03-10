package com.example.dukaan.views

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.dukaan.R
import kotlinx.android.synthetic.main.activity_add_category.*

class AddCategoryActivity : AppCompatActivity() {

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        btnCreateAddCategory.background = ContextCompat.getDrawable(this, R.drawable.disable_btn)

        cvAddCategoryImageAddCategory.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED
                ) {

                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)

                } else {
                    pickImageFromGallery()
                }
            } else {
                pickImageFromGallery()
            }
        }

        btnCreateAddCategory.setOnClickListener {
            if (checkValidity()) {
                val intent = Intent(this, ProductsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (checkValidity()) {
            btnCreateAddCategory.background =
                ContextCompat.getDrawable(this, R.drawable.enable_button)
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            ivCategoryImageAddCategory.setImageURI(data?.data)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
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