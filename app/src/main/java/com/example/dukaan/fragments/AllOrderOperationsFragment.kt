package com.example.dukaan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dukaan.R
import kotlinx.android.synthetic.main.fragment_all_order_operations.*


class AllOrderOperationsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_order_operations, container, false)
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            AllOrderOperationsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val flag = 1
        rlEmptyOrders.setVisibility(if (flag == 1) View.VISIBLE else View.GONE)
    }

}