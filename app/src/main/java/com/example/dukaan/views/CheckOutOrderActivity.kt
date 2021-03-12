package com.example.dukaan.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R
import com.example.dukaan.localDatabase.ConsumerEntity
import com.example.dukaan.localDatabase.DukaanRoomDatabase
import com.example.dukaan.viewModels.UsersViewModel
import com.example.dukaan.viewModels.ViewModelsFactory.ViewModelFactory
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
        var intent: Intent = getIntent()
        if (intent != null) {
            var nameOrder = intent.getStringExtra("nameOrder")
            var priceOrder = intent.getStringExtra("priceOrder")
            TvOrderItemNameCheck.setText(nameOrder)
            TvOrderItemPriceCheck.setText(priceOrder.toString())
            TvOrderItemTotalPriceCheck.setText(priceOrder.toString())
        }
    }

    private fun initViews() {
        val database = DukaanRoomDatabase.getDatabaseContext(this)
        val dao = database.getDukaan()
        val viewmodelFactory = ViewModelFactory(dao)
        usersViewModel = ViewModelProviders.of(this, viewmodelFactory)
            .get(UsersViewModel::class.java)

        BtnPayment.setOnClickListener {
            if (isDataValid()) {
                var name = EtvConsumerName.text.toString()
                var MobileNo = EtvConsumerMobileNo.text.toString()
                var Address = EtvConsumerAddress.text.toString()
                var City = EtvConsumerCity.text.toString()
                var Pin = EtvConsumerPin.text.toString()
               val consumerEntity = ConsumerEntity(name,MobileNo,Address,City,Pin,"COD")
                CoroutineScope(Dispatchers.IO).launch {
                    usersViewModel.checkOutOrderModel(consumerEntity)
                }
                val intent = Intent(this,OrderNowActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun isDataValid(): Boolean {
        if (EtvConsumerName.text.toString().isEmpty()) {
            EtvConsumerName.setError("Name Field Can't Be Blank")
            return false
        }
        if (EtvConsumerMobileNo.text.toString().length != 10) {
            EtvConsumerMobileNo.setError("Invalid Mobile No")
            return false
        }
        if (EtvConsumerAddress.text.toString().isEmpty()) {
            EtvConsumerAddress.setError("Address Field Can't Be Blank")
            return false
        }
        if (EtvConsumerCity.text.toString().isEmpty()) {
            EtvConsumerCity.setError("City Field Can't Be Blank")
            return false
        }
        if (EtvConsumerPin.text.toString().isEmpty()) {
            EtvConsumerPin.setError("Pin Field Can't Be Blank")
            return false
        }
        return true
    }
}