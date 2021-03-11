package com.example.dukaan.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dukaan.localDatabase.ProductEntity
import com.example.dukaan.repository.ProductsRepo

class ProductsViewModel(private val productsRepo: ProductsRepo) : ViewModel() {

    fun addProduct(productEntity: ProductEntity) {
        productsRepo.addProduct(productEntity)
    }

    fun getProducts(): LiveData<List<ProductEntity>> {
        return productsRepo.getProducts()
    }

    fun editProduct(productEntity: ProductEntity) {
        productsRepo.editProducts(productEntity)
    }

    fun deleteProduct(productEntity: ProductEntity) {
        productsRepo.deleteProduct(productEntity)
    }
}