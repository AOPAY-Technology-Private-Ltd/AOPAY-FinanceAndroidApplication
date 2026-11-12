package com.bosandroidapp.aopayfinance.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

import com.bosandroidapp.bosmobilefinance.ui.slideshow.data.model.loginsignup.cibilscore.CibilScoreReq
import com.bosandroidapp.aopayfinance.data.model.loginsignup.verification.AAdhaarDetailesReq
import com.bosandroidapp.aopayfinance.data.repository.CibilRepository
import com.bosandroidapp.aopayfinance.utils.ApiResponse
import kotlinx.coroutines.Dispatchers

class CibilViewModel (private val repository: CibilRepository): ViewModel(){

    fun getCibilReq(req: CibilScoreReq) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            emit(ApiResponse.success(data = repository.getReportsReq(req)))
        }
        catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message?: "Error Occurred!"))
        }
    }


    fun getAAdhaarDetailesReq(req: AAdhaarDetailesReq) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            emit(ApiResponse.success(data = repository.getAadharDetailsReq(req)))
        }
        catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message?: "Error Occurred!"))
        }
    }





}