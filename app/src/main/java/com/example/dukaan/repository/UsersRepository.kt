package com.example.dukaan.repository

import androidx.lifecycle.LiveData
import com.example.dukaan.localDatabase.DukaanRoomDAO
import com.example.dukaan.localDatabase.StoreEntity
import com.example.dukaan.localDatabase.UsersEntity

class UsersRepository(val dukaanRoomDAO: DukaanRoomDAO) {
    fun isUserExists(phonenumber:String): Boolean {
        return dukaanRoomDAO.isUserExists(phonenumber).isNotEmpty()
    }

    fun fetchUser(phonenumber: String): LiveData<List<UsersEntity>> {
        return dukaanRoomDAO.fetchUser(phonenumber)
    }

    fun adNewUser(usersEntity: UsersEntity) {
        dukaanRoomDAO.addNewUser(usersEntity)
    }

    fun insertStore(storeEntity: StoreEntity){
        dukaanRoomDAO.addStore(storeEntity)
    }

    fun updateUser(usersEntity: UsersEntity){
        dukaanRoomDAO.updateUser(usersEntity)
    }

    fun fetchAllStoreRepo(): LiveData<List<StoreEntity>>{
        return dukaanRoomDAO.fetchAllStoreDao()
    }
}