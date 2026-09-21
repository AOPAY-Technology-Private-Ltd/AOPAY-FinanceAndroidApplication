package com.bosandroidapp.aopayfinance.data.model

import com.google.gson.annotations.SerializedName


data class GenerateAccessTokenRequest(

	@field:SerializedName("fcmToken")
	val fcmToken: String? = null,

	@SerializedName("clientcode")
	var clientCode: String,

	@SerializedName("customerCode")
	var customerCode: String

)
