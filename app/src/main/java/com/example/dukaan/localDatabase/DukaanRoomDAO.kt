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

    @Query("select * from users where phone = :phonenumber AND userType =:userType")
    fun getUserType(phonenumber: String,userType:String):List<UsersEntity>

    @Insert
    fun addStore(storeEntity: StoreEntity)

    @Query("select * from stores")
    fun fetchAllStoreDao(): LiveData<List<StoreEntity>>

    @Update
    fun updateUser(usersEntity: UsersEntity)


}