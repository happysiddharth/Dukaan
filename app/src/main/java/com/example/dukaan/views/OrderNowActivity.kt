package com.example.dukaan.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dukaan.R
import com.example.dukaan.fragments.ConsumerOrdersFragment
import com.example.dukaan.fragments.OTPFragment
import com.example.dukaan.fragments.OTPFragment.Companion.PHONE_KEY
import com.example.dukaan.fragments.ShowProductsFragment
import com.example.dukaan.interfaces.OnStoreClicked
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.localDatabase.StoreEntity
import com.example.dukaan.recylerViewAdapter.AllStoresAdapter
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ViewModelFactory
import kotlinx.android.synthetic.main.activity_order_now.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OrderNowActivity : AppCompatActivity(), OnStoreClicked {

    lateinit var usersViewModel: UsersViewModel
    lateinit var allStoresAdapter: AllStoresAdapter
    var storesList = mutableListOf<StoreEntity>()
    var CartItem = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_now)

        initViews()
        setRecyclerAdapter()
        showAllStores()
    }

    private fun initViews() {
        var phone = PreferenceHelper.getStringFromPreference(this,PHONE_KEY)
        val database = DukaanRoomDatabase.getDatabaseContext(this)
        val dao = database.getDukaan()
        val viewmodelFactory = ViewModelFactory(dao)
        usersViewModel = ViewModelProviders.of(this, viewmodelFactory)
            .get(UsersViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            CartItem = usersViewModel.getAllOrdersModel().value!!.size
        }
        TVCartItems.setText(CartItem)

        TVCartItems.setOnClickListener {
            val consumerOrdersFragment = ConsumerOrdersFragment()
            var bundle = Bundle()
            bundle.putString("ConsumerPhoneNo", phone)
            consumerOrdersFragment.arguments = bundle
           supportFragmentManager.beginTransaction().replace(R.id.flStoreProduct,consumerOrdersFragment,
               "ConsumerOrdersFragment").addToBackStack("ConsumerOrdersFragment").commit()
        }
    }

    override fun getStoreDetails(storeEntity: StoreEntity) {
        val productsFragment = ShowProductsFragment()
        var bundle = Bundle()
        bundle.putInt("StoreId", storeEntity.id!!)
        bundle.putString("StoreName", storeEntity.store_name)
        productsFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .add(R.id.flStoreProduct, productsFragment, "ShowProductsFragment")
            .addToBackStack("ShowProductsFragment").commit()
    }

    private fun setRecyclerAdapter() {
       allStoresAdapter = AllStoresAdapter(storesList,this)
        rvAllStores.layoutManager = LinearLayoutManager(this)
        rvAllStores.adapter = allStoresAdapter
    }

    private fun showAllStores() {
           usersViewModel.getAllStoreModel().observe(this, Observer {
               storesList.clear()
               storesList.addAll(it)
               allStoresAdapter.notifyDataSetChanged()
           })
    }
}