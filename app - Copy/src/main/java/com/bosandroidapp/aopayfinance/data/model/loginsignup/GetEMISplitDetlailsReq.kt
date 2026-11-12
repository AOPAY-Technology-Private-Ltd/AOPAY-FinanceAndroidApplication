package com.bosandroidapp.aopayfinance.data.model.loginsignup

import com.google.gson.annotations.SerializedName

data class GetEMISplitDetlailsReq (
    @SerializedName("brandName")
    var brandName:String,

    @SerializedName("modelName")
    var modelName:String,
    @SerializedName("clientcode")
    var clientCode:String

)





/*data class GetEMISplitDetlailsReq (
    @SerializedName("brandName")
    var brandName:String,

    @SerializedName("modelName")
    var modelName:String,

    @SerializedName("variantName")
    var variantName:String
    ,
    @SerializedName("mrpPrice")
    var mrpPrice:String
    ,
    @SerializedName("avlbColors")
    var avlColors:String

)*/

