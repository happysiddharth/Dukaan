package com.example.dukaan.repository

class OrdersRepository {

    private var operationList = mutableListOf<String>()

    private fun setOperationsRepo(){

        operationList.add("All")
        operationList.add("Pending")
        operationList.add("Accepted")
        operationList.add("Rejected")
        operationList.add("Shipped")
        operationList.add("Cancelled")
        operationList.add("Delivered")
        operationList.add("Failed")
    }

    fun allOperationsRepo():List<String>{
        setOperationsRepo()
        return operationList
    }
}