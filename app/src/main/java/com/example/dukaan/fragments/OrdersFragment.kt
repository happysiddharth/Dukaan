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
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.recylerViewAdapter.OrderOperationsAdapter
import com.example.dukaan.repository.OrdersRepository
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.example.dukaan.viewModels.OrdersModelFactory
import com.example.dukaan.viewModels.OrdersViewModel
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.UsersViewModelFactory
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.fragment_orders.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OrdersFragment : Fragment(), com.example.dukaan.interfaces.OnOrderOperationClicked {

    private lateinit var orderOperationsAdapter: OrderOperationsAdapter
    private lateinit var ordersViewModel: OrdersViewModel
    lateinit var usersViewModel: UsersViewModel
    lateinit var phone: String
    private var storeId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        phone = PreferenceHelper.getStringFromPreference(context!!, OTPFragment.PHONE_KEY)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val database = DukaanRoomDatabase.getDatabaseContext(context!!)
        val dao = database.getDukaan()
        val viewModelFactory = UsersViewModelFactory(dao)
        usersViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UsersViewModel::class.java)



        initViews()
        setOrderOperationListRecyclerview()
        storeId = getStoreId()
    }

    private fun setOrderOperationListRecyclerview() {
        val operationList = ordersViewModel.allOperationsModel() as MutableList<String>
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
        val bundle = Bundle()
        bundle.putInt("StoreId", storeId)
        allOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, allOrderOperationsFragment,
            "AllOrderOperationsFragment"
        ).commit()
    }

    private fun launchPendingOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val pendingOrderOperationsFragment = PendingOrderOperationsFragment()

        val bundle = Bundle()
        bundle.putInt("StoreId", storeId)
        pendingOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, pendingOrderOperationsFragment,
            "PendingOrderOperationsFragment"
        ).commit()
    }

    private fun launchAcceptedOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val acceptedOrderOperationsFragment = AcceptedOrderOperationsFragment()

        val bundle = Bundle()
        bundle.putInt("StoreId", storeId)
        acceptedOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, acceptedOrderOperationsFragment,
            "AcceptedOrderOperationsFragment"
        ).commit()
    }

    private fun launchRejectedOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val rejectedOrderOperationsFragment =
            RejectedOrderOperationsFragment()
        val bundle = Bundle()
        bundle.putInt("StoreId", storeId)
        rejectedOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, rejectedOrderOperationsFragment,
            "RejectedOrderOperationsFragment"
        ).commit()
    }

    private fun launchShippedOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val shippedOrderOperationsFragment = ShippedOrderOperationsFragment()

        val bundle = Bundle()
        bundle.putInt("StoreId", storeId)
        shippedOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, shippedOrderOperationsFragment,
            "ShippedOrderOperationsFragment"
        ).commit()
    }

    private fun launchCancelledOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val cancelledOrderOperationsFragment = CancelledOrderOperationsFragment()

        val bundle = Bundle()
        bundle.putInt("StoreId", storeId)
        cancelledOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, cancelledOrderOperationsFragment,
            "CancelledOrderOperationsFragment"
        ).commit()
    }

    private fun launchDeliveredOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val deliveredOrderOperationsFragment = DeliveredOrderOperationsFragment()

        val bundle = Bundle()
        bundle.putInt("StoreId", storeId)
        deliveredOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, deliveredOrderOperationsFragment,
            "DeliveredOrderOperationsFragment"
        ).commit()
    }

    private fun launchFailedOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val failedOrderOperationsFragment = FailedOrderOperationsFragment()

        val bundle = Bundle()
        bundle.putInt("StoreId", storeId)
        failedOrderOperationsFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.flContainer, failedOrderOperationsFragment,
            "FailedOrderOperationsFragment"
        ).commit()
    }

    private fun getStoreId(): Int {
        val userId = getUserId()
        var storeId = 0
        CoroutineScope(Dispatchers.Main).launch {
            usersViewModel.fetchStoreIdModel(userId).observe(this@OrdersFragment, Observer {
                val totalStore = it.size
                for (i in 0 until totalStore) {
                    if (it[i].user_id == userId) {
                        storeId = it[i].id!!
                    }
                }
            })
        }
        return storeId
    }

    private fun getUserId(): Int {
        val userId = 0
        CoroutineScope(Dispatchers.Main).launch {
            usersViewModel.fetchUser(phone).observe(this@OrdersFragment, Observer {
                val totalUser = it.size
                for (i in 0 until totalUser) {
                    if (it[i].phone == phone) {
                        //userId = it[i].id!!
                    }
                }
            })
        }
        return userId
    }
}