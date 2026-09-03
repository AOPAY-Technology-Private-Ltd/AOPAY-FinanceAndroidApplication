package com.bosandroidapp.aopayfinance.data.notification

import com.google.gson.annotations.SerializedName

data class SendNotificationFeatureNameRequest(

	@SerializedName("clientcode")
	var clientCode: String,

	@field:SerializedName("customerCode")
	val customerCode: String? = null,

	@field:SerializedName("retailerCode")
	val retailerCode: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("notificationCode")
	val notificationCode: String? = null
)
