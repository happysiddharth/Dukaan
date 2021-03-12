package com.example.dukaan.localDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "Orders", foreignKeys = arrayOf(
        ForeignKey(
            entity = StoreEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("store_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class OrderEntity(
    @ColumnInfo(name = "imageOrder") var imageOrder: String,
    @ColumnInfo(name = "nameOrder") var nameOrder: String,
    @ColumnInfo(name = "categoryOrder") var categoryOrder: String,
    @ColumnInfo(name = "priceOrder") var priceOrder: Int,
    @ColumnInfo(name = "quantityOrder") var quantityOrder: String,
    @ColumnInfo(name = "unitOrder") var unitOrder: String,
    @ColumnInfo(name = "product_detailsOrder") var product_detailsOrder: String,
    @ColumnInfo(name = "OrderStatus")var OrderStatus:String,
    @ColumnInfo(name = "timestampOrder") var date:String,
    @ColumnInfo(name = "store_id") var store_id: Int) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}
