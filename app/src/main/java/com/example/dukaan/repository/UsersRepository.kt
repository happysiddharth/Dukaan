package com.example.dukaan.repository

import androidx.lifecycle.LiveData
import com.example.dukaan.localDatabase.DukaanRoomDAO
import com.example.dukaan.localDatabase.StoreEntity
import com.example.dukaan.localDatabase.UsersEntity

class UsersRepository(val dukaanRoomDAO: DukaanRoomDAO) {
    fun isUserExists(phonenumber: String): Boolean {
        return dukaanRoomDAO.isUserExists(phonenumber).isNotEmpty()
    }

    fun fetchUser(phonenumber: String): LiveData<List<UsersEntity>> {
        return dukaanRoomDAO.fetchUser(phonenumber)
    }

    fun adNewUser(usersEntity: UsersEntity) {
        dukaanRoomDAO.addNewUser(usersEntity)
    }

    fun insertStore(storeEntity: StoreEntity) {
        dukaanRoomDAO.addStore(storeEntity)
    }

    fun fetchParticularStore(userId:Int): StoreEntity {
        return dukaanRoomDAO.fetchParticularStore(userId)
    }

    fun updateUser(usersEntity: UsersEntity) {
        dukaanRoomDAO.updateUser(usersEntity)
    }

    fun getStoreDetails(user_id: Int): LiveData<List<StoreEntity>> {
        return dukaanRoomDAO.getStoreDetails(user_id)
    }
}