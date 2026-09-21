package com.bosandroidapp.aopayfinance.data.repository

import com.bos.payment.appName.network.ApiInterface
import com.bosandroidapp.aopayfinance.data.enach.EMandateOnlineRequest
import com.bosandroidapp.aopayfinance.data.enach.ENachStatusReq

class OnlineEnachRepository(private val apiInterface: ApiInterface) {

    suspend fun getEMandateOnlineRequest(req: EMandateOnlineRequest) = apiInterface.geteMandateOnlineRequest(req)
    
    suspend fun geteMandateOnlineSatusRequest(req: ENachStatusReq) = apiInterface.geteMandateOnlineSatusRequest(req)

}
