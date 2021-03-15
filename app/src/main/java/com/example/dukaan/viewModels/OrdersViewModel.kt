package com.example.dukaan.viewModels

import androidx.lifecycle.ViewModel
import com.example.dukaan.repository.OrdersRepository

class OrdersViewModel(private val ordersRepository: OrdersRepository):ViewModel() {

    fun allOperationsModel():List<String>{
        return ordersRepository.allOperationsRepo()
    }
}