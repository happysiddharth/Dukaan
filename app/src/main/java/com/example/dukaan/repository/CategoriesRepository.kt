package com.example.dukaan.repository

import androidx.lifecycle.LiveData
import com.example.dukaan.localDatabase.CategoriesDao
import com.example.dukaan.localDatabase.CategoriesEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesRepository(private val categoriesDao: CategoriesDao) {

    fun addCategory(categoriesEntity: CategoriesEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            categoriesDao.addCategory(categoriesEntity)
        }
    }

    fun getCategory(): LiveData<List<CategoriesEntity>> {
        return categoriesDao.getCategory()
    }

    fun editCategory(categoriesEntity: CategoriesEntity) {
        categoriesDao.editCategory(categoriesEntity)
    }

    fun deleteCategory(categoriesEntity: CategoriesEntity) {
        categoriesDao.deleteCategory(categoriesEntity)
    }
}