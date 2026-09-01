package com.bosandroidapp.aopayfinance.ui.view.activity.customer

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bos.payment.appName.network.RetrofitClient
import com.bosandroidapp.aopayfinance.R
import com.bosandroidapp.aopayfinance.constant.ConstantClass
import com.bosandroidapp.aopayfinance.constant.ConstantClass.isInternetAvailable
import com.bosandroidapp.aopayfinance.data.model.CustomerEMIDataItem
import com.bosandroidapp.aopayfinance.data.model.CustomerEmiStatusReq
import com.bosandroidapp.aopayfinance.data.model.loginsignup.CustomerDataItem
import com.bosandroidapp.aopayfinance.data.model.loginsignup.GetCustomerLoanDetailsReq
import com.bosandroidapp.aopayfinance.data.repository.AuthRepository
import com.bosandroidapp.aopayfinance.data.viewModelFactory.CommonViewModelFactory
import com.bosandroidapp.aopayfinance.databinding.ActivityCustomerEmistatusReportBinding
import com.bosandroidapp.aopayfinance.internetchecker.BaseActivity
import com.bosandroidapp.aopayfinance.localdb.SharedPreference
import com.bosandroidapp.aopayfinance.ui.slideshow.adapter.CustomerEMIDetailsAdapter
import com.bosandroidapp.aopayfinance.ui.view.adapter.CustomerEmiStatusAdapter
import com.bosandroidapp.aopayfinance.ui.viewmodel.AuthenticationViewModel
import com.bosandroidapp.aopayfinance.utils.ApiStatus
import com.google.gson.Gson

class CustomerEMIStatusReport : BaseActivity() {

    lateinit var binding: ActivityCustomerEmistatusReportBinding
    lateinit var viewModel: AuthenticationViewModel
    lateinit var preference : SharedPreference
    var customerLoanEmiDetailsList : MutableList<CustomerEMIDataItem?>? = mutableListOf()
    lateinit var adapter : CustomerEmiStatusAdapter


    companion object{
        var loanCode : String = ""
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerEmistatusReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBarsInsets.left, 0, systemBarsInsets.right, systemBarsInsets.bottom)
            WindowInsetsCompat.CONSUMED
        }

        preference = SharedPreference(this)
        viewModel = ViewModelProvider(this, CommonViewModelFactory(AuthRepository(RetrofitClient.apiInterface)))[AuthenticationViewModel::class.java]

        setonClickListner()

    }


    fun  setonClickListner(){

        binding.back.setOnClickListener {
            finish()
        }

    }


    override fun onResume() {
        super.onResume()
        if(isInternetAvailable(this@CustomerEMIStatusReport)) {
            HitApiForEmiList()
        }
    }


    fun HitApiForEmiList(){
        var loanemireq = CustomerEmiStatusReq(
            loanCode = loanCode,
            clientCode = preference.getStringValue(ConstantClass.ClientCode, "")
        )
        Log.d("customerloanEmireq",Gson().toJson(loanemireq))

        viewModel.LoanEmIScheduleWithStatusReq(loanemireq).observe(this) { resources ->
            resources.let {
                when (it.apiStatus) {
                    ApiStatus.SUCCESS -> {
                        it.data?.let { users ->
                            users.body()?.let {
                                response ->
                                Log.d("customerLoanemiresp", Gson().toJson(response))

                                if(ConstantClass.dialog!=null && ConstantClass.dialog?.isShowing==true){
                                    ConstantClass.dialog!!.dismiss()
                                    var LoanEmiList = response.data
                                    customerLoanEmiDetailsList = LoanEmiList as MutableList<CustomerEMIDataItem?>?
                                    if(!customerLoanEmiDetailsList.isNullOrEmpty() && customerLoanEmiDetailsList!!.size>0){
                                        binding.showingLoanList.visibility=View.VISIBLE
                                        binding.notfoundimage.visibility= View.GONE
                                        setDataOnView(customerLoanEmiDetailsList)
                                    }else{
                                       binding.showingLoanList.visibility=View.GONE
                                       binding.notfoundimage.visibility= View.VISIBLE
                                    }

                                }

                            }
                        }

                    }

                    ApiStatus.ERROR -> {
                        if(ConstantClass.dialog!=null && ConstantClass.dialog?.isShowing==true){
                            ConstantClass.dialog!!.dismiss()
                        }

                    }

                    ApiStatus.LOADING -> {
                        ConstantClass.OpenPopUpForVeryfyOTP(this)
                    }

                }
            }
        }

    }


    fun setDataOnView(customerLoanEmiDetailsList : MutableList<CustomerEMIDataItem?>?){
        adapter = CustomerEmiStatusAdapter(this,customerLoanEmiDetailsList!!)
        binding.showingLoanList.adapter = adapter
        adapter.notifyDataSetChanged()
    }


}