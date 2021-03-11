package com.example.dukaan.recylerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dukaan.R
import com.example.dukaan.interfaces.AddItemToCart
import com.example.dukaan.localDatabase.ProductEntity
import com.example.dukaan.recylerViewHolders.AllProductViewHolder

class AllProductAdapter(val productList:List<ProductEntity>,val addItemToCart: AddItemToCart):RecyclerView.Adapter<AllProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_store_product_layout,parent,false)
        return AllProductViewHolder(view, addItemToCart)
    }

    override fun onBindViewHolder(holder: AllProductViewHolder, position: Int) {
        val productEntity:ProductEntity = productList[position]
        holder.setProducts(productEntity)
    }

    override fun getItemCount(): Int {
       return productList.size
    }
}