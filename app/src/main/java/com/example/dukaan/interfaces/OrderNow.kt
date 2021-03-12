package com.example.dukaan.interfaces

import com.example.dukaan.localDatabase.ProductEntity

interface OrderNow {
    fun onProductClicked(productEntity: ProductEntity)
}