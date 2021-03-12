package com.example.dukaan.recylerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dukaan.R
import com.example.dukaan.interfaces.OnStoreClicked
import com.example.dukaan.localDatabase.StoreEntity
import com.example.dukaan.recylerViewHolders.AllStoresViewHolder

class AllStoresAdapter(val storesList:List<StoreEntity>,val onStoreClicked: OnStoreClicked):RecyclerView.Adapter<AllStoresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllStoresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.allstore_item_layout,parent,false)
        return AllStoresViewHolder(view, onStoreClicked)
    }

    override fun onBindViewHolder(holder: AllStoresViewHolder, position: Int) {
        val storeEntity:StoreEntity = storesList[position]
        holder.setAllStores(storeEntity)
    }

    override fun getItemCount(): Int {
        return storesList.size
    }
}