package com.example.dukaan.localDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable


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
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name = "price") var price: Int,
    @ColumnInfo(name = "selling_price") var selling_price: Int,
    @ColumnInfo(name = "quantity") var quantity: String,
    @ColumnInfo(name = "unit") var unit: String,
    @ColumnInfo(name = "product_details") var product_details: String,
    @ColumnInfo(name = "store_id") var store_id: Int) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}