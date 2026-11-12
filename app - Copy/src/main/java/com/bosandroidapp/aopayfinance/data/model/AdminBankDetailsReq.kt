package com.bosandroidapp.aopayfinance.data.model

import com.google.gson.annotations.SerializedName

data class AdminBankDetailsReq(

	@field:SerializedName("adminCode")
	val adminCode: String? = null,

	@SerializedName("clientcode")
	var clientCode: String
)
