package com.bosandroidapp.aopayfinance.data.repository

import com.bosandroidapp.aopayfinance.network.ApiInterface
import com.bosandroidapp.bosmobilefinance.ui.slideshow.data.model.loginsignup.cibilscore.CibilScoreReq
import com.bosandroidapp.aopayfinance.data.model.loginsignup.verification.AAdhaarDetailesReq

class CibilRepository(private val apiInterface: ApiInterface) {

    suspend fun getReportsReq(req: CibilScoreReq) = apiInterface.getcibilscore(req)

    suspend fun getAadharDetailsReq(req: AAdhaarDetailesReq) = apiInterface.getAadharDetails(req)



}