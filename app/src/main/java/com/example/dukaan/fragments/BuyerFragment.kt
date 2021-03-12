package com.example.dukaan.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.localDatabase.UsersEntity
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ViewModelFactory
import com.example.dukaan.views.OrderNowActivity
import kotlinx.android.synthetic.main.fragment_buyer.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BuyerFragment : Fragment() {

    lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
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
        val viewmodelFactory = ViewModelFactory(dao)
        usersViewModel = ViewModelProviders.of(this, viewmodelFactory)
            .get(UsersViewModel::class.java)
        initSetOnclickListener()
    }

    private fun initSetOnclickListener() {
       BtnRegisterBuyer.setOnClickListener {
           if (isDataValid()) {
               val name = EtvBuyerName.text.toString()
               val mobileNo = EtvBuyerMobileNo.text.toString()
               val userType = "Buyer"
               val usersEntity = UsersEntity(name,mobileNo,false,false,"","")
                CoroutineScope(Dispatchers.IO).launch {
                    usersViewModel.addNewuser(usersEntity)
                }
               val intent = Intent(context!!,OrderNowActivity::class.java)
               startActivity(intent)
           }
       }
    }

    private fun isDataValid(): Boolean {
        if (EtvBuyerName.text.toString().isEmpty()) {
            EtvBuyerName.setError("Name Field Can't Be Blank")
            return false
        }
        if (EtvBuyerMobileNo.text.toString().length != 10) {
            EtvBuyerMobileNo.setError("Invalid mobile no")
            return false
        }
        return true
    }

    fun showToast(msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }
}