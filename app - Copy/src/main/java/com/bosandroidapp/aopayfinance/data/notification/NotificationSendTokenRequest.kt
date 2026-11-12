package com.bosandroidapp.aopayfinance.data.notification

import com.google.gson.annotations.SerializedName

data class NotificationSendTokenRequest(

	@field:SerializedName("deviceType")
	val deviceType: String? = null,

	@SerializedName("clientcode")
	var clientCode: String,

	@field:SerializedName("customerCode")
	val customerCode: String? = null,

	@field:SerializedName("retailerCode")
	val retailerCode: String? = null,

	@field:SerializedName("fcmToken")
	val fcmToken: String? = null
)
