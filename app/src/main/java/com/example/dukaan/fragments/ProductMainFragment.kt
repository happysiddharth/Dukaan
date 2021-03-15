package com.example.dukaan.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.dukaan.R
import com.example.dukaan.localDatabase.ProductEntity
import com.example.dukaan.models.ProductsApplication
import com.example.dukaan.sharedpreference.PreferenceHelper
import com.example.dukaan.viewModels.ProductsViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.ProductsViewModelFactory
import com.example.dukaan.views.CreateStore
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_products.tabLayoutProducts
import kotlinx.android.synthetic.main.activity_products.viewPagerProducts
import kotlinx.android.synthetic.main.fragment_product_main.*

class ProductMainFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = viewPagerProducts
        tabLayout = tabLayoutProducts

        val appClass = activity?.application as ProductsApplication
        val repository = appClass.repository
        val viewModelFactory = ProductsViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProductsViewModel::class.java)

        imageButton.setOnClickListener {
            imageButtonCancel.visibility = View.VISIBLE
            editTextTextPersonName.visibility = View.VISIBLE
            imageButton.visibility = View.GONE
            imageButtonCancel.setOnClickListener {
                imageButtonCancel.visibility = View.GONE
                editTextTextPersonName.visibility = View.INVISIBLE
                imageButton.visibility = View.VISIBLE
                editTextTextPersonName.text.clear()
            }

        }

        editTextTextPersonName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.getProducts(PreferenceHelper.getIntFromPreference(context!!,CreateStore.STORE_ID),"%"+s.toString()+"%").observe(this@ProductMainFragment, Observer {
                    setViewPagerAdapter(it)

                })

            }

        })

        viewModel.getProducts(PreferenceHelper.getIntFromPreference(context!!,CreateStore.STORE_ID),"%").observe(this, Observer {
            setViewPagerAdapter(it)

        })

    }
    private fun setViewPagerAdapter(list: List<ProductEntity>) {
        val supportFragmentManager = activity?.supportFragmentManager!!
        val viewPagerAdapter = ViewPagerAdapter(
            supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            list
        )
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }

}