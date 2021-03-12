package com.example.dukaan.interfaces

import com.example.dukaan.localDatabase.ProductEntity

interface AddItemToCart {
    fun onProductClicked(productEntity: ProductEntity)
}