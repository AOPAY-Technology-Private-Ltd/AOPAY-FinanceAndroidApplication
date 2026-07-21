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
import com.bosandroidapp.aopayfinance.data.enach.ENachStatusReq
import com.bosandroidapp.aopayfinance.data.enach.EnachDateUploadReq
import com.bosandroidapp.aopayfinance.data.repository.AuthRepository
import com.bosandroidapp.aopayfinance.data.repository.PanRepository
import com.bosandroidapp.aopayfinance.data.viewModelFactory.CommonViewModelFactory
import com.bosandroidapp.aopayfinance.data.viewModelFactory.PanViewModelFactory
import com.bosandroidapp.aopayfinance.databinding.ActivityRetailerEmandateVerifyPageBinding
import com.bosandroidapp.aopayfinance.internetchecker.BaseActivity
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.CongratulationPage.Companion.loaneCode
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.QRCodePage.Companion.isEnachCancelled
import com.bosandroidapp.aopayfinance.ui.viewmodel.AuthenticationViewModel
import com.bosandroidapp.aopayfinance.ui.viewmodel.PanViewModel
import com.bosandroidapp.aopayfinance.utils.ApiStatus
import com.google.gson.Gson

class RetailerEMandateVerifyPage : BaseActivity() {

    lateinit var binding : ActivityRetailerEmandateVerifyPageBinding
    var isEmandateVerified : String= ""
    var isPannydropVerified : String= "Yes"
    lateinit var viewModel: AuthenticationViewModel
    lateinit var panViewModel: PanViewModel

    lateinit var dialog: Dialog


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
        panViewModel = ViewModelProvider(this,
            PanViewModelFactory(PanRepository(RetrofitClient.apiInterfacePAN))
        )[PanViewModel::class.java]

        setDataInWebView()
    }


    fun setDataInWebView() {

        binding.eMandatewebview.settings.javaScriptEnabled = true
        binding.eMandatewebview.settings.domStorageEnabled = true

        binding.eMandatewebview.addJavascriptInterface(object {

            @JavascriptInterface
            fun onUrlChange(url: String) {
                Log.d("JS_URL", url)
                try {
                    val uri = Uri.parse(url)

                    // Get query parameter
                    val transactionId = uri.getQueryParameter("c")

                    Log.d("TRANSACTION_ID", transactionId ?: "null")

                    if (!transactionId.isNullOrEmpty()) {
                        // Call verify API here
                        doUpdateEMandateStatus(transactionId)
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


                 // ✅ Inject JS AFTER page load
                injectJs(view)
            }
        }

        clearWebView(binding.eMandatewebview)
        // ✅ Load URL AFTER setup
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



    fun doUpdateEMandateStatus(eMandateID : String){

        (this@RetailerEMandateVerifyPage).runOnUiThread {
            var request = ENachStatusReq(
                registrationID = ConstantClass.PAN_VERIFICATION_REGISTRATION_ID,
                eMandateID = eMandateID
            )

            hitApiForEMandateStatus(request)
        }
    }


    fun hitApiForEMandateStatus(request: ENachStatusReq) {
        Log.d("eManadateStatusReq", Gson().toJson(request))
        panViewModel.geteMandateSatusRequest(request).observe(this) { resources ->
            resources.let {
                when (it.apiStatus) {
                    ApiStatus.SUCCESS -> {
                        it.data.let { users ->
                            users!!.body().let { response ->
                                Log.d("eMandateStatusRes", Gson().toJson(response))

                                if(ConstantClass.dialog!=null && ConstantClass.dialog.isShowing){
                                    ConstantClass.dialog.dismiss()
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
                                            emIfscCode =BankIFSCCode
                                        )

                                        hitApiForUploadEnachMandateDataResponse(request,isEmandateVerified)
                                    }

                                }

                                else {
                                    if(!eMandateStatus.equals(eMandatepending)){
                                        isEmandateVerified= "No"
                                        showingRejectioneMandatePopUp()
                                    }
                                }

                            }

                        }

                    }

                    ApiStatus.ERROR -> {
                        ConstantClass.dialog.dismiss()
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


    fun  hitApiForUploadEnachMandateDataResponse(request:EnachDateUploadReq,isMandate: String){

        Log.d("EmandateUploadreq", Gson().toJson(request))

        viewModel.UpdateEmandateDetails(request).observe(this){
                resources ->
            resources.let {

                when(it.apiStatus){
                    ApiStatus.SUCCESS ->{
                        it.data.let { users ->
                            users!!.body().let { response ->
                                Log.d("EmandateUploadRes", Gson().toJson(response))
                                if(isMandate.equals(ConstantClass.isMandate)){
                                    startActivity(Intent(this@RetailerEMandateVerifyPage, CongratulationPage::class.java))
                                    clearData()
                                    finish()
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


    fun showingRejectioneMandatePopUp(){
        dialog = Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.enach_reject_alert)

        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

            statusBarColor = Color.TRANSPARENT
            navigationBarColor = Color.TRANSPARENT
        }

        var Ok = dialog.findViewById<Button>(R.id.btnOk)


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
                    emIfscCode =BankIFSCCode
                )

                hitApiForUploadEnachMandateDataResponse(request,isEmandateVerified)
            }

            dialog.dismiss()

        }

        dialog.setCanceledOnTouchOutside(false)

        dialog.show()

    }


    override fun onBackPressed() {
        isEmandateVerified= "No"
        showingRejectioneMandatePopUp()
    }


}