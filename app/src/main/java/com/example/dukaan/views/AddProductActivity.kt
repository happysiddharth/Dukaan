package com.example.dukaan.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.localDatabase.ProductEntity
import com.example.dukaan.models.ProductsApplication
import com.example.dukaan.recylerViewAdapter.ProductsDataAdapter
import com.example.dukaan.viewModels.ProductsViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ProductsViewModelFactory
import kotlinx.android.synthetic.main.activity_add_category.*
import kotlinx.android.synthetic.main.activity_add_product.*

class AddProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

       // btnContinueAddProduct.background = ContextCompat.getDrawable(this, R.drawable.disable_btn)


        etProductNameAddProduct.setOnClickListener {
            btnContinueAddProduct.background =
                ContextCompat.getDrawable(this, R.drawable.enable_button)
        }

        btnContinueAddProduct.setOnClickListener {
            if (checkValidity()) {
                val intent = Intent(this, AddProductDetailsActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                intent.putExtra("name", etProductNameAddProduct.text.toString())

                startActivity(intent)
            } else {
                Toast.makeText(this, "Enter properly", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun checkValidity(): Boolean {
        if (etProductNameAddProduct.text.toString().isEmpty()) {
            etProductNameAddProduct.error = "Can not be empty"
            return false
        }
        return true
    }
}