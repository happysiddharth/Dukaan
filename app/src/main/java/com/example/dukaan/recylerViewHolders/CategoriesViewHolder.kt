package com.example.dukaan.recylerViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dukaan.R
import com.example.dukaan.clickListeners.CategoryClickListener
import com.example.dukaan.localDatabase.CategoriesEntity
import kotlinx.android.synthetic.main.products_recycler_layout.view.*

class CategoriesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(categoriesEntity: CategoriesEntity, categoryClickListener: CategoryClickListener) {

        view.apply {
            Glide.with(view).load(categoriesEntity.image).placeholder(R.drawable.ic_baseline_image_24)
                .into(ivProductImageProductRecyclerLayout)
            tvProductNameProductRecyclerLayout.text = categoriesEntity.name
            tvProductQuantityProductRecyclerLayout.text = "${categoriesEntity.quantity} products available"
            scStockSwitchProductsRecyclerLayout.isChecked = true

            if (scStockSwitchProductsRecyclerLayout.isChecked) {
                tvStockProductsRecyclerLayout.text = "In stock"
            } else {
                tvStockProductsRecyclerLayout.text = "Out of stock"
            }
        }

    }
}