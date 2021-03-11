package com.example.dukaan.localDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dukaan.localDatabase.UsersEntity

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

    @Query("select * from stores")
    fun getStoreDetails(): LiveData<List<StoreEntity>>

}