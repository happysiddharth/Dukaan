package com.example.dukaan.recylerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dukaan.R
import com.example.dukaan.localDatabase.OrderEntity
import kotlinx.android.synthetic.main.order_details_card_layout.view.*

class AllOrderOfStoreAdapter(val orderList:List<OrderEntity>):RecyclerView.Adapter<AllOrderOfStoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllOrderOfStoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_details_card_layout,parent,false)
        return AllOrderOfStoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllOrderOfStoreViewHolder, position: Int) {
        val orderEntity:OrderEntity = orderList[position]
        holder.setAllOrdersOfStore(orderEntity)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}

class AllOrderOfStoreViewHolder(val view:View):RecyclerView.ViewHolder(view){

     fun setAllOrdersOfStore(orderEntity: OrderEntity){
         view.apply {
             TvOrderId.text = orderEntity.id.toString()
             TvOrderDateTime.text = orderEntity.date
             TvOrderItemCount.text = orderEntity.quantityOrder
             TvOrderPrice.text = orderEntity.priceOrder.toString()
             OrderStatus.text = orderEntity.OrderStatus
         }
         view.apply {
             Glide.with(IvOrderItemImage).load(orderEntity.imageOrder).into(IvOrderItemImage)
         }
     }
}