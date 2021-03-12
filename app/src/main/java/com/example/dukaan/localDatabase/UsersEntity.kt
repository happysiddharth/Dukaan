package com.example.dukaan.localDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UsersEntity(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "phone") var phone: String,
    @ColumnInfo(name = "is_created_first_store") var is_created_first_store: Boolean,
    @ColumnInfo(name = "is_created_first_product") var is_created_first_product: Boolean,
    @ColumnInfo(name = "imageUrl") var imageUrl: String,
    @ColumnInfo(name = "timestamp") var date: String,
    @ColumnInfo(name = "userType") var userType:String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}