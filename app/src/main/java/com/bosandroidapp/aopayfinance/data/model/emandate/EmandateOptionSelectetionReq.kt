package com.bosandroidapp.aopayfinance.data.model.emandate

import com.google.gson.annotations.SerializedName

data class EmandateOptionSelectetionReq(

	@field:SerializedName("mode")
	val mode: String? = null,

	@field:SerializedName("registrationID")
	val registrationID: String? = null
)
