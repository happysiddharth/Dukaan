package com.example.dukaan.localDatabase

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProduct(productEntity: ProductEntity)

    @Query("select * from products")
    fun getProducts(): LiveData<List<ProductEntity>>

    @Update
    fun editProduct(productEntity: ProductEntity)

    @Delete
    fun deleteProduct(productEntity: ProductEntity)
}