package com.example.dukaan.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dukaan.R
import com.example.dukaan.clickListeners.ProductClickListener
import com.example.dukaan.localDatabase.ProductEntity
import com.example.dukaan.models.ProductsApplication
import com.example.dukaan.recylerViewAdapter.ProductsDataAdapter
import com.example.dukaan.viewModels.ProductsViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ProductsViewModelFactory
import com.example.dukaan.views.AddProductActivity
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : Fragment(), ProductClickListener {

    private val productList = mutableListOf<ProductEntity>()
    lateinit var productsDataAdapter: ProductsDataAdapter
    lateinit var viewModel: ProductsViewModel

    companion object {
        fun newInstance(): ProductsFragment {
            return ProductsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        productsDataAdapter = ProductsDataAdapter(productList, this)

        val appClass = activity?.application as ProductsApplication
        val repository = appClass.repository
        val viewModelFactory = ProductsViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProductsViewModel::class.java)

        setRecyclerData()
        getProductsData()

        btnAddNewProduct.setOnClickListener {
            val intent = Intent(activity, AddProductActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getProductsData() {
        viewModel.getProducts().observe(this, Observer {
            productList.clear()
            productList.addAll(it)
            productsDataAdapter.notifyDataSetChanged()
        })
    }

    private fun setRecyclerData() {
        recyclerViewProductsFragment.layoutManager = LinearLayoutManager(context)
        recyclerViewProductsFragment.adapter = productsDataAdapter
    }

    override fun onEditClicked(productEntity: ProductEntity) {

    }

    override fun onDeleteClicked(productEntity: ProductEntity) {

    }

}