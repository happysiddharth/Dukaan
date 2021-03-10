package com.example.dukaan.localDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "products", foreignKeys = arrayOf(
    ForeignKey(
        entity = StoreEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("store_id"),
        onDelete = ForeignKey.CASCADE

    )
))
data class ProductEntity(
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "store_id") val store_id:Int,
    @ColumnInfo(name = "timestamp") val timestamp:String,
    @ColumnInfo(name = "category") val category:String,
    @ColumnInfo(name = "sub_category") val sub_category:String,
    @ColumnInfo(name = "image") val image:String,
    @ColumnInfo(name = "price") val price:Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id:Int?=null
}