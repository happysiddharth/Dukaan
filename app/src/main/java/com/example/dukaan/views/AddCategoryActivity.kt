package com.example.dukaan.views

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.localDatabase.CategoriesEntity
import com.example.dukaan.models.ProductsApplication
import com.example.dukaan.viewModels.CategoriesViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.CategoriesViewModelFactory
import kotlinx.android.synthetic.main.activity_add_category.*

class AddCategoryActivity : AppCompatActivity() {

    lateinit var viewModel: CategoriesViewModel
    private var imageData: Uri? = null

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        val appClass = application as ProductsApplication
        val repository = appClass.categoriesRepository
        val viewModelFactory = CategoriesViewModelFactory(repository)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CategoriesViewModel::class.java)

        btnCreateAddCategory.background = ContextCompat.getDrawable(this, R.drawable.disable_btn)

        etCategoryNameAddCategory.setOnClickListener {
            btnCreateAddCategory.background =
                ContextCompat.getDrawable(this, R.drawable.enable_button)
        }

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

                val image = imageData.toString()
                val name = etCategoryNameAddCategory.text.toString()
                val quantity = "10"

                val categoriesEntity = CategoriesEntity(image, name, quantity)
                viewModel.addCategory(categoriesEntity)

                val intent = Intent(this, ProductsActivity::class.java)
                startActivity(intent)
                finish()
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
            imageData = data?.data
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