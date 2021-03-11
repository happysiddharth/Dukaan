package com.example.dukaan.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.fragments.ShowProductsFragment
import com.example.dukaan.interfaces.OnStoreClicked
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.localDatabase.StoreEntity
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ViewModelFactory


class OrderNowActivity : AppCompatActivity(),OnStoreClicked {

    lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_now)

        initViews()
    }

    private fun initViews() {
        val database = DukaanRoomDatabase.getDatabaseContext(this)
        val dao = database.getDukaan()
        val viewmodelFactory = ViewModelFactory(dao)
        usersViewModel = ViewModelProviders.of(this, viewmodelFactory)
            .get(UsersViewModel::class.java)
    }

    override fun getStoreDetails(storeEntity: StoreEntity) {
        val productsFragment = ShowProductsFragment()
        var bundle = Bundle()
        bundle.putInt("StoreId", storeEntity.id!!)
        bundle.putString("StoreName", storeEntity.store_name)
        productsFragment.arguments
        supportFragmentManager.beginTransaction()
            .replace(R.id.flStoreProduct,productsFragment,"ShowProductsFragment").
            addToBackStack("ShowProductsFragment").commit()
    }

}