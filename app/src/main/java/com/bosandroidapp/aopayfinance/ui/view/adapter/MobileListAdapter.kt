package com.bosandroidapp.aopayfinance.ui.slideshow.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bosandroidapp.aopayfinance.R
import com.bosandroidapp.aopayfinance.constant.ConstantClass
import com.bosandroidapp.aopayfinance.data.model.ProductDataItem
import com.bosandroidapp.aopayfinance.databinding.MobilelistitemlayoutBinding
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.EMICalculationDetailsPage
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.EMICalculationDetailsPage.Companion.MobileData
import com.bosandroidapp.aopayfinance.data.model.loginsignup.DataItem
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.EMICalculationDetailsPage.Companion.EmiSplitDataModel
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.EMICalculationDetailsPage.Companion.FilterDataEmiSplitDataModel

class MobileListAdapter(private val  MobileDataList : MutableList<ProductDataItem>  = mutableListOf(), var context:Context): RecyclerView.Adapter<MobileListAdapter.ViewHolder>() {
    var  selectPosition = -1
    var MobileColorList : MutableList<String> = mutableListOf()


    class ViewHolder (private val binding: MobilelistitemlayoutBinding): RecyclerView.ViewHolder(binding.root) {
      var mobileicon = binding.mobileicon
      var mobilename = binding.mobilename
      var mobileprice = binding.mobileprice
      var selectborder = binding.selectcard
      var colorlayout = binding.colorlayout
     // var colorcard = binding.colorcard
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MobilelistitemlayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int = MobileDataList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Validate position first
        if (position !in MobileDataList.indices) {
            return
        }

        val item = MobileDataList[position]
        val context = holder.itemView.context

        val imagePath = item.imagePath?.trim()

        if (!imagePath.isNullOrEmpty()) {

            val imageUrl =  imagePath

            Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.samsung)
                .error(R.drawable.samsung)
                .fallback(R.drawable.samsung)
                .into(holder.mobileicon)

        } else {
            // Image path is null/empty
            holder.mobileicon.setImageResource(R.drawable.samsung)
        }


        // ---------------------------------------------------------
        // 2. Mobile Name
        // ---------------------------------------------------------

        val brandName = item.brandName?.trim().orEmpty()
        val modelName = item.modelName?.trim().orEmpty()

        val mobileName = when {
            brandName.isNotEmpty() && modelName.isNotEmpty() ->
                "$brandName $modelName"

            brandName.isNotEmpty() ->
                brandName

            modelName.isNotEmpty() ->
                modelName

            else ->
                "Unknown Mobile"
        }

        holder.mobilename.text = mobileName

        // ---------------------------------------------------------
        // 3. Mobile Price + Variant
        // ---------------------------------------------------------

        val mrpPrice = item.mrpPrice?.trim().orEmpty()
        val variantName = item.variantName?.trim().orEmpty()

        holder.mobileprice.text = when {
            mrpPrice.isNotEmpty() && variantName.isNotEmpty() ->
                "₹ $mrpPrice ($variantName)"

            mrpPrice.isNotEmpty() ->
                "₹ $mrpPrice"

            variantName.isNotEmpty() ->
                variantName

            else ->
                "Price not available"
        }

        // ---------------------------------------------------------
        // 4. Available Colors
        // ---------------------------------------------------------

     /*   holder.colorlayout.removeAllViews()

        val availableColors = item.avlbColors
            ?.split(",")
            ?.mapNotNull { color ->
                color.trim().takeIf { it.isNotEmpty() }
            }
            ?.distinct()
            ?: emptyList()


        if (availableColors.isNotEmpty()) {

            val subLayout = LinearLayout(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(8, 4, 8, 4)
                }

                orientation = LinearLayout.HORIZONTAL
            }


            availableColors.forEach { colorValue ->

                val color = try {
                    Color.parseColor(colorValue)
                } catch (e: Exception) {
                    // Invalid color → skip it
                    null
                }

                // Don't create a CardView for invalid colors
                if (color != null) {

                    val cardView = CardView(context).apply {

                        layoutParams = LinearLayout.LayoutParams(
                            context.resources.getDimensionPixelSize(
                                com.intuit.sdp.R.dimen._20sdp
                            ),
                            context.resources.getDimensionPixelSize(
                                com.intuit.sdp.R.dimen._20sdp
                            )
                        ).apply {
                            setMargins(8, 0, 0, 0)
                        }

                        radius = context.resources.getDimension(
                            com.intuit.sdp.R.dimen._10sdp
                        )

                        setCardBackgroundColor(color)
                    }

                    subLayout.addView(cardView)
                }
            }


            // Only add layout if it actually contains colors
            if (subLayout.childCount > 0) {
                holder.colorlayout.addView(subLayout)
            }
        }*/


        if(selectPosition==position){
            holder.selectborder.setBackgroundResource(R.drawable.bg_black_border)
        }
        else{
            holder.selectborder.background = null
        }

        holder.itemView.setOnClickListener{
            selectPosition = position
            notifyDataSetChanged()
            MobileData=MobileDataList[position]
            EmiSplitDataModel.clear()
            FilterDataEmiSplitDataModel.clear()
            context.startActivity(Intent(context, EMICalculationDetailsPage::class.java))

        }

    }

}