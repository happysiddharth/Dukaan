package com.example.dukaan.repository

import androidx.lifecycle.LiveData
import com.example.dukaan.localDatabase.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    fun fetchAllStoreRepo(): LiveData<List<StoreEntity>>{
            return dukaanRoomDAO.fetchAllStoreDao()
    }

    fun getAllProductRepo(StoreId:Int):LiveData<List<ProductEntity>>{
     return  dukaanRoomDAO.getAllProduct(StoreId)
    }

    fun getAllOrdersRepo():LiveData<List<OrderEntity>>{
        return dukaanRoomDAO.getAllOrdersDao()
    }

    fun placeOrderRepo(orderEntity: OrderEntity){
        CoroutineScope(Dispatchers.IO).launch {
            dukaanRoomDAO.PlaceOrderDao(orderEntity)
        }
    }

    fun getAllConsumersRepo():LiveData<List<ConsumerEntity>>{
        return dukaanRoomDAO.getAllConsumersDao()
    }

    fun checkOutOrderRepo(consumerEntity: ConsumerEntity){
        CoroutineScope(Dispatchers.IO).launch {
            dukaanRoomDAO.checkOutOrder(consumerEntity)
        }
    }
}