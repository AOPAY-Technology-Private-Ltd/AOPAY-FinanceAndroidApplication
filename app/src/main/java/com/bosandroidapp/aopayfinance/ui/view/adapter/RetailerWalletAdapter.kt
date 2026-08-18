package com.bosandroidapp.aopayfinance.ui.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bosandroidapp.aopayfinance.R
import com.bosandroidapp.aopayfinance.databinding.LoandetailesItemlayoutBinding
import com.bosandroidapp.aopayfinance.databinding.RetailerWalletReportsBinding
import com.bosandroidapp.aopayfinance.constant.ConstantClass
import com.bosandroidapp.aopayfinance.constant.ConstantClass.formatDateToReport
import com.bosandroidapp.aopayfinance.data.model.DataItem
import com.bosandroidapp.aopayfinance.data.model.loginsignup.CustomerDataItem
import com.bosandroidapp.aopayfinance.localdb.SharedPreference
import com.bosandroidapp.aopayfinance.ui.view.activity.customer.EmiLoanDetailPage
import com.bosandroidapp.aopayfinance.ui.view.activity.customer.EmiLoanDetailPage.Companion.LoanId
import com.bosandroidapp.aopayfinance.ui.view.model.ColorList
import java.util.Locale

class RetailerWalletAdapter (var context: Context, var retailerWalletReportList :List<DataItem?>?): RecyclerView.Adapter<RetailerWalletAdapter.ViewHolder>() {

    lateinit var preference : SharedPreference

    class ViewHolder (private val binding: RetailerWalletReportsBinding): RecyclerView.ViewHolder(binding.root) {
        var status = binding.status
        var transactionid = binding.transactionid
        var withdrawAmount = binding.withdrawAmount
        var retailercode = binding.retailercode
        var transactiondate = binding.transactiondate
        var remarksmsg = binding.remarksmsg
        var paymentMode = binding.paymentMode
        var payoutMode = binding.payoutMode
        var payoutModeLayout = binding.paymentModeLayout
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RetailerWalletReportsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int = retailerWalletReportList!!.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = retailerWalletReportList!![position]!!
        val statusStr = item.transactionStatus?.toLowerCase() ?: ""

        if(item.payoutMode!!.isNotEmpty()){
            holder.payoutModeLayout.visibility=View.VISIBLE
        }

        else{
            holder.payoutModeLayout.visibility=View.GONE
        }

        when (statusStr) {
            "pending" -> {
                holder.status.setTextColor(ContextCompat.getColor(context, R.color.orange))
                holder.status.setBackgroundResource(R.drawable.bg_status_orange)
            }
            "approved", "success" -> {
                holder.status.setTextColor(ContextCompat.getColor(context, R.color.green))
                holder.status.setBackgroundResource(R.drawable.bg_status_green)
            }
            "rejected", "failed" -> {
                holder.status.setTextColor(ContextCompat.getColor(context, R.color.red))
                holder.status.setBackgroundResource(R.drawable.bg_status_red)
            }
            else -> {
                holder.status.setTextColor(ContextCompat.getColor(context, R.color.darkgrey))
                holder.status.setBackgroundResource(R.drawable.bg_status_light)
            }
        }

        preference = SharedPreference(context)
        holder.status.text = item.transactionStatus?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() } ?: "Unknown"
        holder.transactionid.text = item.transactionID
        holder.withdrawAmount.text = "₹ ".plus(item.amount)
        holder.retailercode.text = item.retailerID
        holder.transactiondate.text = formatDateToReport(item.transactionDate ?: "")
        holder.remarksmsg.text = item.remarks
        holder.paymentMode.text = item.paymentMode ?: "N/A"
        holder.payoutMode.text = item.payoutMode ?: "N/A"

    }



}