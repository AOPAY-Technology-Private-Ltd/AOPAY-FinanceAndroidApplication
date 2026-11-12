package com.bosandroidapp.aopayfinance.data.model

import com.google.gson.annotations.SerializedName

data class CustomerEmiStatusReq(

	@field:SerializedName("loanCode")
	val loanCode: String? = null,

	@SerializedName("clientcode")
	var clientCode: String
)
