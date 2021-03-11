package com.example.dukaan.repository

import androidx.lifecycle.LiveData
import com.example.dukaan.localDatabase.ProductEntity
import com.example.dukaan.localDatabase.ProductsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsRepo(private val productsDao: ProductsDao) {

    fun addProduct(productEntity: ProductEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            productsDao.addProduct(productEntity)
        }
    }

    fun getProducts(): LiveData<List<ProductEntity>> {
        return productsDao.getProducts()
    }

    fun editProducts(productEntity: ProductEntity) {
        productsDao.editProduct(productEntity)
    }

    fun deleteProduct(productEntity: ProductEntity) {
        productsDao.deleteProduct(productEntity)
    }
}