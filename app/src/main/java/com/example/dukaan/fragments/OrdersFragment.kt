package com.example.dukaan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dukaan.R
import com.example.dukaan.fragments.OTPFragment.Companion.PHONE_KEY
import com.example.dukaan.recylerViewAdapter.OrderOperationsAdapter
import com.example.dukaan.interfaces.OnOrderOperationClicked
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.repository.OrdersRepository
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.example.dukaan.viewModels.OrdersModelFactory
import com.example.dukaan.viewModels.OrdersViewModel
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_orders.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OrdersFragment : Fragment(), OnOrderOperationClicked {

    lateinit var orderOperationsAdapter: OrderOperationsAdapter
    lateinit var ordersViewModel: OrdersViewModel
    lateinit var usersViewModel: UsersViewModel
    var phone = PreferenceHelper.getStringFromPreference(context!!,PHONE_KEY)
    var Store_Id = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrdersFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setOrderOperationListRecyclerview()
        Store_Id = getStoreId()
    }

    private fun setOrderOperationListRecyclerview() {
        var operationList = ordersViewModel.allOperationsModel() as MutableList<String>
        orderOperationsAdapter = OrderOperationsAdapter(operationList, this)
        rvOrderOperations.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL,
            false
        )
        rvOrderOperations.adapter = orderOperationsAdapter
    }

    private fun initViews() {
        val orderRepo = OrdersRepository()
        val orderViewModelFactory = OrdersModelFactory(orderRepo)
        ordersViewModel =
            ViewModelProviders.of(this, orderViewModelFactory).get(OrdersViewModel::class.java)

        val database = DukaanRoomDatabase.getDatabaseContext(context!!)
        val dao = database.getDukaan()
        val viewmodelFactory = ViewModelFactory(dao)
        usersViewModel = ViewModelProviders.of(this, viewmodelFactory)
            .get(UsersViewModel::class.java)

        launchAllOrderOperations()
    }

    override fun onItemClicked(operation: String) {
        when (operation) {
            "All" -> {
                launchAllOrderOperations()
            }
            "Pending" -> {
                launchPendingOrderOperations()
            }
            "Accepted" -> {
                launchAcceptedOrderOperations()
            }
            "Rejected" -> {
                launchRejectedOrderOperations()
            }
            "Shipped" -> {
                launchShippedOrderOperations()
            }
            "Cancelled" -> {
                launchCancelledOrderOperations()
            }
            "Delivered" -> {
                launchDeliveredOrderOperations()
            }
            "Failed" -> {
                launchFailedOrderOperations()
            }
            else -> {
                launchAllOrderOperations()
            }
        }
    }

    private fun launchAllOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val allOrderOperationsFragment = AllOrderOperationsFragment()
        var bundle = Bundle()
        bundle.putInt("StoreId", Store_Id)
        allOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, allOrderOperationsFragment,
            "AllOrderOperationsFragment"
        ).commit()
    }

    private fun launchPendingOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val pendingOrderOperationsFragment = PendingOrderOperationsFragment()
        var bundle = Bundle()
        bundle.putInt("StoreId", Store_Id)
        pendingOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, pendingOrderOperationsFragment,
            "PendingOrderOperationsFragment"
        ).commit()
    }

    private fun launchAcceptedOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val acceptedOrderOperationsFragment = AcceptedOrderOperationsFragment()
        var bundle = Bundle()
        bundle.putInt("StoreId", Store_Id)
        acceptedOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, acceptedOrderOperationsFragment,
            "AcceptedOrderOperationsFragment"
        ).commit()
    }

    private fun launchRejectedOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val rejectedOrderOperationsFragment = RejectedOrderOperationsFragment()
        var bundle = Bundle()
        bundle.putInt("StoreId", Store_Id)
        rejectedOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, rejectedOrderOperationsFragment,
            "RejectedOrderOperationsFragment"
        ).commit()
    }

    private fun launchShippedOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val shippedOrderOperationsFragment = ShippedOrderOperationsFragment()
        var bundle = Bundle()
        bundle.putInt("StoreId", Store_Id)
        shippedOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, shippedOrderOperationsFragment,
            "ShippedOrderOperationsFragment"
        ).commit()
    }

    private fun launchCancelledOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val cancelledOrderOperationsFragment = CancelledOrderOperationsFragment()
        var bundle = Bundle()
        bundle.putInt("StoreId", Store_Id)
        cancelledOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, cancelledOrderOperationsFragment,
            "CancelledOrderOperationsFragment"
        ).commit()
    }

    private fun launchDeliveredOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val deliveredOrderOperationsFragment = DeliveredOrderOperationsFragment()
        var bundle = Bundle()
        bundle.putInt("StoreId", Store_Id)
        deliveredOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, deliveredOrderOperationsFragment,
            "DeliveredOrderOperationsFragment"
        ).commit()
    }

    private fun launchFailedOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val failedOrderOperationsFragment = FailedOrderOperationsFragment()
        var bundle = Bundle()
        bundle.putInt("StoreId", Store_Id)
        failedOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, failedOrderOperationsFragment,
            "FailedOrderOperationsFragment"
        ).commit()
    }

    private fun getStoreId():Int {
        val userId =  getUserId()
        var StoreId = 0;
        CoroutineScope(Dispatchers.IO).launch {
            usersViewModel.fetchStoreIdModel(userId).observe(this@OrdersFragment, Observer {
                val totalStore = it.size
                for (i in 0 until totalStore){
                    if (it[i].user_id == userId){
                        StoreId = it[i].id!!
                    }
                }
            })
        }
        return StoreId
    }

    private fun getUserId():Int {
        var userId:Int = 0;
        CoroutineScope(Dispatchers.Main).launch {
            usersViewModel.fetchUser(phone!!).observe(this@OrdersFragment, Observer {
                val totalUser = it.size
                for (i in 0 until totalUser){
                    if (it[i].phone == phone){
                        userId = it[i].id!!
                    }
                }
            })
        }
        return userId
    }
}