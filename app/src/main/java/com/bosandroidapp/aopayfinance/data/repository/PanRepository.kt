package com.bosandroidapp.aopayfinance.data.repository

import com.bos.payment.appName.network.ApiInterface
import com.bosandroidapp.aopayfinance.data.enach.EMandateRequest
import com.bosandroidapp.aopayfinance.data.enach.ENachStatusReq
import com.bosandroidapp.aopayfinance.data.loancharge.LoanChargeReq
import com.bosandroidapp.aopayfinance.data.model.loginsignup.verification.AadharVerificationReq
import com.bosandroidapp.aopayfinance.data.model.loginsignup.verification.PanVerificationReq
import com.bosandroidapp.aopayfinance.data.pennydrop.BankListReq
import com.bosandroidapp.aopayfinance.data.pennydrop.PennyDropCheckStatusRequest
import com.bosandroidapp.aopayfinance.data.pennydrop.PennyDropRequest
import com.bosandroidapp.aopayfinance.data.pg.PGRequestCall

class PanRepository(private val apiInterface: ApiInterface) {

    suspend fun getPanVerificationReq(req: PanVerificationReq) = apiInterface.getPanVarification(req)

    suspend fun getBankListReq(req: BankListReq) = apiInterface.getBankListRequest(req)

    suspend fun getpennyDropReq(req: PennyDropRequest) = apiInterface.pennyDropReq(req)

    suspend fun getpennyDropCheckStatusReq(req: PennyDropCheckStatusRequest) = apiInterface.pennyDropStatus(req)

    suspend fun getEMandateRequestReq(req: EMandateRequest) = apiInterface.geteMandateRequest(req)
    suspend fun geteMandateSatusRequest(req: ENachStatusReq) = apiInterface.geteMandateSatusRequest(req)
    suspend fun loanApplyChargesReq(req: LoanChargeReq) = apiInterface.loanApplyChargesReq(req)

    suspend fun getAadharVerificationReq(req: AadharVerificationReq) = apiInterface.getAadharVarification(req)

    suspend fun getPGRequestCall(req: PGRequestCall) = apiInterface.callPG(req)


   // suspend fun EMandateOnlineRequest(req: EMandateRequest) = apiInterface.geteMandateOnlineRequest(req)

    suspend fun geteMandateOnlineSatusRequest(req: ENachStatusReq) = apiInterface.geteMandateOnlineSatusRequest(req)



}
