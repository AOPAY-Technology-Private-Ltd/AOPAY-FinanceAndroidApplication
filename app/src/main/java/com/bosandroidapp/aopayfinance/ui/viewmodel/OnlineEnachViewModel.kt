package com.bosandroidapp.aopayfinance.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bosandroidapp.aopayfinance.data.enach.EMandateOnlineRequest
import com.bosandroidapp.aopayfinance.data.enach.ENachStatusReq
import com.bosandroidapp.aopayfinance.data.repository.OnlineEnachRepository
import com.bosandroidapp.aopayfinance.utils.ApiResponse
import kotlinx.coroutines.Dispatchers

class OnlineEnachViewModel(private val repository: OnlineEnachRepository) : ViewModel() {


    fun getEMandateOnlineRequest(req: EMandateOnlineRequest) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            emit(ApiResponse.success(data = repository.getEMandateOnlineRequest(req)))
        } catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }



    fun geteMandateOnlineSatusRequest(req: ENachStatusReq) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            emit(ApiResponse.success(data = repository.geteMandateOnlineSatusRequest(req)))
        } catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }



}
