package com.example.dukaan.views

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dukaan.R
import kotlinx.android.synthetic.main.activity_add_product_details.*

class AddProductDetailsActivity : AppCompatActivity() {

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product_details)

//        if (intent != null && intent.extras != null) {
//            etProductNameAddProductDetails.text = intent.getStringExtra("name") as Editable
//        }

        cvAddImageAddProductDetails.setOnClickListener {
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

        btnAddProductProductDetails.setOnClickListener {
            if (checkValidation()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
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
            ivProductImageAddProductDetails.setImageURI(data?.data)
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

    private fun checkValidation(): Boolean {
        if (etProductNameAddProductDetails.text.toString().isEmpty()) {
            etProductNameAddProductDetails.error = "Can not be empty"
            return false
        }
        if (etProductCategoryAddProductDetails.text.toString().isEmpty()) {
            etProductCategoryAddProductDetails.error = "Can not be empty"
            return false
        }
        if (etMrpAddProductDetails.text.toString().isEmpty()) {
            etMrpAddProductDetails.error = "Can not be empty"
            return false
        }
        if (etSellingPriceAddProductDetails.text.toString().isEmpty()) {
            etSellingPriceAddProductDetails.error = "Can not be empty"
            return false
        }
        if (etQuantityAddProductDetails.text.toString().isEmpty()) {
            etQuantityAddProductDetails.error = "Can not be empty"
            return false
        }
        if (etUnitAddProductDetails.text.toString().isEmpty()) {
            etUnitAddProductDetails.error = "Can not be empty"
            return false
        }
        if (etProductDetailsAddProductDetails.text.toString().isEmpty()) {
            etProductDetailsAddProductDetails.error = "Can not be empty"
            return false
        }
        return true
    }
}