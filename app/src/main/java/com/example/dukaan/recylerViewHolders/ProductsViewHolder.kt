package com.example.dukaan.recylerViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dukaan.R
import com.example.dukaan.clickListeners.ProductClickListener
import com.example.dukaan.localDatabase.ProductEntity
import kotlinx.android.synthetic.main.products_recycler_layout.view.*

class ProductsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(
        productEntity: ProductEntity,
        productClickListener: ProductClickListener
    ) {

        view.apply {
            TvOrderStoreProductName.text = productEntity.name
            TvOrderStoreProductPrice.text = "${productEntity.quantity} pieces"
            TvOrderStoreProductPrices.text = "₹${productEntity.selling_price}"
            scStockSwitchProductsRecyclerLayout.isChecked = true
            Glide.with(view).load(productEntity.image).placeholder(R.drawable.ic_baseline_image_24)
                .into(ivProductImageProductRecyclerLayout)
            imageView4.setOnClickListener {
                productClickListener.onDeleteClicked(productEntity)
            }
            cvRecyclerLayout.setOnClickListener {
                productClickListener.onEditClicked(productEntity)
            }

            if (scStockSwitchProductsRecyclerLayout.isChecked) {
                tvStockProductsRecyclerLayout.text = "In stock"
            } else {
                tvStockProductsRecyclerLayout.text = "Out of stock"
            }
        }

    }
}