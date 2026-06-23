package com.bosandroidapp.aopayfinance.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bosandroidapp.aopayfinance.R
import com.bosandroidapp.aopayfinance.constant.ConstantClass
import com.bosandroidapp.aopayfinance.constant.ConstantClass.twoDecimal
import com.bosandroidapp.aopayfinance.data.model.BankDataItem
import com.bosandroidapp.aopayfinance.data.model.loginsignup.reports.PayoutDataItem
import com.bosandroidapp.aopayfinance.data.model.loginsignup.reports.TransactionHistoryDataItem
import com.bosandroidapp.aopayfinance.databinding.BankDetailsCardLayoutBinding
import com.bosandroidapp.aopayfinance.databinding.PayoutReportItemLayoutBinding
import com.bosandroidapp.aopayfinance.databinding.TransactionItemLayoutBinding
import com.bosandroidapp.aopayfinance.ui.view.adapter.BankDetailsListAdapter.ViewHolder


class TransactionReportAdapter (var context: Context, var transactionReportList : List<TransactionHistoryDataItem?>?): RecyclerView.Adapter<TransactionReportAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionReportAdapter.ViewHolder {
        val binding = TransactionItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    class ViewHolder (var binding : TransactionItemLayoutBinding): RecyclerView.ViewHolder(binding.root)


    override fun onBindViewHolder(holder: TransactionReportAdapter.ViewHolder, position: Int) {

        val status = transactionReportList!![position]!!.transactionStatus

        holder.binding.reportstatus.text = status

        if(status.equals("Pending")){
            holder.binding.reportstatuslayout.backgroundTintList= context.resources.getColorStateList(R.color.orange)
        }

        if(status.equals("Rejected")){
            holder.binding.reportstatuslayout.backgroundTintList= context.resources.getColorStateList(R.color.red)
        }

        if(status.equals("Approved")){
            holder.binding.reportstatuslayout.backgroundTintList= context.resources.getColorStateList(R.color.green)
        }


       if(transactionReportList!![position]!!.transactionType!!.toLowerCase().equals("credit",ignoreCase = true)) {
           holder.binding.transactionamount.setTextColor(ContextCompat.getColor(context,R.color.green))
           holder.binding.transactionamount.text = "+ ₹ %.2f".format(transactionReportList!![position]!!.creditAmount ?: 0.0)
       }
       else {
           holder.binding.transactionamount.setTextColor(ContextCompat.getColor(context, R.color.red))
           holder.binding.transactionamount.text = "- ₹ %.2f".format(transactionReportList!![position]!!.debitAmount ?: 0.0)
        }

       holder.binding.transactionmsg.text = transactionReportList!![position]!!.transactionMessage
       holder.binding.transactionID.text = transactionReportList!![position]!!.transactionID
       holder.binding.date.text = ConstantClass.formatDateToReport(transactionReportList!![position]!!.transactionDate.toString())

    }



    override fun getItemCount(): Int {
        return transactionReportList!!.size
    }


}