package com.example.dukaan.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dukaan.R
import com.example.dukaan.clickListeners.CategoryClickListener
import com.example.dukaan.localDatabase.CategoriesEntity
import com.example.dukaan.models.ProductsApplication
import com.example.dukaan.recylerViewAdapter.CategoriesDataAdapter
import com.example.dukaan.viewModels.CategoriesViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.CategoriesViewModelFactory
import com.example.dukaan.views.AddCategoryActivity
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : Fragment(), CategoryClickListener {

    private val categoriesList = mutableListOf<CategoriesEntity>()
    private lateinit var categoriesDataAdapter: CategoriesDataAdapter
    private lateinit var viewModel: CategoriesViewModel

    companion object {
        fun newInstance(): CategoriesFragment {
            return CategoriesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesDataAdapter = CategoriesDataAdapter(categoriesList, this)

        val appClass = activity?.application as ProductsApplication
        val repository = appClass.categoriesRepository
        val viewModelFactory = CategoriesViewModelFactory(repository)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CategoriesViewModel::class.java)

        setRecyclerData()
        getCategories()

        btnAddNewCategory.setOnClickListener {
            val intent = Intent(activity, AddCategoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getCategories() {
        viewModel.getCategory().observe(this, Observer {
            categoriesList.clear()
            categoriesList.addAll(it)
            categoriesDataAdapter.notifyDataSetChanged()
        })
    }

    private fun setRecyclerData() {
        recyclerViewCategoriesFragment.layoutManager = LinearLayoutManager(context)
        recyclerViewCategoriesFragment.adapter = categoriesDataAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.category, menu)
        val searchItem = menu.findItem(R.id.category_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onEditClicked(categoriesEntity: CategoriesEntity) {

    }

    override fun onDeleteClicked(categoriesEntity: CategoriesEntity) {

    }
}