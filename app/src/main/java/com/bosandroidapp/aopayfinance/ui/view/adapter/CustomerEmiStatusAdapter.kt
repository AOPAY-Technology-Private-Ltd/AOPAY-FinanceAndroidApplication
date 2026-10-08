package com.bosandroidapp.aopayfinance.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bosandroidapp.aopayfinance.constant.ConstantClass.formatTwoDecimalAmount
import com.bosandroidapp.aopayfinance.data.model.CustomerEMIDataItem
import com.bosandroidapp.aopayfinance.databinding.CustomerEmiStatusLayoutBinding

class CustomerEmiStatusAdapter(var context: Context, var ledgerReportList: List<CustomerEMIDataItem?>) : Adapter<CustomerEmiStatusAdapter.ViewHolder>() {

    class ViewHolder(var binding: CustomerEmiStatusLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerEmiStatusAdapter.ViewHolder {
        val binding = CustomerEmiStatusLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = ledgerReportList[position] ?: return
        val binding = holder.binding

        binding.tvReceiptNo.text = item.receiptNo ?: ""

        if (item.recordStatus?.equals("paid", ignoreCase = true) == true) {
            binding.doneimage.setImageResource(com.bosandroidapp.aopayfinance.R.drawable.doneicon)
            binding.amounttitle.text = context.getString(com.bosandroidapp.aopayfinance.R.string.paid_amount)
            binding.tvRecordStatus.setTextColor(ContextCompat.getColor(context, com.bosandroidapp.aopayfinance.R.color.green))
            binding.tvPendingAmount.text = formatTwoDecimalAmount(item.emiAmount)
        } else {
            binding.amounttitle.text = context.getString(com.bosandroidapp.aopayfinance.R.string.due_amount)
            binding.tvRecordStatus.setTextColor(ContextCompat.getColor(context, com.bosandroidapp.aopayfinance.R.color.red))
            binding.doneimage.setImageResource(com.bosandroidapp.aopayfinance.R.drawable.crossicon)
            binding.tvPendingAmount.text = formatTwoDecimalAmount(item.pendingAmount)
        }

        binding.tvEmiAmount.text = formatTwoDecimalAmount(item.emiAmount)
        binding.tvFineAmount.text = formatTwoDecimalAmount(item.fine)
        binding.tvBounceAmount.text = formatTwoDecimalAmount(item.bouncingCharges)
        binding.tvOtherAmount.text = formatTwoDecimalAmount(item.otherCharges)
        binding.tvWaiveAmount.text = formatTwoDecimalAmount(item.waiveOffAmount)
        binding.tvTotalChargesAmount.text = formatTwoDecimalAmount(item.totalCharges)

        binding.tvPaymentMode.text = item.paymentMode ?: ""
        binding.tvRecordStatus.text = item.recordStatus ?: ""
        binding.tvPaymentDate.text = item.paymentDate ?: ""
        binding.tvDueDate.text = "Due Date: ${item.emIDueDate ?: ""}"
    }

    override fun getItemCount(): Int {
        return ledgerReportList.size
    }
}