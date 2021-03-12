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

    @Insert
    fun addStore(storeEntity: StoreEntity)

    @Update
    fun updateUser(usersEntity: UsersEntity)

    @Query("select * from stores where user_id=:user_id")
    fun getStoreDetails(user_id: Int): LiveData<List<StoreEntity>>

}