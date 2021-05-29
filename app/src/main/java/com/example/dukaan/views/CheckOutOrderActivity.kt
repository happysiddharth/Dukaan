package com.example.dukaan.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.localDatabase.ConsumerEntity
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.usersViewModelFactory.UsersViewModelFactory
import kotlinx.android.synthetic.main.activity_check_out_order.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckOutOrderActivity : AppCompatActivity() {

    lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out_order)
        getDataFromIntent()
        initViews()
    }

    private fun getDataFromIntent() {
        val intent: Intent = intent
        val nameOrder = intent.getStringExtra("nameOrder")
        val priceOrder = intent.getStringExtra("priceOrder")
        TvOrderItemNameCheck.text = nameOrder
        TvOrderItemPriceCheck.text = priceOrder.toString()
        TvOrderItemTotalPriceCheck.text = priceOrder.toString()
    }

    private fun initViews() {
        val database = DukaanRoomDatabase.getDatabaseContext(this)
        val dao = database.getDukaan()
        val viewModelFactory = UsersViewModelFactory(dao)
        usersViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UsersViewModel::class.java)

        BtnPayment.setOnClickListener {
            if (isDataValid()) {
                val name = EtvConsumerName.text.toString()
                val mobileNo = EtvConsumerMobileNo.text.toString()
                val address = EtvConsumerAddress.text.toString()
                val city = EtvConsumerCity.text.toString()
                val pin = EtvConsumerPin.text.toString()
                val consumerEntity = ConsumerEntity(name, mobileNo, address, city, pin, "COD")
                CoroutineScope(Dispatchers.IO).launch {
                    usersViewModel.checkOutOrderModel(consumerEntity)
                }
                val intent = Intent(this, OrderNowActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun isDataValid(): Boolean {
        if (EtvConsumerName.text.toString().isEmpty()) {
            EtvConsumerName.error = "Name Field Can't Be Blank"
            return false
        }
        if (EtvConsumerMobileNo.text.toString().length != 10) {
            EtvConsumerMobileNo.error = "Invalid Mobile No"
            return false
        }
        if (EtvConsumerAddress.text.toString().isEmpty()) {
            EtvConsumerAddress.error = "Address Field Can't Be Blank"
            return false
        }
        if (EtvConsumerCity.text.toString().isEmpty()) {
            EtvConsumerCity.error = "City Field Can't Be Blank"
            return false
        }
        if (EtvConsumerPin.text.toString().isEmpty()) {
            EtvConsumerPin.error = "Pin Field Can't Be Blank"
            return false
        }
        return true
    }
}