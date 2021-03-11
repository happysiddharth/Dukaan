package com.example.dukaan.interfaces

import com.example.dukaan.localDatabase.StoreEntity

interface OnStoreClicked {
    fun getStoreDetails(storeEntity: StoreEntity)
}