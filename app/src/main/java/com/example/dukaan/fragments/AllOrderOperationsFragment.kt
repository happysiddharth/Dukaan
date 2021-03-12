package com.example.dukaan.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dukaan.R
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.localDatabase.OrderEntity
import com.example.dukaan.localDatabase.ProductEntity
import com.example.dukaan.recylerViewAdapter.AllOrderOfStoreAdapter
import com.example.dukaan.recylerViewAdapter.AllProductAdapter
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ViewModelFactory
import com.example.dukaan.views.AcceptOrderActivity
import kotlinx.android.synthetic.main.fragment_all_order_operations.*
import kotlinx.android.synthetic.main.fragment_show_products.*


class AllOrderOperationsFragment : Fragment() {

    var StoreId: Int? = 0
    lateinit var usersViewModel: UsersViewModel
    lateinit var allOrderOfStoreAdapter: AllOrderOfStoreAdapter
    var orderList = mutableListOf<OrderEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        StoreId = arguments?.getInt("StoreId")
        return inflater.inflate(R.layout.fragment_all_order_operations, container, false)
    }

    companion object {

        fun newInstance(): AllOrderOperationsFragment {
            return AllOrderOperationsFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (orderList.size == 0) {
            rlEmptyOrders.visibility = View.VISIBLE
        } else {
            rlEmptyOrders.visibility = View.GONE
        }
        initViews()
        setRecyclerAdapter()
       // showAllOrderOfStores()
        Toast.makeText(context,"AllOrders Fragment $StoreId",Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {
        val database = DukaanRoomDatabase.getDatabaseContext(context!!)
        val dao = database.getDukaan()
        val viewmodelFactory = ViewModelFactory(dao)
        usersViewModel = ViewModelProviders.of(this, viewmodelFactory)
            .get(UsersViewModel::class.java)
        BtnShareStore.setOnClickListener {
            val intent = Intent(context, AcceptOrderActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setRecyclerAdapter() {
        allOrderOfStoreAdapter = AllOrderOfStoreAdapter(orderList)
        rvAllOrders.layoutManager = LinearLayoutManager(context)
        rvAllOrders.adapter = allOrderOfStoreAdapter
    }

    private fun showAllOrderOfStores() {
        usersViewModel.getAllOrdersModel(StoreId!!).observe(this, Observer {
            orderList.clear()
            orderList.addAll(it)
            allOrderOfStoreAdapter.notifyDataSetChanged()
        })
    }

}