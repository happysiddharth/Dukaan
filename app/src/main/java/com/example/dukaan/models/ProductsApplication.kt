package com.example.dukaan.models

import android.app.Application
import androidx.room.RoomDatabase
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.repository.CategoriesRepository
import com.example.dukaan.repository.ProductsRepo

class ProductsApplication : Application() {

    private val productsDao by lazy {
        val database = DukaanRoomDatabase.getDatabaseContext(this)
        database.getProductsDao()
    }

    private val categoriesDao by lazy {
        val database = DukaanRoomDatabase.getDatabaseContext(this)
        database.getCategoriesDao()
    }

    val repository by lazy {
        ProductsRepo(productsDao)
    }

    val categoriesRepository by lazy {
        CategoriesRepository(categoriesDao)
    }
}