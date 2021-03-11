package com.example.dukaan.clickListeners

import com.example.dukaan.localDatabase.CategoriesEntity

interface CategoryClickListener {
    fun onEditClicked(categoriesEntity: CategoriesEntity)
    fun onDeleteClicked(categoriesEntity: CategoriesEntity)
}