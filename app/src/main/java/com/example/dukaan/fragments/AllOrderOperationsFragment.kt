package com.example.dukaan.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_all_order_operations.*
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dukaan.R
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.localDatabase.OrderEntity
import com.example.dukaan.recylerViewAdapter.AllOrderOfStoreAdapter
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.UsersViewModelFactory
import com.example.dukaan.views.AcceptOrderActivity

class AllOrderOperationsFragment : Fragment() {


    var storeId: Int? = 0
    lateinit var usersViewModel: UsersViewModel
    private lateinit var allOrderOfStoreAdapter: AllOrderOfStoreAdapter
    private var orderList = mutableListOf<OrderEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        storeId = arguments?.getInt("StoreId")

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
        Toast.makeText(context, "AllOrders Fragment $storeId", Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {
        val database = DukaanRoomDatabase.getDatabaseContext(context!!)
        val dao = database.getDukaan()
        val viewModelFactory = UsersViewModelFactory(dao)
        usersViewModel = ViewModelProviders.of(this, viewModelFactory)
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

}