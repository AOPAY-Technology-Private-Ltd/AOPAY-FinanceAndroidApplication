package com.bosandroidapp.aopayfinance.ui.view.activity.retailer

import android.Manifest
import android.R
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.text.InputFilter
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.bos.payment.appName.network.ApiInterface
import com.bos.payment.appName.network.RetrofitClient
import com.bosandroidapp.aopayfinance.constant.ConstantClass
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AadharNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AadharVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AccountNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AccountType
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BankIFSCCode
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BankName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BranchName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BrandName
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
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPhotoPath
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPinCode
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPrimaryMobileNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPrimaryMobileVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustPrimaryOTP
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustStateName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CusteMailID
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustomerCodeForEnach
import com.bosandroidapp.aopayfinance.constant.ConstantClass.DefaulterEmiDebitPending
import com.bosandroidapp.aopayfinance.constant.ConstantClass.DownPayment
import com.bosandroidapp.aopayfinance.constant.ConstantClass.EmiAmount
import com.bosandroidapp.aopayfinance.constant.ConstantClass.FirstName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ImeiNumber1
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ImeiNumber2
import com.bosandroidapp.aopayfinance.constant.ConstantClass.InterestAmt
import com.bosandroidapp.aopayfinance.constant.ConstantClass.InterestRate
import com.bosandroidapp.aopayfinance.constant.ConstantClass.Invoive_Path
import com.bosandroidapp.aopayfinance.constant.ConstantClass.LastName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.LoanCodeForEnach
import com.bosandroidapp.aopayfinance.constant.ConstantClass.LoanEndDate
import com.bosandroidapp.aopayfinance.constant.ConstantClass.LoanStartDate
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ModelColor
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ModelName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ModelVarient
import com.bosandroidapp.aopayfinance.constant.ConstantClass.PanNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.PanNumberVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.PanResponse
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ProcessingFees
import com.bosandroidapp.aopayfinance.constant.ConstantClass.RefAddress
import com.bosandroidapp.aopayfinance.constant.ConstantClass.RefName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.RefRelationShip
import com.bosandroidapp.aopayfinance.constant.ConstantClass.RefmobileNo
import com.bosandroidapp.aopayfinance.constant.ConstantClass.RetailerCodeForEnach
import com.bosandroidapp.aopayfinance.constant.ConstantClass.Tenure
import com.bosandroidapp.aopayfinance.constant.ConstantClass.calculateEmiEndDateFromNow
import com.bosandroidapp.aopayfinance.constant.ConstantClass.createMultipartFromUri
import com.bosandroidapp.aopayfinance.constant.ConstantClass.getCurrentStartDate
import com.bosandroidapp.aopayfinance.constant.ConstantClass.iisAggrementVerified
import com.bosandroidapp.aopayfinance.data.model.SessionOutReq
import com.bosandroidapp.aopayfinance.data.model.ValidateAccessKeyReq
import com.bosandroidapp.aopayfinance.data.model.ValidateSessionRequest
import com.bosandroidapp.aopayfinance.data.model.loginsignup.LoanCreatedReq
import com.bosandroidapp.aopayfinance.data.model.loginsignup.LogoutReq
import com.bosandroidapp.aopayfinance.data.repository.AuthRepository
import com.bosandroidapp.aopayfinance.data.viewModelFactory.CommonViewModelFactory
import com.bosandroidapp.aopayfinance.databinding.ActivityAppScanInstallPageBinding
import com.bosandroidapp.aopayfinance.internetchecker.BaseActivity
import com.bosandroidapp.aopayfinance.localdb.SharedPreference
import com.bosandroidapp.aopayfinance.ui.slideshow.activity.DashBoard
import com.bosandroidapp.aopayfinance.ui.view.activity.ChooseYourRolePage
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.CongratulationPage.Companion.MiddleName
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.CongratulationPage.Companion.loaneCode
import com.bosandroidapp.aopayfinance.ui.viewmodel.AuthenticationViewModel
import com.bosandroidapp.aopayfinance.utils.ApiStatus
import okhttp3.RequestBody.Companion.toRequestBody
import com.bosandroidapp.bosmobilefinance.ui.slideshow.ui.view.activity.retailer.cibilreportsfragment.BureauScore.Companion.userScore
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.math.roundToInt
import kotlin.toString

