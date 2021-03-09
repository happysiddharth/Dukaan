package com.example.dukaan.recylerViewHolders

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dukaan.R
import kotlinx.android.synthetic.main.order_operations_layout.view.*

class OrderOperationsViewHolder(val view: View,val onOrderOperationClicked: OnOrderOperationClicked):RecyclerView.ViewHolder(view) {

    @SuppressLint("ResourceAsColor")
    fun setOperationsButton(operation:String){
        view.apply {
            BtnOrdOperation.text = operation
            BtnOrdOperation.setOnClickListener {
                onOrderOperationClicked.onItemClicked(operation)
            }
        }
    }
}
