package com.example.dukaan.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dukaan.R
import com.example.dukaan.views.AddCategoryActivity
import kotlinx.android.synthetic.main.fragment_catefories.*

class CategoriesFragment : Fragment() {

    companion object {
        public fun newInstance(): CategoriesFragment {
            return CategoriesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catefories, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAddNewCategory.setOnClickListener {
            val intent = Intent(activity, AddCategoryActivity::class.java)
            startActivity(intent)
        }
    }
}