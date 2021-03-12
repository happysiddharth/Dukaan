package com.example.dukaan.recylerViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dukaan.interfaces.AddItemToCart
import com.example.dukaan.localDatabase.ProductEntity
import kotlinx.android.synthetic.main.all_store_product_layout.view.*

class AllProductViewHolder(val view: View,val addItemToCart: AddItemToCart):RecyclerView.ViewHolder(view) {

    fun setProducts(productEntity: ProductEntity){
        view.apply {

            TvOrderStoreProductName.text = productEntity.name
            TvOrderStoreProductPrices.text = productEntity.selling_price.toString()
            TvOrderStoreProductQuantity.text = productEntity.quantity
            BtnAddToCart.setOnClickListener {
                addItemToCart.onProductClicked(productEntity)
            }
        }
    }
}