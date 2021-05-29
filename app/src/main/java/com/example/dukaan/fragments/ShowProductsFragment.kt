package com.example.dukaan.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dukaan.R
import com.example.dukaan.interfaces.OrderNow
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.localDatabase.OrderEntity
import com.example.dukaan.localDatabase.ProductEntity
import com.example.dukaan.recylerViewAdapter.AllProductAdapter
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.UsersViewModelFactory
import com.example.dukaan.views.CheckOutOrderActivity
import kotlinx.android.synthetic.main.fragment_show_products.*
import java.time.LocalDateTime

class ShowProductsFragment : Fragment(), OrderNow {

    var storeId: Int? = 0
    var storeName: String? = ""
    lateinit var usersViewModel: UsersViewModel
    private lateinit var allProductAdapter: AllProductAdapter
    private var productList = mutableListOf<ProductEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        storeId = arguments?.getInt("StoreId")
        storeName = arguments?.getString("StoreName")
        return inflater.inflate(R.layout.fragment_show_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setRecyclerAdapter()
        showAllProductsOfStores()
    }

    private fun initViews() {
        val database = DukaanRoomDatabase.getDatabaseContext(context!!)
        val dao = database.getDukaan()
        val viewModelFactory = UsersViewModelFactory(dao)
        usersViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UsersViewModel::class.java)
        TvOrderStoreName.text = storeName
    }

    private fun setRecyclerAdapter() {
        allProductAdapter = AllProductAdapter(productList, this)
        rvStoreProducts.layoutManager = LinearLayoutManager(context)
        rvStoreProducts.adapter = allProductAdapter
    }

    private fun showAllProductsOfStores() {
        usersViewModel.getAllProductModel(storeId!!).observe(this, Observer {
            productList.clear()
            productList.addAll(it)
            allProductAdapter.notifyDataSetChanged()
        })
    }

    override fun onProductClicked(productEntity: ProductEntity) {
        val orderStatus = "Pending"
        val imageOrder = productEntity.image
        val nameOrder = productEntity.name
        val categoryOrder = productEntity.category
        val priceOrder = productEntity.selling_price
        val quantityOrder = productEntity.quantity
        val unitOrder = productEntity.unit
        val product_detailsOrder = productEntity.product_details
        val store_id = productEntity.store_id

        val time = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            Toast.makeText(context, "Android Version is old", Toast.LENGTH_SHORT).show()
        }
        //Toast.makeText(context,nameOrder,Toast.LENGTH_SHORT).show()

        val orderEntity = OrderEntity(
            imageOrder, nameOrder, categoryOrder, priceOrder, quantityOrder, unitOrder,
            product_detailsOrder, orderStatus, time.toString(), store_id
        )
        usersViewModel.placeOrderModel(orderEntity)
        val intent = Intent(context, CheckOutOrderActivity::class.java)
        intent.putExtra("imageOrder", imageOrder)
        intent.putExtra("nameOrder", nameOrder)
        intent.putExtra("categoryOrder", categoryOrder)
        intent.putExtra("priceOrder", priceOrder)
        intent.putExtra("quantityOrder", quantityOrder)
        intent.putExtra("unitOrder", unitOrder)
        intent.putExtra("product_detailsOrder", product_detailsOrder)
        startActivity(intent)
    }
}