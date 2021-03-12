package com.example.dukaan.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.dukaan.localDatabase.ProductEntity

class ViewPagerAdapter(fm: FragmentManager, behavior: Int,var list:List<ProductEntity>) :
    FragmentStatePagerAdapter(fm, behavior) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            ProductsFragment.newInstance(list)
        } else {
            CategoriesFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {

        var tabName: String = ""

        return if (position == 0) {
            "Products"
        } else {
            "Categories"
        }
    }
}