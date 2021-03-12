package com.example.dukaan.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dukaan.localDatabase.*
import com.example.dukaan.repository.UsersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel(val dukaanRoomDAO: DukaanRoomDAO) : ViewModel() {
    val userRepository = UsersRepository(dukaanRoomDAO)
    suspend fun isUserExists(phonenumber: String): Boolean {
        return userRepository.isUserExists(phonenumber)
    }

    suspend fun addNewuser(usersEntity: UsersEntity) {
        userRepository.adNewUser(usersEntity)
    }

    suspend fun updateStore(storeEntity: StoreEntity){
        dukaanRoomDAO.updateStore(storeEntity)
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
    fun getStoreDetails(): LiveData<List<StoreEntity>> {
        return userRepository.getStoreDetails()
    }
      
     fun getAllStoreModel(): LiveData<List<StoreEntity>>{
        return userRepository.fetchAllStoreRepo()
    }

     fun getAllProductModel(StoreId:Int):LiveData<List<ProductEntity>>{
        return userRepository.getAllProductRepo(StoreId)
    }

     fun getAllOrdersModel(storeId:Int):LiveData<List<OrderEntity>>{
        return userRepository.getAllOrdersRepo(storeId)
    }

     fun placeOrderModel(orderEntity: OrderEntity){
         CoroutineScope(Dispatchers.IO).launch {
             userRepository.placeOrderRepo(orderEntity)
         }
    }

     fun getAllConsumersModel():LiveData<List<ConsumerEntity>>{
        return userRepository.getAllConsumersRepo()
    }

     fun checkOutOrderModel(consumerEntity: ConsumerEntity){
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.checkOutOrderRepo(consumerEntity)
        }
    }

    fun fetchStoreIdModel(userId:Int): LiveData<List<StoreEntity>>{
        return userRepository.fetchStoreIdRepo(userId)
    }

}