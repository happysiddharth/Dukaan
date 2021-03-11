package com.example.dukaan.clickListeners

import com.example.dukaan.localDatabase.ProductEntity

interface ProductClickListener {
    fun onEditClicked(productEntity: ProductEntity)
    fun onDeleteClicked(productEntity: ProductEntity)
}