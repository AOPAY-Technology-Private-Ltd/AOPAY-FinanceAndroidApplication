package com.bosandroidapp.aopayfinance.ui.view.activity.retailer

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.webkit.CookieManager
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebStorage
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.bos.payment.appName.network.RetrofitClient
import com.bosandroidapp.bosmobilefinance.ui.slideshow.ui.view.activity.retailer.cibilreportsfragment.BureauScore.Companion.userScore
import com.bosandroidapp.aopayfinance.R
import com.bosandroidapp.aopayfinance.constant.ConstantClass
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AadharNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AadharVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AccountNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AccountType
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BankIFSCCode
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BankName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BranchName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BrandName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CheckOnlineOrOffline
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CibilResponse
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ClickOnCardLowCibilScore
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustAlternateMobileNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustAlternateMobileOTP
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustAlternateMobileVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustAreaSector
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustCityName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustCode
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustCountry
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustCurrentAddress
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustFirstName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustFlatNo
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustLastName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustMiddleName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPinCode
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPrimaryMobileNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPrimaryMobileVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPrimaryOTP
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustStateName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CusteMailID
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustomerCodeForEnach
import com.bosandroidapp.aopayfinance.constant.ConstantClass.EmiAmount
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ImeiNumber1
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ImeiNumber2
import com.bosandroidapp.aopayfinance.constant.ConstantClass.PanNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.PanNumberVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.PanResponse
import com.bosandroidapp.aopayfinance.constant.ConstantClass.RefAadharTransactionIdNo
import com.bosandroidapp.aopayfinance.constant.ConstantClass.RefAddress
import com.bosandroidapp.aopayfinance.constant.ConstantClass.RefName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.RefRelationShip
import com.bosandroidapp.aopayfinance.constant.ConstantClass.RefmobileNo
import com.bosandroidapp.aopayfinance.constant.ConstantClass.RetailerCodeForEnach
import com.bosandroidapp.aopayfinance.constant.ConstantClass.eMandate
import com.bosandroidapp.aopayfinance.constant.ConstantClass.eMandatepending
import com.bosandroidapp.aopayfinance.constant.ConstantClass.iisAggrementVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.isMandate
import com.bosandroidapp.aopayfinance.constant.ConstantClass.isPannydropVerified
import com.bosandroidapp.aopayfinance.data.enach.ENachStatusReq
import com.bosandroidapp.aopayfinance.data.enach.EnachDateUploadReq
import com.bosandroidapp.aopayfinance.data.repository.AuthRepository
import com.bosandroidapp.aopayfinance.data.repository.DikshifinsureRepository
import com.bosandroidapp.aopayfinance.data.repository.PanRepository
import com.bosandroidapp.aopayfinance.data.repository.OnlineEnachRepository
import com.bosandroidapp.aopayfinance.data.viewModelFactory.CommonViewModelFactory
import com.bosandroidapp.aopayfinance.data.viewModelFactory.DikshifinsureOnlinePGModelFactory
import com.bosandroidapp.aopayfinance.data.viewModelFactory.OnlineEnachViewModelFactory
import com.bosandroidapp.aopayfinance.data.viewModelFactory.PanViewModelFactory
import com.bosandroidapp.aopayfinance.databinding.ActivityRetailerEmandateVerifyPageBinding
import com.bosandroidapp.aopayfinance.internetchecker.BaseActivity
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.CongratulationPage.Companion.loaneCode
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.QRCodePage.Companion.isEnachCancelled
import com.bosandroidapp.aopayfinance.ui.viewmodel.AuthenticationViewModel
import com.bosandroidapp.aopayfinance.ui.viewmodel.DikshifinsureViewModel
import com.bosandroidapp.aopayfinance.ui.viewmodel.OnlineEnachViewModel
import com.bosandroidapp.aopayfinance.ui.viewmodel.PanViewModel
import com.bosandroidapp.aopayfinance.utils.ApiStatus
import com.bosandroidapp.oqmobilefinance.data.upiautomandate.UpiAutoOrderStatusRequest
import com.bosandroidapp.oqmobilefinance.data.upiautomandate.UpiAutoTransactionRequest
import com.google.gson.Gson
import kotlin.math.roundToInt
import kotlin.text.equals

