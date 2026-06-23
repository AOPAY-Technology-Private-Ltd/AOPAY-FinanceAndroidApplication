package com.bosandroidapp.aopayfinance.ui.slideshow.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bosandroidapp.bosmobilefinance.ui.slideshow.ui.view.activity.retailer.cibilreportsfragment.BureauScore.Companion.userScore
import com.bosandroidapp.aopayfinance.R
import com.bosandroidapp.aopayfinance.constant.ConstantClass
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AadhaarResponse
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AadharNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AadharVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BASE_URL_IMAGE
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CardType
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CibilResponse
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustAlternateMobileNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustAreaSector
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustCityName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustCode
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustCurrentAddress
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustFirstName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustFlatNo
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustLastName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustMiddleName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPhotoPath
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPinCode
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPrimaryMobileNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPrimaryMobileVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPrimaryOTP
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustStateName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CusteMailID
import com.bosandroidapp.aopayfinance.constant.ConstantClass.PanNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.PanNumberVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.PanResponse
import com.bosandroidapp.aopayfinance.constant.ConstantClass.cacheImageAndGetUri
import com.bosandroidapp.aopayfinance.constant.ConstantClass.convertToDDMMYYYY
import com.bosandroidapp.aopayfinance.constant.ConstantClass.isAggrementVerified
import com.bosandroidapp.aopayfinance.data.model.CibilDataItem
import com.bosandroidapp.aopayfinance.databinding.LowcibilcustomerreportsitemlayoutBinding
import com.bosandroidapp.aopayfinance.localdb.SharedPreference
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.MobileSelectionActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LowCibilScoreCustomerReportListAdapter(private val ReportsDataList: List<CibilDataItem?> = listOf(), var context: Context) : RecyclerView.Adapter<LowCibilScoreCustomerReportListAdapter.ViewHolder>() {

    lateinit var preference: SharedPreference


    class ViewHolder(private val binding: LowcibilcustomerreportsitemlayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var status = binding.statustxt
        var mailid = binding.mailidtxt
        var customername = binding.customername
        var customercode = binding.customercode
        var address = binding.addresstxt
        var createdDate = binding.createdDatetxt
        var custMob = binding.custMob
        var customerImage = binding.customerImage
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LowcibilcustomerreportsitemlayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }



    override fun getItemCount(): Int = ReportsDataList.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        preference = SharedPreference(context)
        holder.status.text = ReportsDataList[position]!!.activeStatus

        if(ReportsDataList[position]!!.activeStatus!!.toLowerCase().equals("pending")){
            holder.status.setTextColor(ContextCompat.getColor(context, R.color.orange))
        }

        if(ReportsDataList[position]!!.activeStatus!!.toLowerCase().equals("approved")){
            holder.status.setTextColor(ContextCompat.getColor(context, R.color.green))
        }

        if(ReportsDataList[position]!!.activeStatus!!.toLowerCase().equals("rejected")){
            holder.status.setTextColor(ContextCompat.getColor(context, R.color.red))
        }

        holder.mailid.text = ReportsDataList[position]!!.eMailID
        var address = "${ReportsDataList[position]!!.flatNo},${ReportsDataList[position]!!.aearSector}-(${ReportsDataList[position]!!.pinCode})"
        var name = "${ReportsDataList[position]!!.firstName} ${ReportsDataList[position]!!.lastName}"
        var createdDate = convertToDDMMYYYY(ReportsDataList[position]!!.createdAt)
        holder.address.text = address
        holder.customername.text = name
        holder.customercode.text = ReportsDataList[position]!!.customerCode
        holder.createdDate.text = createdDate
        holder.custMob.text = ReportsDataList[position]!!.primaryMobileNumber
        var customerPath = BASE_URL_IMAGE+ReportsDataList[position]!!.custPhotoPath
        Glide.with(context).load(customerPath).into(holder.customerImage)

        holder.itemView.setOnClickListener {

            if(ReportsDataList[position]!!.activeStatus!!.toLowerCase().equals("approved")&& ReportsDataList[position]!!.loanID!!.isNullOrBlank()){

                CoroutineScope(Dispatchers.IO).launch {
                    CustPhotoPath = cacheImageAndGetUri(context,BASE_URL_IMAGE+ReportsDataList[position]!!.custPhotoPath)
                    Log.d("ImageCache", "Uri: $CustPhotoPath")
                }
                CustFirstName = ReportsDataList[position]!!.firstName!!.trim()
                CustMiddleName = ReportsDataList[position]!!.middleName!!.trim()
                CustLastName = ReportsDataList[position]!!.lastName!!.trim()
                CustPrimaryMobileNumber = ReportsDataList[position]!!.primaryMobileNumber!!.trim()
                CustAlternateMobileNumber = ReportsDataList[position]!!.alternateMobileNumber!!.trim()
                isAggrementVerified = "yes"
                CustPrimaryMobileVerified = ReportsDataList[position]!!.primaryMobileVerified!!.trim()
                CusteMailID =  ReportsDataList[position]!!.eMailID!!.trim()
                CustFlatNo = ReportsDataList[position]!!.flatNo!!.trim()
                CustAreaSector = ReportsDataList[position]!!.aearSector!!.trim()
                CustCurrentAddress = ReportsDataList[position]!!.currentAddress!!.trim()
                CustPinCode = ReportsDataList[position]!!.pinCode!!.trim()
                CustStateName = ReportsDataList[position]!!.stateName!!.trim()
                CustCityName = ReportsDataList[position]!!.cityName!!.trim()
                CustCode = ReportsDataList[position]!!.customerCode!!.trim()
                CustPrimaryOTP = ReportsDataList[position]!!.primaryOTP!!.trim()
                AadharNumber = ReportsDataList[position]!!.aadharNumber!!.trim()
                AadharVerified = ReportsDataList[position]!!.aadharNumberVerified!!.trim()
                PanNumber = ReportsDataList[position]!!.panNumber!!.trim()
                PanNumberVerified = ReportsDataList[position]!!.panNumberVerified!!.trim()
                PanResponse = ReportsDataList[position]!!.panApiResponse!!.trim()
                AadhaarResponse = ""
                userScore = ReportsDataList[position]!!.cibilScore!!.toFloat()
                CibilResponse = ReportsDataList[position]!!.cibilApiResponse!!.trim()
                isAggrementVerified = ReportsDataList[position]!!.isAggrementVerified!!.trim()
                ConstantClass.ClickOnCardDashboard = "Customer"
                ConstantClass.ClickOnCardLowCibilScore = CardType

                if(!CustAlternateMobileNumber.isNullOrBlank()){
                    context.startActivity(Intent(context, MobileSelectionActivity::class.java))
                }

                else {
                    Toast.makeText(context,"Please enter alternate mobile number !!", Toast.LENGTH_SHORT).show()
                }

              }

           }

    }


}