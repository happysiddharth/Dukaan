package com.example.dukaan.recylerViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dukaan.interfaces.OnStoreClicked
import com.example.dukaan.localDatabase.StoreEntity
import kotlinx.android.synthetic.main.all_store_product_layout.view.*
import kotlinx.android.synthetic.main.allstore_item_layout.view.*

class AllStoresViewHolder(val view:View,val onStoreClicked: OnStoreClicked):RecyclerView.ViewHolder(view) {

    fun setAllStores(storeEntity: StoreEntity){
        view.apply {
            TvStoreName.text = storeEntity.store_name
            TvStoreCategory.text = storeEntity.categories
        }
        view.apply {
            rlStoreRow.setOnClickListener {
                onStoreClicked.getStoreDetails(storeEntity)
            }
        }
    }
}