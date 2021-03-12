package com.example.dukaan.localDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Consumer"/* foreignKeys = arrayOf(
        ForeignKey(
            entity = OrderEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("order_id"),
            onDelete = ForeignKey.CASCADE
        )
    )*/
)
data class ConsumerEntity(
    @ColumnInfo(name = "ConsumerName") var ConsumerName:String,
    @ColumnInfo(name = "ConsumerMobileNO") var ConsumerMobileNo:String,
    @ColumnInfo(name = "ConsumerAddress") var ConsumerAddress:String,
    @ColumnInfo(name = "ConsumerCity") var ConsumerCity:String,
    @ColumnInfo(name = "ConsumerPin") var ConsumerPin:String,
    @ColumnInfo(name = "ConsumerPayment") var ConsumerPayment:String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")var idConsumer:Int? = null
}
