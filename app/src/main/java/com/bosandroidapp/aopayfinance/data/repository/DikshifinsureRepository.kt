package com.bosandroidapp.aopayfinance.data.repository


import com.bos.payment.appName.network.ApiInterface
import com.bosandroidapp.aopayfinance.data.model.customerreq.CustomerDeviceLockStatusReq
import com.bosandroidapp.oqmobilefinance.data.pg.GetOrderStatusOnlinePGRequest
import com.bosandroidapp.oqmobilefinance.data.pg.PGOnlineRequestCall
import com.bosandroidapp.oqmobilefinance.data.upiautomandate.UPIMandateRequest
import com.bosandroidapp.oqmobilefinance.data.upiautomandate.UpiAutoOrderStatusRequest
import com.bosandroidapp.oqmobilefinance.data.upiautomandate.UpiAutoTransactionRequest

class DikshifinsureRepository (private val apiInterface: ApiInterface){

     suspend fun getPGRequestCallOnline(req: PGOnlineRequestCall) = apiInterface.callPGOnline(req)

     suspend fun getOrderOnlineStatusPgRequest(req: GetOrderStatusOnlinePGRequest) = apiInterface.getOrderOnlineStatusPgRequest(req)

     suspend fun getUpiMandateOnlineRequest(req: UPIMandateRequest) = apiInterface.getUpiMandateOnlineRequest(req)

     suspend fun getUpiAutoMandateOrderStatusRequest(req: UpiAutoOrderStatusRequest) = apiInterface.getUpiAutoMandateOrderStatusRequest(req)

     suspend fun getUpiAutoMandateTransactionRequest(req: UpiAutoTransactionRequest) = apiInterface.getUpiAutoMandateTransactionRequest(req)

     suspend fun uploadDeviceLockStatusReq(req: CustomerDeviceLockStatusReq) = apiInterface.uploadDeviceLockStatusReq(req)

}