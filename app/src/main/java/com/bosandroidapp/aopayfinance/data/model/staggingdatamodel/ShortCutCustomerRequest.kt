package com.bosandroidapp.aopayfinance.data.model.staggingdatamodel

import com.google.gson.annotations.SerializedName

data class ShortCutCustomerRequest(

	@field:SerializedName("searchText")
	val searchText: String? = null,

	@field:SerializedName("retailerCode")
	val retailerCode: String? = null
)
