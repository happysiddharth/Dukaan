package com.example.dukaan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dukaan.R
import com.example.dukaan.recylerViewAdapter.OrderOperationsAdapter
import com.example.dukaan.recylerViewHolders.OnOrderOperationClicked
import com.example.dukaan.repository.OrdersRepository
import com.example.dukaan.viewModels.OrdersModelFactory
import com.example.dukaan.viewModels.OrdersViewModel
import kotlinx.android.synthetic.main.fragment_orders.*


class OrdersFragment : Fragment(),OnOrderOperationClicked{

    lateinit var orderOperationsAdapter: OrderOperationsAdapter
    lateinit var ordersViewModel: OrdersViewModel

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
    }

    private fun setOrderOperationListRecyclerview() {
        var operationList = ordersViewModel.allOperationsModel() as MutableList<String>
        orderOperationsAdapter = OrderOperationsAdapter(operationList, this)
        rvOrderOperations.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,
            false)
        rvOrderOperations.adapter = orderOperationsAdapter
    }

    private fun initViews() {
        val orderRepo = OrdersRepository()
        val orderViewModelFactory = OrdersModelFactory(orderRepo)
        ordersViewModel =
            ViewModelProviders.of(this, orderViewModelFactory).get(OrdersViewModel::class.java)
    }

    override fun onItemClicked(operation: String){
        when (operation){
            "All" -> { launchAllOrderOperations() }
            "Pending" -> { launchPendingOrderOperations() }
            "Accepted" -> { launchAcceptedOrderOperations() }
            "Rejected" -> { launchRejectedOrderOperations() }
            "Shipped" -> { launchShippedOrderOperations() }
            "Cancelled" -> { launchCancelledOrderOperations() }
            "Delivered" -> { launchDeliveredOrderOperations() }
            "Failed" -> { launchFailedOrderOperations() }
            else -> { launchAllOrderOperations() }
        }
    }

    private fun launchAllOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val allOrderOperationsFragment = AllOrderOperationsFragment()
        fragmentTransaction.replace(R.id.flContainer, allOrderOperationsFragment,
            "AllOrderOperationsFragment").commit()
    }

    private fun launchPendingOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val pendingOrderOperationsFragment = PendingOrderOperationsFragment()
        fragmentTransaction.replace(R.id.flContainer, pendingOrderOperationsFragment,
            "PendingOrderOperationsFragment").commit()
    }

    private fun launchAcceptedOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val acceptedOrderOperationsFragment = AcceptedOrderOperationsFragment()
        fragmentTransaction.replace(R.id.flContainer, acceptedOrderOperationsFragment,
            "AcceptedOrderOperationsFragment").commit()
    }

    private fun launchRejectedOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val rejectedOrderOperationsFragment =RejectedOrderOperationsFragment()
        fragmentTransaction.replace(R.id.flContainer, rejectedOrderOperationsFragment,
            "RejectedOrderOperationsFragment").commit()
    }

    private fun launchShippedOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val shippedOrderOperationsFragment = ShippedOrderOperationsFragment()
        fragmentTransaction.replace(R.id.flContainer, shippedOrderOperationsFragment,
            "ShippedOrderOperationsFragment").commit()
    }

    private fun launchCancelledOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val cancelledOrderOperationsFragment = CancelledOrderOperationsFragment()
        fragmentTransaction.replace(R.id.flContainer, cancelledOrderOperationsFragment,
            "CancelledOrderOperationsFragment").commit()
    }

    private fun launchDeliveredOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val deliveredOrderOperationsFragment = DeliveredOrderOperationsFragment()
        fragmentTransaction.replace(R.id.flContainer, deliveredOrderOperationsFragment,
            "DeliveredOrderOperationsFragment").commit()
    }

    private fun launchFailedOrderOperations() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val failedOrderOperationsFragment = FailedOrderOperationsFragment()
        fragmentTransaction.replace(R.id.flContainer, failedOrderOperationsFragment,
            "FailedOrderOperationsFragment").commit()
    }
}