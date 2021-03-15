package com.example.dukaan.viewModels.usersViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dukaan.repository.CategoriesRepository
import com.example.dukaan.viewModels.CategoriesViewModel

class CategoriesViewModelFactory(private val categoriesRepository: CategoriesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoriesViewModel(categoriesRepository) as T
    }
}