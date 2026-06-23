package com.bosandroidapp.aopayfinance.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.bosandroidapp.aopayfinance.data.enach.EMandateRequest
import com.bosandroidapp.aopayfinance.data.enach.ENachStatusReq
import com.bosandroidapp.aopayfinance.data.loancharge.LoanChargeReq
import com.bosandroidapp.aopayfinance.data.model.loginsignup.verification.AadharVerificationReq
import com.bosandroidapp.aopayfinance.data.model.loginsignup.verification.PanVerificationReq
import com.bosandroidapp.aopayfinance.data.pennydrop.BankListReq
import com.bosandroidapp.aopayfinance.data.pennydrop.PennyDropCheckStatusRequest
import com.bosandroidapp.aopayfinance.data.pennydrop.PennyDropRequest
import com.bosandroidapp.aopayfinance.data.pg.PGRequestCall
import com.bosandroidapp.aopayfinance.data.repository.AuthRepository
import com.bosandroidapp.aopayfinance.data.repository.PanRepository
import com.bosandroidapp.aopayfinance.utils.ApiResponse
import kotlinx.coroutines.Dispatchers

class PanViewModel(private val repository: PanRepository) : ViewModel() {

    fun getPanVerificationReq(req: PanVerificationReq) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            emit(ApiResponse.success(data = repository.getPanVerificationReq(req)))
        }
        catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message?: "Error Occurred!"))
        }
    }

    fun getBankListReq(req: BankListReq ) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            emit(ApiResponse.success(data = repository.getBankListReq(req)))
        }
        catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message?: "Error Occurred!"))
        }
    }

    fun getpennyDropReq(req: PennyDropRequest) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            emit(ApiResponse.success(data = repository.getpennyDropReq(req)))
        }
        catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message?: "Error Occurred!"))
        }
    }

    fun getpennyDropCheckStatusReq(req: PennyDropCheckStatusRequest) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            emit(ApiResponse.success(data = repository.getpennyDropCheckStatusReq(req)))
        }
        catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message?: "Error Occurred!"))
        }
    }

    fun getEMandateRequestReq(req: EMandateRequest) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            emit(ApiResponse.success(data = repository.getEMandateRequestReq(req)))
        }
        catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message?: "Error Occurred!"))
        }
    }

    fun geteMandateSatusRequest(req: ENachStatusReq) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            emit(ApiResponse.success(data = repository.geteMandateSatusRequest(req)))
        }
        catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message?: "Error Occurred!"))
        }
    }


    fun loanApplyChargesReq(req: LoanChargeReq) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            emit(ApiResponse.success(data = repository.loanApplyChargesReq(req)))
        }
        catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message?: "Error Occurred!"))
        }
    }


    fun getAadharVerificationReq(req: AadharVerificationReq) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            emit(ApiResponse.success(data = repository.getAadharVerificationReq(req)))
        }
        catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message?: "Error Occurred!"))
        }
    }


    fun getPGRequestCall(req: PGRequestCall) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            emit(ApiResponse.success(data = repository.getPGRequestCall(req)))
        }
        catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message?: "Error Occurred!"))
        }
    }

}