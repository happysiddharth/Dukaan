package com.example.dukaan.recylerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dukaan.R
import com.example.dukaan.interfaces.OnOrderOperationClicked
import com.example.dukaan.recylerViewHolders.OrderOperationsViewHolder

class OrderOperationsAdapter(val operationList:List<String>,val onOrderOperationClicked: OnOrderOperationClicked)
    :RecyclerView.Adapter<OrderOperationsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderOperationsViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.order_operations_layout,parent,false)
        return OrderOperationsViewHolder(view, onOrderOperationClicked)
    }

    override fun onBindViewHolder(holder: OrderOperationsViewHolder, position: Int) {
        val operation:String = operationList[position]
        holder.setOperationsButton(operation)
    }

    override fun getItemCount(): Int {
        return operationList.size
    }
}