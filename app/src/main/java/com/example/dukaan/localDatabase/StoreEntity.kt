package com.example.dukaan.localDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "stores", foreignKeys = arrayOf(
        ForeignKey(
            entity = UsersEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("user_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class StoreEntity(
    @ColumnInfo(name = "store_name") var store_name: String,
    @ColumnInfo(name = "user_id") var user_id: Int,
    @ColumnInfo(name = "timestamp") var timestamp: String,
    @ColumnInfo(name = "categories") var categories: String
    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int? = null
}