class AppScanInstallPage : BaseActivity() {
    lateinit var viewModel: AuthenticationViewModel
    lateinit var binding : ActivityAppScanInstallPageBinding
    lateinit var api: ApiInterface
    lateinit var preference: SharedPreference
    private  var invoicePhotoUri: Uri? = null

    companion object{
        var LoanMode=""
        var CustomerPhotoPath=""
    }

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
                // Handle the photoUri, e.g., show image in ImageView
                binding.invoiceImage.visibility= View.VISIBLE
                Glide.with(this)
                    .load(invoicePhotoUri)
                    .centerCrop()
                    .into(binding.invoiceImage)
                binding.tvUploadText.text= "Re-Upload"
                Invoive_Path = invoicePhotoUri
                binding.btnUploadToServer.visibility = View.VISIBLE
        }
        else{
            invoicePhotoUri = null
            binding.invoiceImage.visibility= View.GONE
            binding.btnUploadToServer.visibility = View.GONE
        }

    }


    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            launchCamera()
        } else {
            Toast.makeText(this, "Camera permission is required to upload invoice", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAppScanInstallPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBarsInsets.left, 0, systemBarsInsets.right, systemBarsInsets.bottom)
            WindowInsetsCompat.CONSUMED
        }

        preference = SharedPreference(this)
        api = RetrofitClient.apiInterface
        viewModel = ViewModelProvider(this,
            CommonViewModelFactory(AuthRepository(RetrofitClient.apiInterface))
        )[AuthenticationViewModel::class.java]

        binding.accesstoken.filters = arrayOf(InputFilter.AllCaps())
        binding.accesstoken.inputType = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS

        binding.validatekeylayout.visibility = View.GONE


        setOnClickListner()
        setDataOnUi()

    }


    fun setDataOnUi(){
            binding.username.text = CustFirstName.plus(" ").plus(CustLastName)
            if(CustPhotoPath!=null){
                binding.customerimage.setImageURI(CustPhotoPath)
            }
            else {
                Glide.with(this).load(CustomerPhotoPath).
                placeholder(com.bosandroidapp.aopayfinance.R.drawable.customer).
                error(com.bosandroidapp.aopayfinance.R.drawable.customer).into( binding.customerimage)
            }

    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun setOnClickListner() {

        binding.home.setOnClickListener {
            val intent = Intent(this, DashBoard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }


        binding.back.setOnClickListener {
           // OpenPopUpForVAlert()
        }
        
        
        binding.validatekeylayout.setOnClickListener {
            if (Invoive_Path == null) {
                Toast.makeText(this@AppScanInstallPage, "Upload invoice first!!", Toast.LENGTH_SHORT).show()
            } else if (!binding.accesstoken.text.toString().isNullOrBlank()) {
                hitApiForValidateKey()
            } else {
                Toast.makeText(this@AppScanInstallPage, "Enter access key first!!", Toast.LENGTH_SHORT)
                    .show()
            }
        }


        binding.clicktoopenappqr.setOnClickListener {
            OpenPopUpForQRScanAlert()
        }


        binding.btnUploadInvoice.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                launchCamera()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }


        binding.btnUploadToServer.setOnClickListener {
            if (Invoive_Path != null) {
                hitApiForUploadInvoice()
            } else {
                Toast.makeText(this@AppScanInstallPage, "Select invoice first!!", Toast.LENGTH_SHORT).show()
            }
        }

    }


    fun hitApiForUploadInvoice() {

        if (CustomerCodeForEnach.isNullOrBlank()) {
            Toast.makeText(this, "Customer Code is missing!", Toast.LENGTH_SHORT).show()
            return
        }

        ConstantClass.OpenPopUpForVeryfyOTP(this)

        try {
            val invoicePart = createMultipartFromUri(this, Invoive_Path, "Image_FileName", "InvoivePath")

            if (invoicePart == null) {
                if (ConstantClass.dialog != null &&  ConstantClass.dialog!!.isShowing) {
                    ConstantClass.dialog!!.dismiss()
                }
                Toast.makeText(this, "Failed to process image", Toast.LENGTH_SHORT).show()
                return
            }

            viewModel.uploadInVoiceRequest(
                customerCode = CustomerCodeForEnach,
                columnName = "Invoive_Path",
                newValue = "Invoice",
                imagePart = invoicePart
            ).observe(this) { resources ->
                when (resources.apiStatus) {

                    ApiStatus.SUCCESS -> {
                        if (ConstantClass.dialog != null && ConstantClass.dialog!!.isShowing) {
                            ConstantClass.dialog!!.dismiss()
                        }
                        val response = resources.data?.body()
                        Log.d("UPLOAD_SUCCESS", response.toString())

                        if (response != null && response.statuss == "200") {
                            Toast.makeText(this@AppScanInstallPage, "Invoice uploaded successfully!", Toast.LENGTH_SHORT).show()
                            binding.btnUploadToServer.visibility = View.GONE
                            binding.tvUploadText.text = "Uploaded"
                            binding.validatekeylayout.visibility = View.VISIBLE
                            binding.doneicon.visibility = View.VISIBLE
                            binding.btnUploadInvoice.isEnabled = false
                        } else {
                            val serverMsg = response?.message ?: "Unknown server error"
                            Toast.makeText(this@AppScanInstallPage, "Server Error: $serverMsg", Toast.LENGTH_LONG).show()
                        }
                    }
                    ApiStatus.ERROR -> {
                        if (ConstantClass.dialog != null && ConstantClass.dialog!!.isShowing) {
                            ConstantClass.dialog!!.dismiss()
                        }
                        Toast.makeText(this@AppScanInstallPage, "Upload failed: ${resources.message}", Toast.LENGTH_SHORT).show()
                    }
                    ApiStatus.LOADING -> {
                        // Loader already shown via OpenPopUpForVeryfyOTP
                    }
                }
            }
        } catch (e: Exception) {
            if (ConstantClass.dialog != null &&  ConstantClass.dialog!!.isShowing) {
                ConstantClass.dialog!!.dismiss()
            }
            Toast.makeText(this, "Error processing invoice: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


    private fun handleApiError(responseCode: Int, errorBody: String?) {
        if (ConstantClass.dialog?.isShowing == true) {
            ConstantClass.dialog!!.dismiss()
        }


        val message = when (responseCode) {
            400 -> "Bad request. Please check entered data with code 400."
            401 -> "Session expired. Please login again with code 401."
            403 -> "You are not authorized to perform this action with code 403."
            404 -> "Service not found. Please try again later with code 404."
            500 -> "Server error. Please try after some time with code 500."
            else -> "Something went wrong. Please try again."
        }

        Log.e("API_ERROR", "Code: $responseCode Body: $errorBody")

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


    @SuppressLint("SetTextI18n")
    fun OpenPopUpForQRScanAlert() {
        ConstantClass.dialog = Dialog(this, R.style.Theme_Black_NoTitleBar_Fullscreen)
        ConstantClass.dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        ConstantClass.dialog!!.setContentView(com.bosandroidapp.aopayfinance.R.layout.appdownloadqrlayout)


        ConstantClass.dialog!!.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }


        ConstantClass.dialog!!.setCanceledOnTouchOutside(false)

        val cancel = ConstantClass.dialog!!.findViewById<Button>(com.bosandroidapp.aopayfinance.R.id.btnClose)

        val provisioningQR = ConstantClass.dialog!!.findViewById<ImageView>(com.bosandroidapp.aopayfinance.R.id.qr_code_provising)

        val progressbar = ConstantClass.dialog!!.findViewById<ProgressBar>(com.bosandroidapp.aopayfinance.R.id.progressbar)

        hitApiForDownloadAppUrlLinkQR(provisioningQR,progressbar)

        cancel.setOnClickListener {
            ConstantClass.dialog!!.dismiss()
        }

        ConstantClass.dialog!!.show()

    }


    fun hitApiForDownloadAppUrlLinkQR(qrCodeProvising: ImageView, progressBar: ProgressBar) {
        lifecycleScope.launch {

            progressBar.visibility = View.VISIBLE
            qrCodeProvising.visibility = View.GONE

            try {
                val bitmap = withContext(Dispatchers.IO) {

                    val response = RetrofitClient.apiInterface.getApkUrlLink()

                    if (response!!.isSuccessful && response.body() != null) {

                        response.body()!!.byteStream().use { inputStream ->
                            BitmapFactory.decodeStream(inputStream)
                        }

                    } else {
                        null
                    }
                }

                bitmap?.let {
                    qrCodeProvising.setImageBitmap(it)
                }

            } catch (e: Exception) {

                e.printStackTrace()

            } finally {

                progressBar.visibility = View.GONE
                qrCodeProvising.visibility = View.VISIBLE
            }
        }


    }


    fun hitApiForValidateKey() {
        var keyvalidatereq = ValidateAccessKeyReq(
            apiacessKey = binding.accesstoken.text.toString().trim(),
            clientCode = preference.getStringValue(ConstantClass.ClientCode,"")
        )

        Log.d("keyvalidatereq", Gson().toJson(keyvalidatereq))

        viewModel.getAccessKeyForValidateAPKReq(keyvalidatereq).observe(this) { resources ->
            resources.let {
                when (it.apiStatus) {
                    ApiStatus.SUCCESS -> {

                        it.data?.let { users ->
                            users.body()?.let { response ->
                                Log.d("LoginResponse", Gson().toJson(response))
                                ConstantClass.dialog!!.dismiss()

                                if (response.success!! && response.statusCode == 200) {
                                    binding.accesstoken.isEnabled= false
                                    binding.verifykeyicon.visibility= View.VISIBLE

                                    val firstName = preference.getStringValue(ConstantClass.FirstName, "").orEmpty()
                                    val lastName = preference.getStringValue(ConstantClass.LastName, "").orEmpty()
                                    val safeLastName = if (!lastName.isNullOrBlank() && lastName != "null") lastName else ""
                                    var createdBy = firstName.plus(" ").plus(safeLastName)
                                    var retailercode = preference.getStringValue(ConstantClass.RetailerCode, "")

                                    val startDate = getCurrentStartDate()
                                    val endDate = calculateEmiEndDateFromNow(Tenure.toInt())

                                    var loancreatedreq = LoanCreatedReq(
                                        modetype = "UPDATE",
                                        rid = ConstantClass.LoanRID,
                                        customerCode = CustomerCodeForEnach,
                                        loanAmount = ConstantClass.LoanAmount.toDouble(),
                                        downPayment = DownPayment.toDouble(),
                                        emiAmount = EmiAmount.toDouble(),
                                        tenure = Tenure.toInt(),
                                        interestRate = InterestRate.toDouble(),
                                        startDate = startDate,
                                        endDate = endDate,
                                        imeiNumber = ImeiNumber1,
                                        createdBy = createdBy,
                                        brandname = BrandName,
                                        modelname = ModelName,
                                        variantname = ModelVarient,
                                        avlcolor = ModelColor,
                                        retailerCode = retailercode,
                                        processingFees = ProcessingFees,
                                        interestAmt = InterestAmt,
                                        remarks = "",
                                        recordStatus = ConstantClass.CustomerLoanStatus,
                                        creditScore = userScore.toString(),
                                        validateKey = binding.accesstoken.text.toString(),
                                        defaultEmidebit = DefaulterEmiDebitPending,
                                        sellingPrice = ConstantClass.SellingPrice.toDouble(),
                                        loanMode = LoanMode,
                                        clientCode = preference.getStringValue(ConstantClass.ClientCode, "")
                                    )
                                    hitApiForRetailerCreatedLoan(loancreatedreq!!)
                                }
                                else {
                                    binding.accesstoken.isEnabled= true
                                    binding.verifykeyicon.visibility= View.GONE
                                }

                                Toast.makeText(this@AppScanInstallPage, response.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    ApiStatus.ERROR -> {
                        ConstantClass.dialog!!.dismiss()
                        binding.accesstoken.isEnabled = true
                        Toast.makeText(this@AppScanInstallPage, resources.message ?: "Error occurred", Toast.LENGTH_SHORT).show()
                    }

                    ApiStatus.LOADING -> {
                        ConstantClass.OpenPopUpForVeryfyOTP(this)
                    }
                }

            }

        }

    }

    fun hitApiForRetailerCreatedLoan(requset: LoanCreatedReq) {

        Log.d("customerReq", Gson().toJson(requset))
        viewModel.getRetailerLoanCreatedReq(requset).observe(this) { resources ->
            resources.let {
                when (it.apiStatus) {
                    ApiStatus.SUCCESS -> {
                        it.data?.let { users ->
                            users.body()?.let { response ->
                                Log.d("customerres", Gson().toJson(response))

                                if (response.status?.toLowerCase().equals(ConstantClass.LoanSuccessStatus)) {
                                    LoanMode=""
                                    CongratulationPage.loaneCode = response.data!!.loanCode!!
                                    CongratulationPage.FirstName = CustFirstName
                                    CongratulationPage.MiddleName = CustMiddleName
                                    CongratulationPage.LastName = CustLastName
                                    CustomerCodeForEnach = response.data!!.customerCode!!
                                    LoanCodeForEnach = response.data!!.loanCode!!
                                    RetailerCodeForEnach = response.data!!.retailerCode!!

                                    LoanStartDate = response.data.startDate!!
                                    LoanEndDate = response.data.endDate!!
                                    CustomerPhotoPath=""

                                    startActivity(Intent(this@AppScanInstallPage, CongratulationPage::class.java))
                                    clearData()
                                    finish()

                                }
                                else {
                                    if (ConstantClass.dialog != null && ConstantClass.dialog!!.isShowing) {
                                        ConstantClass.dialog!!.dismiss()
                                    }
                                }

                            }
                        }
                    }

                    ApiStatus.ERROR -> {

                        if (ConstantClass.dialog != null && ConstantClass.dialog!!.isShowing) {
                            ConstantClass.dialog!!.dismiss()
                        }

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


    fun hitApiForLogin() {

        var sessionOutReq = SessionOutReq(
            retailerCode = preference.getStringValue(ConstantClass.RetailerCode, ""),
            clientCode = preference.getStringValue(ConstantClass.ClientCode,"")
        )

        Log.d("SessionOutReq", Gson().toJson(sessionOutReq))
        viewModel.getSessionReq(sessionOutReq).observe(this) { resources ->
            resources.let {
                when (it.apiStatus) {
                    ApiStatus.SUCCESS -> {
                        it.data?.let { users ->
                            users.body()?.let { response ->
                                Log.d("SessionOutResponse", Gson().toJson(response))
                                if (ConstantClass.dialog != null && ConstantClass.dialog!!.isShowing) {
                                    ConstantClass.dialog!!.dismiss()
                                }
                                ConstantClass.checkActiveStatusAndLogout(this@AppScanInstallPage, response.status, preference)
                            }
                        }
                    }

                    ApiStatus.ERROR -> {

                    }

                    ApiStatus.LOADING -> {

                    }
                }
            }
        }


        var request = ValidateSessionRequest(
            preference.getStringValue(ConstantClass.RetailerCode, ""),
            preference.getStringValue(ConstantClass.DEVICEID, ""),
            preference.getStringValue(ConstantClass.FCMTOKEN, "")
        )

        Log.d("validaterequest", Gson().toJson(request))
        viewModel.getSessionExpiredReq(request).observe(this) { resources ->
            resources.let {
                when (it.apiStatus) {
                    ApiStatus.SUCCESS -> {
                        it.data?.let { users ->
                            users.body()?.let { response ->
                                Log.d("validateresp", Gson().toJson(response))
                                if (response.status == 0) {
                                    hitApiForRetailerLogout()
                                }
                            }
                        }
                    }

                    ApiStatus.ERROR -> {

                    }

                    ApiStatus.LOADING -> {

                    }
                }
            }
        }

    }


    fun hitApiForRetailerLogout() {
        var loginRequest = LogoutReq(
            retailerCode = preference.getStringValue(ConstantClass.RetailerCode, ""),
        )

        Log.d("LogoutReq", Gson().toJson(loginRequest))

        viewModel.getLogout(loginRequest).observe(this) { resources ->
            resources.let {
                when (it.apiStatus) {
                    ApiStatus.SUCCESS -> {
                        it.data?.let { users ->
                            users.body()?.let { response ->
                                Log.d("LogoutResponse", Gson().toJson(response))
                                preference.setBooleanValue(ConstantClass.LoggedIn, false)
                                preference.setStringValue(ConstantClass.LoginType, "")
                                ConstantClass.ClickOnCardDashboard = ""
                                val intent = Intent(this@AppScanInstallPage, ChooseYourRolePage::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                                finish()
                            }
                        }
                    }

                    ApiStatus.ERROR -> {

                    }

                    ApiStatus.LOADING -> {

                    }
                }
            }
        }

    }


    override fun onResume() {
        super.onResume()

        hitApiForLogin()

    }


    override fun onBackPressed() {

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
        LoanStartDate = ""
        LoanEndDate = ""

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
        ConstantClass.Invoive_Path = null

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

    private fun createImageFile(): File {
        val fileName = "IMG_${System.currentTimeMillis()}"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDir)
    }

    private fun launchCamera() {
        try {
            val photoFile = createImageFile()
            if (photoFile != null) {
                invoicePhotoUri = FileProvider.getUriForFile(this, "${packageName}.fileprovider", photoFile)
                cameraLauncher.launch(invoicePhotoUri!!)
            } else {
                Toast.makeText(this, "Failed to create image file", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Camera error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }



}
