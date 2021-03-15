package com.example.dukaan.viewModels.usersViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dukaan.repository.ProductsRepo
import com.example.dukaan.viewModels.ProductsViewModel

class ProductsViewModelFactory(private val productsRepo: ProductsRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsViewModel(productsRepo) as T
    }
}