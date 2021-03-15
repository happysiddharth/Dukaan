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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.fragments.OTPFragment
import com.example.dukaan.localDatabase.CategoriesEntity
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.localDatabase.ProductEntity
import com.example.dukaan.localDatabase.UsersEntity
import com.example.dukaan.models.ProductsApplication
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.example.dukaan.viewModels.CategoriesViewModel
import com.example.dukaan.viewModels.ProductsViewModel
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.CategoriesViewModelFactory
import com.example.dukaan.viewModels.usersViewModelFactory.ProductsViewModelFactory
import com.example.dukaan.viewModels.usersViewModelFactory.UsersViewModelFactory
import kotlinx.android.synthetic.main.activity_add_product_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddProductDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductsViewModel
    private lateinit var categoriesViewModel: CategoriesViewModel
    private var imageData: Uri? = null

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product_details)

        val appClass = application as ProductsApplication
        val repository = appClass.repository
        val categoriesRepository = appClass.categoriesRepository

        val viewModelFactory = ProductsViewModelFactory(repository)
        val categoriesViewModelFactory = CategoriesViewModelFactory(categoriesRepository)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProductsViewModel::class.java)

        categoriesViewModel = ViewModelProviders.of(this, categoriesViewModelFactory)
            .get(CategoriesViewModel::class.java)

        val database = DukaanRoomDatabase.getDatabaseContext(applicationContext)

        val dao = database.getDukaan()

        val usersViewModelFactory = UsersViewModelFactory(dao)
        val usersViewModel = ViewModelProviders.of(this, usersViewModelFactory)
            .get(UsersViewModel::class.java)

        if (intent != null && intent.extras != null) {
            etProductNameAddProductDetails.setText(intent.getStringExtra("name"))
        }

        etProductDetailsAddProductDetails.setOnClickListener {
            btnAddProductProductDetails.background =
                ContextCompat.getDrawable(this, R.drawable.enable_button)
        }

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

                val image = imageData.toString()
                val name = etProductNameAddProductDetails.text.toString()
                val category = etProductCategoryAddProductDetails.text.toString()
                val price = etMrpAddProductDetails.text.toString().toInt()
                val sellingPrice = etSellingPriceAddProductDetails.text.toString().toInt()
                val quantity = etQuantityAddProductDetails.text.toString()
                val unit = etUnitAddProductDetails.text.toString()
                val productDetails = etProductDetailsAddProductDetails.text.toString()

                val productEntity = ProductEntity(
                    image,
                    name,
                    category,
                    price,
                    sellingPrice,
                    quantity,
                    unit,
                    productDetails,
                    PreferenceHelper.getIntFromPreference(applicationContext, CreateStore.STORE_ID)
                )
                CoroutineScope(Dispatchers.Main).launch {
                    usersViewModel.fetchUser(
                        PreferenceHelper.getStringFromPreference(
                            this@AddProductDetailsActivity,
                            OTPFragment.PHONE_KEY
                        )!!
                    ).observe(this@AddProductDetailsActivity,
                        Observer {
                            val usersEntity: UsersEntity = it[0]
                            usersEntity.is_created_first_product = true
                            CoroutineScope(Dispatchers.IO).launch {
                                usersViewModel.updateUser(usersEntity)
                            }

                        })

                }
                viewModel.addProduct(productEntity)

                val categoriesEntity = CategoriesEntity(image, category, quantity)
                categoriesViewModel.addCategory(categoriesEntity)

                val intent = Intent(applicationContext, ProductsActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
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