class RetailerEMandateVerifyPage : BaseActivity() {

    lateinit var binding : ActivityRetailerEmandateVerifyPageBinding
    var isEmandateVerified : String= ""
    lateinit var viewModel: AuthenticationViewModel
    lateinit var panViewModel: PanViewModel
    lateinit var dikshifinsureViewModel: DikshifinsureViewModel
    lateinit var onlineEnachViewModel: OnlineEnachViewModel
    lateinit var dialog: Dialog

    var merchandId : String= ""
    var registrationId : String= ""
    private var isStatusCheckInProgress = false



    companion object{
        var webUrl: String? = ""
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRetailerEmandateVerifyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBarsInsets.left, 0, systemBarsInsets.right, systemBarsInsets.bottom)
            WindowInsetsCompat.CONSUMED
        }

        viewModel = ViewModelProvider(this, CommonViewModelFactory(AuthRepository(RetrofitClient.apiInterface)))[AuthenticationViewModel::class.java]

        panViewModel = ViewModelProvider(this, PanViewModelFactory(PanRepository(RetrofitClient.apiInterfacePAN)))[PanViewModel::class.java]

        dikshifinsureViewModel = ViewModelProvider(this, DikshifinsureOnlinePGModelFactory(DikshifinsureRepository(RetrofitClient.apiInterfaceOnlinePG)))[DikshifinsureViewModel::class.java]

        onlineEnachViewModel = ViewModelProvider(this, OnlineEnachViewModelFactory(OnlineEnachRepository(RetrofitClient.apiInterfaceOnlineEnach)))[OnlineEnachViewModel::class.java]


        if(intent.hasExtra(ConstantClass.MarchentOrderID_UPIAUTOPAY)&& intent.hasExtra(ConstantClass.RegistrationID_UPIAUTOPAY))
        {
            merchandId = intent.getStringExtra(ConstantClass.MarchentOrderID_UPIAUTOPAY).toString()
            registrationId = intent.getStringExtra(ConstantClass.RegistrationID_UPIAUTOPAY).toString()
        }


        setDataInWebView()

    }


    fun setDataInWebView() {

        clearWebView(binding.eMandatewebview)

        binding.eMandatewebview.settings.javaScriptEnabled = true
        binding.eMandatewebview.settings.domStorageEnabled = true
        binding.eMandatewebview.settings.databaseEnabled = true
        binding.eMandatewebview.settings.loadsImagesAutomatically  = true
        binding.eMandatewebview.settings.javaScriptCanOpenWindowsAutomatically  = true
        binding.eMandatewebview.settings.setSupportMultipleWindows(true)
        binding.eMandatewebview.settings.allowFileAccess = true
        binding.eMandatewebview.settings.allowContentAccess = true
        binding.eMandatewebview.settings.mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
        // Important for payment-related WebView flows
        if (WebViewFeature.isFeatureSupported(WebViewFeature.PAYMENT_REQUEST)) {

            WebSettingsCompat.setPaymentRequestEnabled(binding.eMandatewebview.settings, true)
            WebSettingsCompat.setHasEnrolledInstrumentEnabled(binding.eMandatewebview.settings, true)
        }

        Log.d("PHONEPE_WEBVIEW", "Payment Request supported = ${
            WebViewFeature.isFeatureSupported(
                WebViewFeature.PAYMENT_REQUEST
            )
        }")

        Log.d("PHONEPE_WEBVIEW", "WebView version = ${WebView.getCurrentWebViewPackage()?.versionName}")

        binding.eMandatewebview.settings.cacheMode = WebSettings.LOAD_DEFAULT

        binding.eMandatewebview.settings.userAgentString = WebSettings.getDefaultUserAgent(this)
        val cookieManager = CookieManager.getInstance()

        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(binding.eMandatewebview, true)

        binding.eMandatewebview.addJavascriptInterface(object {

            @JavascriptInterface
            fun onUrlChange(url: String) {
                Log.d("JS_URL", url)
                try {
                    val uri = Uri.parse(url)

                    if (!merchandId.isNullOrEmpty() && !registrationId.isNullOrEmpty()) {
                        doUpdateUpiAutoMandateStatus()
                    }

                    else{
                        // Get query parameter
                        val transactionId = uri.getQueryParameter("c")

                        Log.d("TRANSACTION_ID", transactionId ?: "null")

                        if (!transactionId.isNullOrEmpty()) {
                            // Call verify API here
                            doUpdateEMandateStatus(transactionId)
                        }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

                
            }
        }, "Android")

        binding.eMandatewebview.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                Log.d("WEBVIEW", "Loaded URL: $url")

                 //  Inject JS AFTER page load
                injectJs(view)

            }
        }

        //  Load URL AFTER setup
        binding.eMandatewebview.loadUrl(webUrl!!)

    }


    fun clearWebView(webView: WebView) {

        webView.apply {
            clearHistory()
            clearCache(true)
            clearFormData()
            clearSslPreferences()

            CookieManager.getInstance().removeAllCookies(null)
            CookieManager.getInstance().flush()

            WebStorage.getInstance().deleteAllData()
        }
    }


    fun injectJs(webView: WebView?) {
        webView?.evaluateJavascript("""
        (function() {

            function notify() {
                Android.onUrlChange(window.location.href);
            }

            var pushState = history.pushState;
            history.pushState = function() {
                pushState.apply(history, arguments);
                notify();
            };

            var replaceState = history.replaceState;
            history.replaceState = function() {
                replaceState.apply(history, arguments);
                notify();
            };

            window.addEventListener('popstate', notify);

            notify(); // initial trigger
        })();
    """.trimIndent(), null)
    }


    // hit api for  online emandate auto pay

    fun doUpdateUpiAutoMandateStatus() {
        if (isStatusCheckInProgress) return
        isStatusCheckInProgress = true
        (this@RetailerEMandateVerifyPage).runOnUiThread {
            hitApiForUpiAutoMandateOrderStatus(registrationId, merchandId)
        }
    }


    fun hitApiForUpiAutoMandateOrderStatus(registrationId: String, merchandId: String) {
        val request = UpiAutoOrderStatusRequest(
            registrationID = registrationId,
            merchantOrderId = merchandId
        )
        Log.d("UpiAutoStatusReq", Gson().toJson(request))

        dikshifinsureViewModel.getUpiAutoMandateOrderStatusRequest(request).observe(this) { resources ->
            when (resources.apiStatus) {
                ApiStatus.SUCCESS -> {
                    isStatusCheckInProgress = false
                    ConstantClass.dialog!!.dismiss()
                    val response = resources.data?.body()
                    Log.d("UpiAutoStatusRes", Gson().toJson(response))

                    if(response?.state!!.toLowerCase().equals("failed",ignoreCase = true)){
                        isEmandateVerified= "No"
                        showingRejectioneMandatePopUp(response.paymentDetails?.filterNotNull()?.firstOrNull()?.rail?.umn ?: "")
                        return@observe
                    }

                    if (response?.state?.toLowerCase().equals("completed", ignoreCase = true) == true) {

                        if (response!!.paymentDetails.isNullOrEmpty()) {
                            // First completed: mandate created, now trigger transaction
                            hitApiForUpiAutoMandateTransaction(registrationId)
                        }
                        else {
                            // Second completed: transaction done
                            val umn = response.paymentDetails?.filterNotNull()?.firstOrNull()?.rail?.umn ?: ""

                            isEmandateVerified = isMandate
                            val uploadReq = EnachDateUploadReq(
                                isEmandateVerified = isEmandateVerified,
                                emAccountType = AccountType,
                                isPannydropVerified = isPannydropVerified,
                                emAccountNumber = AccountNumber,
                                customerCode = CustomerCodeForEnach,
                                retailerCode = RetailerCodeForEnach,
                                loanCode = loaneCode,
                                emBankName = BankName,
                                emIfscCode = BankIFSCCode,
                                emumrn = umn
                            )
                            hitApiForUploadEnachMandateDataResponse(uploadReq, isEmandateVerified)
                        }
                    }

                }
                ApiStatus.ERROR -> {
                    isStatusCheckInProgress = false
                    ConstantClass.dialog!!.dismiss()
                    Toast.makeText(this, resources.message ?: "Status Check Failed", Toast.LENGTH_SHORT).show()
                }
                ApiStatus.LOADING -> {
                    if (!ConstantClass.dialog!!.isShowing) {
                        ConstantClass.OpenPopUpForVeryfyOTP(this)
                    }
                }
            }
        }

    }


    fun hitApiForUpiAutoMandateTransaction(registrationId: String) {
        val emiNumbers = "1" // Defaulting to 1 for mandate creation flow
        val amount = EmiAmount.toDouble().roundToInt()

        val request = UpiAutoTransactionRequest(
            registrationID = registrationId,
            amount = amount,
            eMINumbers = emiNumbers,
            customerCode = CustomerCodeForEnach,
            loanCode = loaneCode
        )
        Log.d("UpiAutoTransReq", Gson().toJson(request))

        dikshifinsureViewModel.getUpiAutoMandateTransactionRequest(request).observe(this) { resources ->
            when (resources.apiStatus) {
                ApiStatus.SUCCESS -> {
                    isStatusCheckInProgress = false
                    ConstantClass.dialog!!.dismiss()
                    val response = resources.data?.body()
                    Log.d("UpiAutoTransRes", Gson().toJson(response))

                    if (!response?.intentUrl.isNullOrEmpty()) {
                        clearWebView(binding.eMandatewebview)
                        merchandId = response?.marchentOrderID ?: ""
                        binding.eMandatewebview.loadUrl(response?.intentUrl!!)
                    }
                    else {
                        Toast.makeText(this, response?.errorMessage ?: "Transaction trigger failed", Toast.LENGTH_SHORT).show()
                    }
                }
                ApiStatus.ERROR -> {
                    isStatusCheckInProgress = false
                    ConstantClass.dialog!!.dismiss()
                    Toast.makeText(this, resources.message ?: "Transaction Failed", Toast.LENGTH_SHORT).show()
                }
                ApiStatus.LOADING -> {
                    if (!ConstantClass.dialog!!.isShowing) {
                        ConstantClass.OpenPopUpForVeryfyOTP(this)
                    }
                }
            }
        }

    }


    fun doUpdateEMandateStatus(eMandateID : String){

        (this@RetailerEMandateVerifyPage).runOnUiThread {
            var request = ENachStatusReq(
                registrationID = if (ConstantClass.CheckOnlineOrOffline == ConstantClass.online) {
                    ConstantClass.PAN_VERIFICATION_REGISTRATION_ID
                } else {
                    ConstantClass.PAN_VERIFICATION_REGISTRATION_ID_OFFLINE
                },
                eMandateID = eMandateID
            )

            hitApiForEMandateStatus(request)
        }
    }


    fun hitApiForEMandateStatus(request: ENachStatusReq) {
        Log.d("eManadateStatusReq", Gson().toJson(request))
        if(ConstantClass.CheckOnlineOrOffline.equals(ConstantClass.offline)) {
            panViewModel.geteMandateSatusRequest(request).observe(this) { resources ->
                resources.let {
                    when (it.apiStatus) {
                        ApiStatus.SUCCESS -> {
                            it.data.let { users ->
                                users!!.body().let { response ->
                                    Log.d("eMandateStatusRes", Gson().toJson(response))

                                    if (ConstantClass.dialog != null && ConstantClass.dialog?.isShowing == true) {
                                        ConstantClass.dialog!!.dismiss()
                                    }

                                    var statusCode = response!!.statusCode
                                    var eMandateStatus = ""

                                    if (response.data!!.customer != null) {
                                        eMandateStatus = response.data.customer!!.accptd!!
                                    }

                                    if (response!!.statusCode.equals("NP000") && eMandateStatus.equals(
                                            eMandate
                                        )
                                    ) {
                                        isEmandateVerified = isMandate
                                        CheckOnlineOrOffline = ""
                                        Toast.makeText(
                                            this,
                                            "ENach Mandate is Active",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        if (!isEmandateVerified.isNullOrBlank()) {

                                            var request = EnachDateUploadReq(
                                                isEmandateVerified = isEmandateVerified,
                                                emAccountType = AccountType,
                                                isPannydropVerified = isPannydropVerified,
                                                emAccountNumber = AccountNumber,
                                                customerCode = CustomerCodeForEnach,
                                                retailerCode = RetailerCodeForEnach,
                                                loanCode = loaneCode,
                                                emBankName = BankName,
                                                emIfscCode = BankIFSCCode,
                                                emumrn = response.data.customer!!.umrn
                                            )

                                            hitApiForUploadEnachMandateDataResponse(
                                                request,
                                                isEmandateVerified
                                            )
                                        }

                                    } else {
                                        if (!eMandateStatus.equals(eMandatepending)) {
                                            isEmandateVerified = "No"
                                            showingRejectioneMandatePopUp(response.data.customer!!.umrn!!)
                                        }
                                    }

                                }

                            }

                        }

                        ApiStatus.ERROR -> {
                            ConstantClass.dialog!!.dismiss()
                            // ✅ Print the full error details
                            Log.e("API_ERROR", "Status: ERROR")
                            Log.e("API_ERROR_CODE", resources.data?.code().toString())
                            Log.e("API_ERROR_MSG", resources.message ?: "Unknown Error")

                            Toast.makeText(
                                this,
                                "Server error occurred (Code: ${resources.data?.code() ?: "Unknown"})",
                                Toast.LENGTH_LONG
                            ).show()

                            // Optional: Handle specific 500 error
                            if (resources.data?.code() == 500) {
                                Log.e("API_ERROR", "Internal Server Error from backend.")
                            }
                        }

                        ApiStatus.LOADING -> {

                        }

                    }

                }

            }
        }
        else{
            panViewModel.geteMandateOnlineSatusRequest(request).observe(this) { resources ->
                resources.let {
                    when (it.apiStatus) {
                        ApiStatus.SUCCESS -> {
                            it.data.let { users ->
                                users!!.body().let { response ->
                                    Log.d("eMandateonlineStatusRes", Gson().toJson(response))

                                    if(ConstantClass.dialog!=null && ConstantClass.dialog!!.isShowing){
                                        ConstantClass.dialog!!.dismiss()
                                    }

                                    var statusCode =  response!!.statusCode
                                    var eMandateStatus =""

                                    if(response.data!!.customer!=null){
                                        eMandateStatus = response.data.customer!!.accptd!!
                                    }

                                    if (response!!.statusCode.equals("NP000")&& eMandateStatus.equals(eMandate)) {
                                        isEmandateVerified= isMandate
                                        CheckOnlineOrOffline =""
                                        Toast.makeText(this, "ENach Mandate is Active", Toast.LENGTH_SHORT).show()

                                        if(!isEmandateVerified.isNullOrBlank()){

                                            var request = EnachDateUploadReq(
                                                isEmandateVerified = isEmandateVerified,
                                                emAccountType = AccountType,
                                                isPannydropVerified = isPannydropVerified,
                                                emAccountNumber = AccountNumber,
                                                customerCode = CustomerCodeForEnach,
                                                retailerCode= RetailerCodeForEnach,
                                                loanCode= loaneCode,
                                                emBankName=BankName,
                                                emIfscCode =BankIFSCCode,
                                                emumrn = response.data.customer!!.umrn
                                            )

                                            hitApiForUploadEnachMandateDataResponse(request,isEmandateVerified)

                                        }

                                    }

                                    else {
                                        if(!eMandateStatus.equals(eMandatepending)){
                                            isEmandateVerified= "No"
                                            showingRejectioneMandatePopUp(response.data.customer!!.umrn!!)
                                        }
                                    }

                                }

                            }

                        }

                        ApiStatus.ERROR -> {
                            ConstantClass.dialog!!.dismiss()
                            // ✅ Print the full error details
                            Log.e("API_ERROR", "Status: ERROR")
                            Log.e("API_ERROR_CODE", resources.data?.code().toString())
                            Log.e("API_ERROR_MSG", resources.message ?: "Unknown Error")

                            Toast.makeText(this@RetailerEMandateVerifyPage, resources.message ?: "Server error occurred", Toast.LENGTH_LONG).show()

                            // Optional: Handle specific 500 error
                            if (resources.data?.code() == 500) {
                                Log.e("API_ERROR", "Internal Server Error from backend.")
                            }
                        }

                        ApiStatus.LOADING -> {

                        }

                    }

                }

            }
        }
    }


    fun  hitApiForUploadEnachMandateDataResponse(request:EnachDateUploadReq,isMandate: String){

        Log.d("EmandateUploadreq", Gson().toJson(request))

        viewModel.UpdateEmandateDetails(request).observe(this){ resources ->
            resources.let {

                when(it.apiStatus){
                    ApiStatus.SUCCESS ->{
                        it.data.let { users ->
                            users!!.body().let { response ->
                                Log.d("EmandateUploadRes", Gson().toJson(response))
                                if(isMandate.equals(ConstantClass.isMandate)){
                                    // success response
                                    startActivity(Intent(this@RetailerEMandateVerifyPage, AppScanInstallPage::class.java))
                                   /* startActivity(Intent(this@RetailerEMandateVerifyPage, CongratulationPage::class.java))
                                    clearData()
                                    finish()*/
                                }
                                else{
                                    isEnachCancelled = true
                                    finish()
                                }

                            }
                        }

                    }
                    ApiStatus.ERROR ->{
                        // ✅ Print the full error details
                        Log.e("API_ERROR", "Status: ERROR")
                        Log.e("API_ERROR_CODE", resources.data?.code().toString())
                        Log.e("API_ERROR_MSG", resources.message ?: "Unknown Error")

                        Toast.makeText(this, "Server error occurred (Code: ${resources.data?.code() ?: "Unknown"})", Toast.LENGTH_LONG).show()

                        // Optional: Handle specific 500 error
                        if (resources.data?.code() == 500) {
                            Log.e("API_ERROR", "Internal Server Error from backend.")
                        }
                    }



                    ApiStatus.LOADING -> {

                    }
                }
            }
        }

    }


    fun clearData() {
        CustFirstName = ""
        CustMiddleName = ""
        CustLastName = ""
        CustPrimaryMobileNumber = ""
        CustPrimaryOTP = ""
        CustPrimaryMobileVerified = ""
        CustAlternateMobileNumber = ""
        CustAlternateMobileOTP = ""
        CustAlternateMobileVerified = ""
        CusteMailID = ""
        CustFlatNo = ""
        CustAreaSector = ""
        CustPinCode = ""
        CustCurrentAddress = ""
        CustStateName = ""
        CustCityName = ""
        CustCountry = ""

        AadharNumber = ""
        PanNumber = ""

        BrandName = ""
        ConstantClass.ModelName = ""
        ConstantClass.ModelVarient = ""
        ConstantClass.ModelColor = ""
        ConstantClass.SellingPrice = ""
        ConstantClass.DownPayment = ""
        ConstantClass.Tenure = ""

        EmiAmount = ""

        ImeiNumber1 = ""
        ImeiNumber2 = ""

        AccountNumber = ""
        BankIFSCCode = ""
        BankName = ""
        AccountType = ""
        BranchName = ""

        RefName = ""
        RefRelationShip = ""
        RefmobileNo = ""
        RefAddress = ""
        ClickOnCardLowCibilScore = ""
        ConstantClass.ClickOnCardDashboard = ""
        CustCode = ""
        CibilResponse = ""
        userScore = 0f
        PanResponse = ""
        PanNumberVerified = ""
        PanNumber = ""
        AadharVerified = ""
        AadharNumber = ""
        CustPrimaryOTP = ""
        CustCode = ""
        iisAggrementVerified = false


    }


    fun showingRejectioneMandatePopUp(emumrn: String){
        dialog = Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.enach_reject_alert)

        dialog!!.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

            statusBarColor = Color.TRANSPARENT
            navigationBarColor = Color.TRANSPARENT
        }

        var Ok = dialog!!.findViewById<Button>(R.id.btnOk)


        Ok.setOnClickListener {

            if(!isEmandateVerified.isNullOrBlank()){

                var request = EnachDateUploadReq(
                    isEmandateVerified = isEmandateVerified,
                    emAccountType = AccountType,
                    isPannydropVerified = isPannydropVerified,
                    emAccountNumber = AccountNumber,
                    customerCode = CustomerCodeForEnach,
                    retailerCode= RetailerCodeForEnach,
                    loanCode= loaneCode,
                    emBankName=BankName,
                    emIfscCode =BankIFSCCode,
                    emumrn = emumrn
                )

                hitApiForUploadEnachMandateDataResponse(request,isEmandateVerified)
            }

            dialog!!.dismiss()

        }

        dialog!!.setCanceledOnTouchOutside(false)

        dialog!!.show()

    }


    override fun onBackPressed() {
        isEmandateVerified= "No"
        showingRejectioneMandatePopUp("")
    }


}