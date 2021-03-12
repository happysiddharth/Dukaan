package com.example.dukaan.recylerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dukaan.R
import com.example.dukaan.clickListeners.DeleteTheParticularProduct
import com.example.dukaan.clickListeners.ProductClickListener
import com.example.dukaan.localDatabase.ProductEntity
import com.example.dukaan.recylerViewHolders.ProductsViewHolder

class ProductsDataAdapter(
    private var productEntityList: List<ProductEntity>,
    private var productClickListener: ProductClickListener,
    private var deleteTheParticularProduct: DeleteTheParticularProduct
) : RecyclerView.Adapter<ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_recycler_layout, parent, false)

        return ProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val productEntity = productEntityList[position]
        holder.setData(productEntity, productClickListener,deleteTheParticularProduct)
    }

    override fun getItemCount(): Int {
        return productEntityList.size
    }
}