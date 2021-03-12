package com.example.dukaan.views


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.dukaan.R
import com.example.dukaan.localDatabase.ProductEntity
import com.example.dukaan.models.ProductsApplication
import com.example.dukaan.viewModels.ProductsViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ProductsViewModelFactory
import kotlinx.android.synthetic.main.activity_edit_product.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EditProductActivity : AppCompatActivity() {

    lateinit var productEntity: ProductEntity
    private lateinit var viewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        val appClass = application as ProductsApplication
        val repository = appClass.repository
        val viewModelFactory = ProductsViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProductsViewModel::class.java)

        if (intent != null && intent.extras != null) {
            productEntity = intent.getSerializableExtra("productEntity") as ProductEntity
        }


        Glide.with(this).load(productEntity.image).placeholder(R.drawable.ic_baseline_image_24)
            .into(ivProductImageEditProduct)

        etProductNameEditProduct.setText(productEntity.name)
        etProductCategoryEditProduct.setText(productEntity.category)
        etMrpEditProduct.setText(productEntity.price.toString())
        etSellingPriceEditProduct.setText(productEntity.selling_price.toString())
        etQuantityEditProduct.setText(productEntity.quantity)
        etUnitEditProduct.setText(productEntity.unit)
        etProductDetailsEditProduct.setText(productEntity.product_details)

        btnAddProductEditProduct.setOnClickListener {

            productEntity.image = productEntity.image
            productEntity.name = etProductNameEditProduct.text.toString()
            productEntity.category = etProductCategoryEditProduct.text.toString()
            productEntity.price = etMrpEditProduct.text.toString().toInt()
            productEntity.selling_price = etSellingPriceEditProduct.text.toString().toInt()
            productEntity.quantity = etQuantityEditProduct.text.toString()
            productEntity.unit = etUnitEditProduct.text.toString()
            productEntity.product_details = etProductDetailsEditProduct.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.editProduct(productEntity)
            }

            Toast.makeText(applicationContext,"Updated",Toast.LENGTH_LONG).show()
           finish()
        }

    }
}