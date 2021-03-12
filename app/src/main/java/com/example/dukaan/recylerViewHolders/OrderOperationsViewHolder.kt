package com.example.dukaan.recylerViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dukaan.interfaces.OnOrderOperationClicked
import kotlinx.android.synthetic.main.order_operations_layout.view.*

class OrderOperationsViewHolder(val view: View,val onOrderOperationClicked: OnOrderOperationClicked):RecyclerView.ViewHolder(view) {

    fun setOperationsButton(operation:String){
        view.apply {
            BtnOrdOperation.text = operation
            BtnOrdOperation.setOnClickListener {
                onOrderOperationClicked.onItemClicked(operation)
            }
        }
    }
}
