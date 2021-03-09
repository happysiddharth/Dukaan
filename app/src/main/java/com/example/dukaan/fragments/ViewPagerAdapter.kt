package com.example.dukaan.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            ProductsFragment.newInstance()
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