package com.example.dukaan.recylerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dukaan.R
import com.example.dukaan.clickListeners.CategoryClickListener
import com.example.dukaan.localDatabase.CategoriesEntity
import com.example.dukaan.recylerViewHolders.CategoriesViewHolder

class CategoriesDataAdapter(
    private var categoryEntityList: List<CategoriesEntity>,
    private var categoryClickListener: CategoryClickListener
) : RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_recycler_layout, parent, false)

        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val categoriesEntity = categoryEntityList[position]
        holder.setData(categoriesEntity, categoryClickListener)
    }

    override fun getItemCount(): Int {
        return categoryEntityList.size
    }
}