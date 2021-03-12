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
import com.example.dukaan.viewModels.ViewModelsFactory.ViewModelFactory
import com.example.dukaan.views.CheckOutOrderActivity
import kotlinx.android.synthetic.main.fragment_show_products.*
import java.time.LocalDateTime

class ShowProductsFragment : Fragment(), OrderNow {

    var StoreId:Int? = 0
    var StoreName: String? = ""
    lateinit var usersViewModel: UsersViewModel
    lateinit var allProductAdapter: AllProductAdapter
    var productList = mutableListOf<ProductEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        StoreId = arguments?.getInt("StoreId")
        StoreName = arguments?.getString("StoreName")
        return inflater.inflate(R.layout.fragment_show_products, container, false)
    }

    companion object {
        fun newInstance(): ShowProductsFragment {
            return ShowProductsFragment()
        }
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
        val viewmodelFactory = ViewModelFactory(dao)
        usersViewModel = ViewModelProviders.of(this, viewmodelFactory)
            .get(UsersViewModel::class.java)
        TvOrderStoreName.setText(StoreName)
    }

    private fun setRecyclerAdapter() {
        allProductAdapter = AllProductAdapter(productList, this)
        rvStoreProducts.layoutManager = LinearLayoutManager(context)
        rvStoreProducts.adapter = allProductAdapter
    }

    fun showAllProductsOfStores() {
        usersViewModel.getAllProductModel(StoreId!!).observe(this, Observer {
            productList.clear()
            productList.addAll(it)
            allProductAdapter.notifyDataSetChanged()
        })
    }

    override fun onProductClicked(productEntity: ProductEntity) {
        var orderStatus = "Pending"
        var imageOrder = productEntity.image
        var nameOrder = productEntity.name
        var categoryOrder = productEntity.category
        var priceOrder = productEntity.selling_price
        var quantityOrder = productEntity.quantity
        var unitOrder = productEntity.unit
        var product_detailsOrder = productEntity.product_details
        var store_id = productEntity.store_id

        var time = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            Toast.makeText(context,"Android Version is old",Toast.LENGTH_SHORT).show()
        }
        //Toast.makeText(context,nameOrder,Toast.LENGTH_SHORT).show()

        val orderEntity = OrderEntity(imageOrder, nameOrder, categoryOrder, priceOrder, quantityOrder, unitOrder,
            product_detailsOrder, orderStatus,time.toString(),store_id)
        usersViewModel.placeOrderModel(orderEntity)
        val intent = Intent(context,CheckOutOrderActivity::class.java)
        intent.putExtra("imageOrder",imageOrder)
        intent.putExtra("nameOrder",nameOrder)
        intent.putExtra("categoryOrder",categoryOrder)
        intent.putExtra("priceOrder",priceOrder)
        intent.putExtra("quantityOrder",quantityOrder)
        intent.putExtra("unitOrder",unitOrder)
        intent.putExtra("product_detailsOrder",product_detailsOrder)
        startActivity(intent)
    }
}