package com.example.dukaan.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dukaan.repository.OrdersRepository

class OrdersModelFactory(val ordersRepository: OrdersRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OrdersViewModel(ordersRepository) as T
    }
}