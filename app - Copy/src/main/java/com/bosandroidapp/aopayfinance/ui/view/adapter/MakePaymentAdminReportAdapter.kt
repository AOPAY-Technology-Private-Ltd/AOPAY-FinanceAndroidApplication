package com.bosandroidapp.aopayfinance.ui.view.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bosandroidapp.aopayfinance.R
import com.bosandroidapp.aopayfinance.constant.ConstantClass
import com.bosandroidapp.aopayfinance.constant.ConstantClass.formatDateToReport
import com.bosandroidapp.aopayfinance.constant.ConstantClass.twoDecimal
import com.bosandroidapp.aopayfinance.data.model.DataItem
import com.bosandroidapp.aopayfinance.data.model.MakePaymentReportDataItem
import com.bosandroidapp.aopayfinance.data.model.loginsignup.CustomerDataItem
import com.bosandroidapp.aopayfinance.databinding.MakePaymentRequestReportlayoutBinding
import com.bosandroidapp.aopayfinance.localdb.SharedPreference
import com.bosandroidapp.aopayfinance.ui.view.activity.customer.EmiLoanDetailPage
import com.bosandroidapp.aopayfinance.ui.view.activity.customer.EmiLoanDetailPage.Companion.LoanId
import com.bosandroidapp.aopayfinance.ui.view.model.ColorList

class MakePaymentAdminReportAdapter (var context: Context, var retailerWalletReportList :List<MakePaymentReportDataItem?>): RecyclerView.Adapter<MakePaymentAdminReportAdapter.ViewHolder>() {

    lateinit var preference : SharedPreference


    class ViewHolder ( var binding: MakePaymentRequestReportlayoutBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MakePaymentRequestReportlayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int = retailerWalletReportList!!.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = retailerWalletReportList!![position]!!
        val status = item.recordStatus ?: ""
        when (status.lowercase()) {
            "pending" -> {
                holder.binding.tvRecordStatus.setTextColor(Color.parseColor("#D97706"))
                holder.binding.tvRecordStatus.setBackgroundResource(R.drawable.bg_status_orange)
            }

            "approved" -> {
                holder.binding.tvRecordStatus.setTextColor(Color.parseColor("#16A34A"))
                holder.binding.tvRecordStatus.setBackgroundResource(R.drawable.bg_status_green)
            }

            "rejected" -> {
                holder.binding.tvRecordStatus.setTextColor(Color.parseColor("#DC2626"))
                holder.binding.tvRecordStatus.setBackgroundResource(R.drawable.bg_status_red)
            }

        }

        preference = SharedPreference(context)
        holder.binding.tvRecordStatus.text = retailerWalletReportList!![position]!!.recordStatus
        holder.binding.tvTransactionNo.text = retailerWalletReportList!![position]!!.transactionNo
        holder.binding.tvUtrNumber.text = retailerWalletReportList!![position]!!.utrNumber
        holder.binding.tvReqDate.text = formatDateToReport(retailerWalletReportList!![position]!!.requestDateTime!!)
        holder.binding.tvTransDate.text = formatDateToReport(retailerWalletReportList!![position]!!.transactionDateTime!!)
        holder.binding.tvAmount.text = "₹ " .plus(retailerWalletReportList!![position]!!.requestAmount!!.twoDecimal())

    }



}