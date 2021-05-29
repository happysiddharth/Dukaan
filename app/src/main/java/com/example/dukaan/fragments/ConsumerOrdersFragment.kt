package com.example.dukaan.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dukaan.R


class ConsumerOrdersFragment : Fragment() {

    private var phoneNo = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        phoneNo = arguments?.getString("ConsumerPhoneNo").toString()
        return inflater.inflate(R.layout.fragment_consumer_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, phoneNo, Toast.LENGTH_SHORT).show()
    }
}