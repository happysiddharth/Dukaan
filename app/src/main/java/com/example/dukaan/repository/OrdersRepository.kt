package com.example.dukaan.repository

class OrdersRepository {

    var operationList = mutableListOf<String>()

    fun setOperationsRepo(){
        operationList.add("Failed")
        operationList.add("Delivered")
        operationList.add("Cancelled")
        operationList.add("Shipped")
        operationList.add("Rejected")
        operationList.add("Accepted")
        operationList.add("Pending")
        operationList.add("All")
    }

    fun allOperationsRepo():List<String>{
        setOperationsRepo()
        return operationList
    }
}