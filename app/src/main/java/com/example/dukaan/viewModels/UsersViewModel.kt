package com.example.dukaan.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dukaan.localDatabase.DukaanRoomDAO
import com.example.dukaan.localDatabase.StoreEntity
import com.example.dukaan.localDatabase.UsersEntity
import com.example.dukaan.repository.UsersRepository

class UsersViewModel(val dukaanRoomDAO: DukaanRoomDAO) : ViewModel() {
    val userRepository = UsersRepository(dukaanRoomDAO)
    suspend fun isUserExists(phonenumber: String): Boolean {
        return userRepository.isUserExists(phonenumber)
    }

    suspend fun addNewuser(usersEntity: UsersEntity) {
        userRepository.adNewUser(usersEntity)
    }

    suspend fun fetchUser(phonenumber: String): LiveData<List<UsersEntity>> {
        return userRepository.fetchUser(phonenumber)
    }

    suspend fun insertStore(storeEntity: StoreEntity) {
        return userRepository.insertStore(storeEntity)
    }

    suspend fun fetchParticularStore(userId:Int): StoreEntity {
        return userRepository.fetchParticularStore(userId)
    }

    suspend fun updateUser(usersEntity: UsersEntity) {
        userRepository.updateUser(usersEntity)
    }

    fun getStoreDetails(user_id: Int): LiveData<List<StoreEntity>> {
        return userRepository.getStoreDetails(user_id)
    }

}