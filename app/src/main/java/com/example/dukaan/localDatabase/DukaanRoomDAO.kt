package com.example.dukaan.localDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DukaanRoomDAO {
    @Insert
    fun addNewUser(entity: UsersEntity)

    @Query("select * from users where phone = :phonenumber")
    fun isUserExists(phonenumber: String): List<UsersEntity>

    @Query("select * from users where phone = :phonenumber")
    fun fetchUser(phonenumber: String): LiveData<List<UsersEntity>>

    @Query("select * from stores where user_id = :userId")
    fun fetchParticularStore(userId: Int): StoreEntity
    @Query("select * from users where phone = :phonenumber AND userType =:userType")
    fun getUserType(phonenumber: String, userType: String): List<UsersEntity>

    @Query("select * from products where store_id =:StoreId")
    fun getAllProduct(StoreId: Int): LiveData<List<ProductEntity>>

    @Insert
    fun addStore(storeEntity: StoreEntity)

    @Query("select * from stores")
    fun fetchAllStoreDao(): LiveData<List<StoreEntity>>

    @Update
    fun updateUser(usersEntity: UsersEntity)

    @Query("select * from stores where user_id=:user_id")
    fun getStoreDetails(user_id: Int): LiveData<List<StoreEntity>>

    @Query("SELECT * FROM Orders")
    fun getAllOrdersDao():LiveData<List<OrderEntity>>

    @Insert
    fun PlaceOrderDao(orderEntity: OrderEntity)

    @Query("SELECT * FROM Consumer")
    fun getAllConsumersDao():LiveData<List<ConsumerEntity>>

    @Insert
    fun checkOutOrder(consumerEntity: ConsumerEntity)
}