package com.example.dukaan.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dukaan.localDatabase.CategoriesEntity
import com.example.dukaan.repository.CategoriesRepository

class CategoriesViewModel(private val categoriesRepository: CategoriesRepository) : ViewModel() {

    fun addCategory(categoriesEntity: CategoriesEntity) {
        categoriesRepository.addCategory(categoriesEntity)
    }

    fun getCategory(): LiveData<List<CategoriesEntity>> {
        return categoriesRepository.getCategory()
    }

    fun editCategory(categoriesEntity: CategoriesEntity) {
        categoriesRepository.editCategory(categoriesEntity)
    }

    fun deleteCategory(categoriesEntity: CategoriesEntity) {
        categoriesRepository.deleteCategory(categoriesEntity)
    }
}