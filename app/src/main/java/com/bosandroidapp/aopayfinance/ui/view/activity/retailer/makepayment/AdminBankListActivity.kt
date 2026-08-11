package com.bosandroidapp.aopayfinance.ui.view.activity.retailer.makepayment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.bos.payment.appName.network.RetrofitClient
import com.bosandroidapp.aopayfinance.R
import com.bosandroidapp.aopayfinance.constant.ConstantClass
import com.bosandroidapp.aopayfinance.data.model.AdminBankDataItem
import com.bosandroidapp.aopayfinance.data.model.AdminBankDetailsReq
import com.bosandroidapp.aopayfinance.data.repository.AuthRepository
import com.bosandroidapp.aopayfinance.data.viewModelFactory.CommonViewModelFactory
import com.bosandroidapp.aopayfinance.databinding.FragmentBankListPageBinding
import com.bosandroidapp.aopayfinance.databinding.FragmentPayoutPageBinding
import com.bosandroidapp.aopayfinance.kioskmode.admin
import com.bosandroidapp.aopayfinance.localdb.SharedPreference
import com.bosandroidapp.aopayfinance.ui.view.activity.fragment.BankListPage.Companion.bankDataList
import com.bosandroidapp.aopayfinance.ui.view.adapter.BankListAdapter
import com.bosandroidapp.aopayfinance.ui.viewmodel.AuthenticationViewModel
import com.bosandroidapp.aopayfinance.utils.ApiStatus
import com.google.gson.Gson


class AdminBankListActivity : Fragment() {

    lateinit var binding: FragmentBankListPageBinding
    lateinit var preference: SharedPreference
    lateinit var viewModel: AuthenticationViewModel
    lateinit var adapter: BankListAdapter
    var bankList : MutableList<AdminBankDataItem?> = mutableListOf()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentBankListPageBinding.inflate(layoutInflater, container, false)
        preference = SharedPreference(requireContext())
        viewModel = ViewModelProvider(this, CommonViewModelFactory(AuthRepository(RetrofitClient.apiInterface)))[AuthenticationViewModel::class.java]
        hitApiForAdminBankList()
        return binding.root

    }

    fun hitApiForAdminBankList(){
        var  adminBankReq = AdminBankDetailsReq(
            adminCode = ConstantClass.Admin
        )

        Log.d("AdminBankListReq", Gson().toJson(adminBankReq))

        viewModel.GetAdminBankDetailsReq(adminBankReq).observe(requireActivity()) { resources ->
            resources.let {
                when (it.apiStatus) {
                    ApiStatus.SUCCESS -> {
                        it.data.let { users ->
                            users!!.body().let { response ->
                                ConstantClass.dialog!!.dismiss()
                                if(response!!.status!!.toLowerCase().equals("true",ignoreCase = true)){
                                    Log.d("BankListRes",Gson().toJson(response))
                                    bankList= response.data!!

                                    if(bankList!!.isNotEmpty()){
                                        setAdapterData(bankList)
                                    }
                                    else{
                                        binding.banklist.visibility= View.GONE
                                        binding.notfoundlayout.visibility= View.VISIBLE
                                    }

                                }
                                else{
                                    binding.banklist.visibility= View.GONE
                                    binding.notfoundlayout.visibility= View.VISIBLE
                                }

                            }

                        }

                    }

                    ApiStatus.ERROR -> {
                        ConstantClass.dialog!!.dismiss()
                    }

                    ApiStatus.LOADING -> {
                        ConstantClass.OpenPopUpForVeryfyOTP(requireContext())
                    }

                }
            }
        }

    }


    private fun setAdapterData( bankList : List<AdminBankDataItem?>?){
        if(bankList!!.isNotEmpty()){
            binding.banklist.visibility= View.VISIBLE
            binding.notfoundlayout.visibility= View.GONE
            adapter = BankListAdapter(requireActivity(), bankList)
            binding.banklist.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        else {
            binding.banklist.visibility= View.GONE
            binding.notfoundlayout.visibility= View.VISIBLE
        }

    }


}