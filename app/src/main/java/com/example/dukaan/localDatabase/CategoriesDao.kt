package com.example.dukaan.localDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCategory(categoriesEntity: CategoriesEntity)

    @Query("select * from CategoriesTable")
    fun getCategory(): LiveData<List<CategoriesEntity>>

    @Update
    fun editCategory(categoriesEntity: CategoriesEntity)

    @Delete
    fun deleteCategory(categoriesEntity: CategoriesEntity)
}