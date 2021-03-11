package com.example.dukaan.localDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "products", foreignKeys = arrayOf(
        ForeignKey(
            entity = StoreEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("store_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class ProductEntity(
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "selling_price") val selling_price: Int,
    @ColumnInfo(name = "quantity") val quantity: String,
    @ColumnInfo(name = "unit") val unit: String,
    @ColumnInfo(name = "product_details") val product_details: String,
    @ColumnInfo(name = "store_id") val store_id: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

}