package com.example.dukaan.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.localDatabase.UsersEntity
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.UsersViewModelFactory
import com.example.dukaan.views.OrderNowActivity
import kotlinx.android.synthetic.main.fragment_buyer.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BuyerFragment : Fragment() {

    lateinit var usersViewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buyer, container, false)
    }

    companion object {
        fun newInstance(): BuyerFragment {
            return BuyerFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = DukaanRoomDatabase.getDatabaseContext(context!!)
        val dao = database.getDukaan()
        val viewModelFactory = UsersViewModelFactory(dao)
        usersViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UsersViewModel::class.java)
        initSetOnclickListener()
    }

    private fun initSetOnclickListener() {
        BtnRegisterBuyer.setOnClickListener {
            if (isDataValid()) {
                val name = EtvBuyerName.text.toString()
                val mobileNo = EtvBuyerMobileNo.text.toString()
                val usersEntity = UsersEntity(
                    name, mobileNo,
                    is_created_first_store = false,
                    is_created_first_product = false,
                    imageUrl = "",
                    userType = ""
                )
                CoroutineScope(Dispatchers.IO).launch {
                    usersViewModel.addNewuser(usersEntity)
                }
                val intent = Intent(context!!, OrderNowActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun isDataValid(): Boolean {
        if (EtvBuyerName.text.toString().isEmpty()) {
            EtvBuyerName.error = "Name Field Can't Be Blank"
            return false
        }
        if (EtvBuyerMobileNo.text.toString().length != 10) {
            EtvBuyerMobileNo.error = "Invalid mobile no"
            return false
        }
        return true
    }
}