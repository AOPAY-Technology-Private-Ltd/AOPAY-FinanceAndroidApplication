package com.bosandroidapp.aopayfinance.ui.view.activity.retailer.reports

import android.R
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bos.payment.appName.network.RetrofitClient
import com.bosandroidapp.aopayfinance.constant.ConstantClass
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AadhaarResponse
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AadharNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AccountHolderName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AccountNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.AccountType
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BankID
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BankIFSCCode
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BankName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BranchAddress
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BranchName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.BrandName
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CibilResponse
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CreatedByCustomerShortCut
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustAlternateMobileNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustAreaSector
import com.bosandroidapp.aopayfinance.constant.ConstantClass.CustCityName
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
import com.bosandroidapp.aopayfinance.constant.ConstantClass.DownPayment
import com.bosandroidapp.aopayfinance.constant.ConstantClass.EmiAmount
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ImeiNumber1
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ImeiNumber2
import com.bosandroidapp.aopayfinance.constant.ConstantClass.InterestAmt
import com.bosandroidapp.aopayfinance.constant.ConstantClass.InterestRate
import com.bosandroidapp.aopayfinance.constant.ConstantClass.IsRetailerAggrementVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.LoanEndDate
import com.bosandroidapp.aopayfinance.constant.ConstantClass.LoanRID
import com.bosandroidapp.aopayfinance.constant.ConstantClass.LoanStartDate
import com.bosandroidapp.aopayfinance.constant.ConstantClass.LoanStatus
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
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ReferenceAadharNumber
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ReferenceAadharVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.RefmobileNo
import com.bosandroidapp.aopayfinance.constant.ConstantClass.RetailerCodeForEnach
import com.bosandroidapp.aopayfinance.constant.ConstantClass.SellingPrice
import com.bosandroidapp.aopayfinance.constant.ConstantClass.Tenure
import com.bosandroidapp.aopayfinance.constant.ConstantClass.ToBePaidAmount
import com.bosandroidapp.aopayfinance.constant.ConstantClass.UPIMandate
import com.bosandroidapp.aopayfinance.constant.ConstantClass.downloadImageToTemp
import com.bosandroidapp.aopayfinance.constant.ConstantClass.isAccessKeyVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.isAggrementVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.isEmandateVerified
import com.bosandroidapp.aopayfinance.constant.ConstantClass.isPannydropVerified
import com.bosandroidapp.aopayfinance.data.enach.EMandateRequest
import com.bosandroidapp.aopayfinance.data.enach.EMandateOnlineRequest
import com.bosandroidapp.aopayfinance.data.enach.EnachDateUploadReq
import com.bosandroidapp.aopayfinance.data.model.emandate.EmandateOptionSelectetionReq
import com.bosandroidapp.aopayfinance.data.model.emandate.EmandateSelectDataItem
import com.bosandroidapp.aopayfinance.data.model.staggingdatamodel.CustomerStepDataItem
import com.bosandroidapp.aopayfinance.data.model.staggingdatamodel.ShortCutCustomerRequest
import com.bosandroidapp.aopayfinance.data.pennydrop.BankListReq
import com.bosandroidapp.aopayfinance.data.repository.AuthRepository
import com.bosandroidapp.aopayfinance.data.repository.DikshifinsureRepository
import com.bosandroidapp.aopayfinance.data.repository.PanRepository
import com.bosandroidapp.aopayfinance.data.repository.OnlineEnachRepository
import com.bosandroidapp.aopayfinance.data.viewModelFactory.CommonViewModelFactory
import com.bosandroidapp.aopayfinance.data.viewModelFactory.DikshifinsureOnlinePGModelFactory
import com.bosandroidapp.aopayfinance.data.viewModelFactory.OnlineEnachViewModelFactory
import com.bosandroidapp.aopayfinance.data.viewModelFactory.PanViewModelFactory
import com.bosandroidapp.aopayfinance.databinding.ActivityCustomerListForCreatingShortCutLoanProcessPageBinding
import com.bosandroidapp.aopayfinance.localdb.SharedPreference
import com.bosandroidapp.aopayfinance.ui.slideshow.activity.DashBoard
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.AppScanInstallPage
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.AppScanInstallPage.Companion.CustomerPhotoPath
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.AppScanInstallPage.Companion.LoanMode
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.CongratulationPage.Companion.loaneCode
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.IMEIDetailsPage
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.MobileSelectionActivity
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.NewCustomerRegistrationPage
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.PaymentInformation
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.QRCodePage
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.QRCodePage.Companion.isEnachCancelled
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.RetailerEMandateVerifyPage
import com.bosandroidapp.aopayfinance.ui.view.activity.retailer.RetailerEMandateVerifyPage.Companion.webUrl
import com.bosandroidapp.aopayfinance.ui.view.adapter.CustomerShortcutLoanAdapter
import com.bosandroidapp.aopayfinance.ui.view.adapter.EmandateOptionAdapter
import com.bosandroidapp.aopayfinance.ui.viewmodel.AuthenticationViewModel
import com.bosandroidapp.aopayfinance.ui.viewmodel.DikshifinsureViewModel
import com.bosandroidapp.aopayfinance.ui.viewmodel.OnlineEnachViewModel
import com.bosandroidapp.aopayfinance.ui.viewmodel.PanViewModel
import com.bosandroidapp.aopayfinance.utils.ApiStatus
import com.bosandroidapp.bosmobilefinance.ui.slideshow.ui.view.activity.retailer.cibilreportsfragment.BureauScore.Companion.userScore
import com.bosandroidapp.oqmobilefinance.data.model.CustomerSearchForShortCutLoanRequest
import com.bosandroidapp.oqmobilefinance.data.model.CustomerShortCutDataItem
import com.bosandroidapp.oqmobilefinance.data.upiautomandate.UPIMandateRequest
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.isNotEmpty
import kotlin.math.roundToInt

class CustomerListForCreatingShortCutLoanProcessPage : AppCompatActivity() {
    private lateinit var preference: SharedPreference
    private lateinit var viewModel: AuthenticationViewModel
    private lateinit var panViewModel: PanViewModel
    lateinit var dikshifinsureViewModel: DikshifinsureViewModel
    lateinit var onlineEnachViewModel: OnlineEnachViewModel
    var bankList = mutableListOf<Pair<String, Int>>()
    private var customerList: MutableList<CustomerStepDataItem> = mutableListOf()

    private var FilterReportDataList: MutableList<CustomerStepDataItem> = mutableListOf()
    private lateinit var customerAdapter: CustomerShortcutLoanAdapter
    private lateinit var binding: ActivityCustomerListForCreatingShortCutLoanProcessPageBinding


    private var selectedAuthType: String = ""
    private var emandateSelectList: MutableList<Pair<String, List<EmandateSelectDataItem>>?> = mutableListOf()

    lateinit var dialog: Dialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomerListForCreatingShortCutLoanProcessPageBinding.inflate(layoutInflater)
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
        preference = SharedPreference(this)


        dikshifinsureViewModel = ViewModelProvider(this, DikshifinsureOnlinePGModelFactory(DikshifinsureRepository(RetrofitClient.apiInterfaceOnlinePG))
        )[DikshifinsureViewModel::class.java]


        setonclickListner()
        setupRecyclerView()
        setview()


    }


    fun setonclickListner() {

        binding.searcCustomer.addTextChangedListener(object : TextWatcher {


            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }


            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val search = s.toString().trim()


            }

            override fun afterTextChanged(s: Editable?) {
                val search = s.toString().trim()

                if(search.isNotEmpty()){
                    var filterList = customerList.filter {
                                it.fullName!!.lowercase().contains(search.lowercase()) ||
                                it.customerCode!!.lowercase().contains(search.lowercase())||
                                it.mobileNumber!!.lowercase().contains(search.lowercase())

                    } as MutableList<CustomerStepDataItem>
                    customerDataAccordingToStatus(filterList)
                }
            }

        })


        binding.back.setOnClickListener {
            finish()
        }


    }


    fun hitApiForCustomerwiseDetails(customerCode: String?,step: String?) {
        var req = CustomerSearchForShortCutLoanRequest(
            searchText = customerCode
        )

        Log.d("SearchReq", Gson().toJson(req))

        viewModel.getCustomerDataForSearch(req).observe(this) { resource ->
            resource.let {
                when (it.apiStatus) {
                    ApiStatus.SUCCESS -> {
                        ConstantClass.dialog!!.dismiss()
                        it.data?.let { users ->
                            if (users.isSuccessful) {
                                users.body()?.let { response ->
                                    if (response.code == 200 && response.status == true) {
                                        var getCustomerData = response.data!![0]
                                        handleCustomerClick(getCustomerData,step)
                                    }
                                    else {
                                        Toast.makeText(this@CustomerListForCreatingShortCutLoanProcessPage, response.message, Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } else {
                                Toast.makeText(this@CustomerListForCreatingShortCutLoanProcessPage, "Error: ${users.message()}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    ApiStatus.ERROR -> {
                        ConstantClass.dialog!!.dismiss()
                        Toast.makeText(this@CustomerListForCreatingShortCutLoanProcessPage, resource.message ?: "Error occurred", Toast.LENGTH_SHORT).show()
                    }

                    ApiStatus.LOADING -> {
                        ConstantClass.OpenPopUpForVeryfyOTP(this)
                    }
                }
            }
        }

    }



    fun setview() {
        val adapter = ArrayAdapter.createFromResource(this, com.bosandroidapp.aopayfinance.R.array.customershortcutlist, com.bosandroidapp.aopayfinance.R.layout.mobilenamelayout)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.reporttype.adapter = adapter

        binding.reporttype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                val selectedItem = parent.getItemAtPosition(position).toString()

                if(selectedItem=="All"){
                    hitApiForGetReports()
                    binding.searcCustomer.setText("")
                }
                else {
                    var filterList = customerList.filter {
                        it.customerStatus!!.lowercase().contains(selectedItem.lowercase())
                    } as MutableList<CustomerStepDataItem>
                    customerDataAccordingToStatus(filterList)
                }


            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // nothing
            }
        }

    }


    private fun setupRecyclerView() {
        customerAdapter = CustomerShortcutLoanAdapter(customerList, this) { item ->

            var customer = item.customerCode
            var step = item.currentStep

            if(step.equals("8")){
                return@CustomerShortcutLoanAdapter
            }

            hitApiForCustomerwiseDetails(customer,step)
        }
        binding.showCustomerreports.layoutManager = LinearLayoutManager(this)
        binding.showCustomerreports.adapter = customerAdapter
    }


    private fun handleCustomerClick(item: CustomerShortCutDataItem, step : String?) {

        val customerDetails = item.customerDetails
        val productDetails = item.productDetails
        val bankDetails = item.bankDetails
        val eMandateDetails = item.eMandateDetails
        val referenceDetails = item.referenceDetails
        val imeiDetails = item.imeiDetails
        val createLoanDetails = item.createLoanDetails
        val invoiceAndAppVerification = item.invoiceAndAppVerification

            customerDetails?.let { customerDetails->

                val image = ConstantClass.BASE_URL_IMAGE + (customerDetails.custPhotoPath ?: "")

                lifecycleScope.launch(Dispatchers.IO) {

                    val imageFile = downloadImageToTemp(
                        this@CustomerListForCreatingShortCutLoanProcessPage,
                        image
                    )

                    withContext(Dispatchers.Main) {
                        if (imageFile != null) {
                            var filepath = imageFile.absolutePath
                            Log.d("IMAGE", "File saved: ${imageFile.absolutePath}")
                            Log.d("IMAGE", "Exists: ${imageFile.exists()}")
                        } else {
                            Log.e("IMAGE", "Download failed")
                        }
                    }

                }

                CustomerPhotoPath = image
                CustomerCodeForEnach = customerDetails.customerCode ?: ""
                preference.setStringValue(ConstantClass.CustomerCode, CustomerCodeForEnach)
                CustFirstName = customerDetails.firstName ?: ""
                CustMiddleName = customerDetails.middleName ?: ""
                AccountHolderName = "${customerDetails.firstName ?: ""} ${customerDetails.lastName ?: ""}"
                CustLastName = customerDetails.lastName ?: ""
                CustPrimaryMobileNumber = customerDetails.primaryMobileNumber ?: ""
                CustPrimaryOTP = customerDetails.primaryOTP ?: ""
                CustPrimaryMobileVerified = customerDetails.primaryMobileVerified ?: ""
                CustAlternateMobileNumber = customerDetails.alternateMobileNumber ?: ""
                CusteMailID = customerDetails.eMailID ?: ""
                CustFlatNo = customerDetails.flatNo ?: ""
                CustAreaSector = customerDetails.aearSector ?: ""
                CustPinCode = customerDetails.pinCode ?: ""
                CustCurrentAddress = customerDetails.currentAddress ?: ""
                CustStateName = customerDetails.stateName ?: ""
                CustCityName = customerDetails.cityName ?: ""
                CustCountry = customerDetails.country ?: ""
                AadharNumber = customerDetails.aadharNumber ?: ""
                ConstantClass.AadharVerified = customerDetails.aadharNumberVerified ?: ""
                PanNumber = customerDetails.panNumber ?: ""
                PanNumberVerified = customerDetails.panNumberVerified ?: ""
                CreatedByCustomerShortCut = customerDetails.createdBy ?: ""
                userScore = customerDetails.cibilScore?.trim()?.toFloatOrNull() ?: 0f
                isAggrementVerified = customerDetails.isAggrementVerified?.toString() ?: "no"
                PanResponse = customerDetails.panApiResponse ?: ""
                AadhaarResponse = customerDetails.aadhaarApiResponse ?: ""
                CibilResponse = customerDetails.cibilApiResponse ?: ""
                RetailerCodeForEnach = customerDetails.retailerCode ?: ""
            }

            productDetails?.let { productDetails->
                BrandName = productDetails.brandName ?: ""
                ModelName = productDetails.modelName ?: ""
                ModelVarient = productDetails.modelVariant ?: ""
                ModelColor = productDetails.color ?: ""
                SellingPrice = productDetails.sellingPrice ?: ""
                DownPayment = productDetails.downPayment ?: ""
                Tenure = productDetails.tenure ?: ""
                EmiAmount = productDetails.emiAmount ?: ""
                ConstantClass.LoanAmount = productDetails.loanAmount?.trim()?.toDoubleOrNull() ?: 0.0
                InterestRate = productDetails.interestRate ?: ""
                ProcessingFees = productDetails.processingFees ?: ""
                InterestAmt = productDetails.interestAmt ?: ""
                val dp = DownPayment.toDoubleOrNull() ?: 0.0
                val pf = ProcessingFees.toDoubleOrNull() ?: 0.0
                val toBePaidNow = dp + pf
                ToBePaidAmount = "%.2f".format(toBePaidNow)
            }

            bankDetails?.let { bankDetails->
                AccountNumber = bankDetails.accountNumber ?: ""
                BankIFSCCode = bankDetails.bankIFSCCode ?: ""
                BankName = bankDetails.bankName ?: ""
                AccountType = bankDetails.accountType ?: ""
                BranchName = bankDetails.branchName ?: ""
                BranchAddress = bankDetails.branchAddress ?: ""
                isPannydropVerified = bankDetails.isPannydropVerified ?: ""
            }

            eMandateDetails?.let { eMandateDetails->
                UPIMandate = eMandateDetails.upiMandate ?: ""
            }

            referenceDetails?.let { referenceDetails->
                RefName = referenceDetails.refName ?: ""
                RefRelationShip = referenceDetails.refRelationShip ?: ""
                RefmobileNo = referenceDetails.refmobileNo ?: ""
                RefAddress = referenceDetails.refAddress ?: ""
                ReferenceAadharVerified = referenceDetails.isrefKycVerified ?: ""
                ReferenceAadharNumber = referenceDetails.refAdhaarNumber ?: ""
            }

            imeiDetails?.let { imeiDetails->
                ImeiNumber1 = imeiDetails.imeiNumber1 ?: ""
                ImeiNumber2 = imeiDetails.imeiNumber2 ?: ""
                IsRetailerAggrementVerified = imeiDetails.isRetailerAggrementVerified?.toString() ?: "no"
            }

            if (createLoanDetails != null && !(createLoanDetails.loanCode.isNullOrBlank())) {
                LoanMode = createLoanDetails.loanMode ?: ""
                LoanStatus = createLoanDetails.loanStatus ?: ""
                LoanRID = createLoanDetails.loanRID?.takeIf { it.isNotBlank() }?.toIntOrNull() ?: 0
                loaneCode = createLoanDetails.loanCode ?: ""
                ConstantClass.DefaultEmidebit = createLoanDetails.defaulterEmiDebit ?: ""
                if (createLoanDetails.loanStartDate != null && createLoanDetails.loanEndDate != null) {
                    LoanStartDate = createLoanDetails.loanStartDate ?: ""
                    LoanEndDate = createLoanDetails.loanEndDate ?: ""
                }
                else {
                    LoanStartDate = ""
                    LoanEndDate = ""
                }
                isEmandateVerified = createLoanDetails.isEmandateVerified ?: ""
                ConstantClass.CheckOnlineOrOffline = createLoanDetails.loanMode ?: ConstantClass.online
            }

            invoiceAndAppVerification?.let { invoiceAndAppVerification->
                isAccessKeyVerified = invoiceAndAppVerification.isAccessKeyVerified ?: ""
            }

            if (createLoanDetails?.loanCode.isNullOrBlank()) {
                showOnlineOfflineDialog(item, step ?: "")
            }

            else {
                if(LoanMode.isNotEmpty()){
                    proceedWithLoanLogic(item,step ?: "")
                }else {
                    Toast.makeText(this@CustomerListForCreatingShortCutLoanProcessPage, "Loan mode not found", Toast.LENGTH_SHORT).show()
                }

            }

    }



    private fun showOnlineOfflineDialog(item: CustomerShortCutDataItem,step : String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(com.bosandroidapp.aopayfinance.R.layout.dialog_online_offline)

        dialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        dialog.setCanceledOnTouchOutside(false)

        val radioGroup = dialog.findViewById<RadioGroup>(com.bosandroidapp.aopayfinance.R.id.radioGroup)
        val btnCancel = dialog.findViewById<Button>(com.bosandroidapp.aopayfinance.R.id.btnCancel)
        val btnOk = dialog.findViewById<Button>(com.bosandroidapp.aopayfinance.R.id.btnOk)


        btnCancel.setOnClickListener {
            dialog.dismiss()
        }


        btnOk.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Please select mode", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            ConstantClass.CheckOnlineOrOffline = if (selectedId == com.bosandroidapp.aopayfinance.R.id.rbOnline) {
                ConstantClass.online
            }
            else {
                ConstantClass.offline
            }

            dialog.dismiss()

            navigateToNextStep(item,step)
        }

        dialog.show()
    }



    private fun navigateToNextStep(item: CustomerShortCutDataItem, step: String) {
        var customerDetails = item.customerDetails


        when(step) {
            "0"-> {
                startActivity(Intent(this, NewCustomerRegistrationPage::class.java))
            }
            // Step 1: Mobile Selection (Brand/EMI)
            "1" -> {
                startActivity(Intent(this, MobileSelectionActivity::class.java))
            }


            // Step 2: Payment Info - Bank/Pennydrop (EMandate is blank)
           "2" -> {
                startActivity(Intent(this, PaymentInformation::class.java).apply {
                    putExtra("EMandate", "")
                })
            }


            // Step 3: Payment Info - E-Mandate (Before Loan Created)
            "3"  -> {
                startActivity(Intent(this, PaymentInformation::class.java).apply {
                    putExtra("EMandate", "NO")
                })
            }


            // Step 4: Payment Info - Reference (Before Loan Created)
            "4"-> {
                startActivity(Intent(this, PaymentInformation::class.java).apply {
                    putExtra("EMandate", "Yes")
                })
            }


            // Step 5: IMEI Details
           "5"-> {
                startActivity(Intent(this, IMEIDetailsPage::class.java))
            }


            // Step 6: Create Loan (QR Code)
            "6"  -> {
                startActivity(Intent(this, QRCodePage::class.java))
            }

            else -> {
                Toast.makeText(this@CustomerListForCreatingShortCutLoanProcessPage,"Customer ${customerDetails!!.customerCode} data is missing!!",
                    Toast.LENGTH_SHORT).show()
            }

        }

    }



    private fun ShowPopUpForEnachProcess(item: CustomerShortCutDataItem) {
        val dialog = Dialog(this, com.bosandroidapp.aopayfinance.R.style.FullScreenDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(com.bosandroidapp.aopayfinance.R.layout.dialog_enach_process)
        dialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.setCanceledOnTouchOutside(false)
        val nextLayout = dialog.findViewById<LinearLayout>(com.bosandroidapp.aopayfinance.R.id.nextlayout)
        val accountnumber = dialog.findViewById<TextView>(com.bosandroidapp.aopayfinance.R.id.accountnumber)
        val banificeryName = dialog.findViewById<TextView>(com.bosandroidapp.aopayfinance.R.id.banificeryName)
        val ifsccode = dialog.findViewById<TextView>(com.bosandroidapp.aopayfinance.R.id.ifsccode)
        val bankname = dialog.findViewById<TextView>(com.bosandroidapp.aopayfinance.R.id.bankname)
        val branchname = dialog.findViewById<TextView>(com.bosandroidapp.aopayfinance.R.id.branchname)
        val branchaddress = dialog.findViewById<TextView>(com.bosandroidapp.aopayfinance.R.id.branchaddress)
        val acounttype = dialog.findViewById<TextView>(com.bosandroidapp.aopayfinance.R.id.acounttype)
        val back = dialog.findViewById<ImageView>(com.bosandroidapp.aopayfinance.R.id.back)


        var customerDetails = item.customerDetails
        var productDetails = item.productDetails
        var bankDetails = item.bankDetails
        var eMandateDetails = item.eMandateDetails
        var referenceDetails = item.referenceDetails
        var imeiDetails = item.imeiDetails
        var createLoanDetails = item.createLoanDetails

        accountnumber.text = AccountNumber
        banificeryName.text = "${customerDetails!!.firstName} ${customerDetails!!.lastName}"
        ifsccode.text = BankIFSCCode
        bankname.text = BankName
        branchname.text = BranchName
        branchaddress.text = BranchAddress
        acounttype.text = AccountType

        if (bankList.isNotEmpty()) {

            BankID = bankList.find { it.first == BankName }?.second!!
            Log.d("FetchBankList", Gson().toJson(bankList))
        }

        nextLayout.setOnClickListener {
            if (banificeryName.text.toString().isEmpty()) {
                Toast.makeText(this@CustomerListForCreatingShortCutLoanProcessPage, "Please enter banificery name", Toast.LENGTH_SHORT).show()
            } 
            else {
                val startDate = ConstantClass.formatToYYYYMMDD(LoanStartDate)
                val endDate = ConstantClass.formatToYYYYMMDD(LoanEndDate)
                val emiAmountVal = productDetails!!.emiAmount?.toDoubleOrNull()?.roundToInt() ?: 0

                val request = EMandateRequest(
                    categoryID = 7,
                    collectionAmount = emiAmountVal,
                    collectCollectionUntilCancle = false,
                    seqType = "RCUR",
                    iFSCCode = bankDetails!!.bankIFSCCode,
                    frequncy = "MNTH",
                    registrationID = ConstantClass.PAN_VERIFICATION_REGISTRATION_ID_OFFLINE,
                    accountHolderName = "${customerDetails.firstName} ${customerDetails.lastName}",
                    finalCollectionDate = endDate,
                    loanNo = createLoanDetails!!.loanCode,
                    accountType = bankDetails!!.accountType,
                    emailAddress = customerDetails.eMailID ?: CusteMailID,
                    firstCollectionDate = startDate,
                    mobileNumber = customerDetails.primaryMobileNumber,
                    bankAccountNumberConfirmation = bankDetails.accountNumber,
                    addIn2 = BranchAddress,
                    addIn3 = "",
                    debitType = true,
                    teleNumber = "",
                    authType = "",
                    bankID = BankID,
                    bankAccountNumber = bankDetails.accountNumber
                )
                hitApiForEnach(request, item, true)
            }
        }

        back.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }



    fun hitApiForBankList(item: CustomerShortCutDataItem) {
        bankList.clear()

        var req = BankListReq(
            registrationID = if (ConstantClass.CheckOnlineOrOffline == ConstantClass.online) {
                ConstantClass.PAN_VERIFICATION_REGISTRATION_ID
            } else {
                ConstantClass.PAN_VERIFICATION_REGISTRATION_ID_OFFLINE
            }
        )
        Log.d("BankListReq", Gson().toJson(req))

        panViewModel.getBankListReq(req).observe(this) { resources ->
            resources.let {
                when (it.apiStatus) {
                    ApiStatus.SUCCESS -> {
                        it.data.let { users ->
                            users!!.body().let { response ->

                                if (response!!.status!!.toLowerCase().equals("false")) {
                                    ConstantClass.dialog!!.dismiss()
                                    Toast.makeText(this@CustomerListForCreatingShortCutLoanProcessPage,
                                        response.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                response?.data?.banks?.forEach {
                                    bankList.add(Pair(it!!.name!!, it.id) as Pair<String, Int>)
                                }

                                if (bankList.isNotEmpty()) {
                                    ConstantClass.dialog!!.dismiss()
                                    ShowPopUpForEnachProcess(item)
                                }

                                Log.d("List", Gson().toJson(response?.data?.banks))

                            }

                        }

                    }

                    ApiStatus.ERROR -> {
                        ConstantClass.dialog!!.dismiss()
                        Toast.makeText(
                            this@CustomerListForCreatingShortCutLoanProcessPage,
                            resources.message ?: "Error occurred",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    ApiStatus.LOADING -> {
                        ConstantClass.OpenPopUpForVeryfyOTP(this)
                    }

                }

            }

        }


    }



    private fun proceedWithLoanLogic(item: CustomerShortCutDataItem,step: String) {
        var loanData = item.createLoanDetails
        var invoiceAppVerification = item.invoiceAndAppVerification
        var bankDetails = item.bankDetails

        val isEmandateVerifiedStatus = loanData!!.isEmandateVerified!!.toLowerCase().equals("yes", true)
        val isAccessKeyVerifiedStatus = invoiceAppVerification!!.isAccessKeyVerified!!.toLowerCase().equals("yes", true)

        if (!isEmandateVerifiedStatus) {
            val isBankDetailsMissing = bankDetails?.bankIFSCCode.isNullOrBlank() || bankDetails?.accountNumber.isNullOrBlank() || bankDetails?.accountType.isNullOrBlank()

            if (isBankDetailsMissing && LoanMode == ConstantClass.offline) {
                startActivity(Intent(this, AppScanInstallPage::class.java))
            } else {
                // Step 7: E-Mandate Process (After Loan Created)
                showEmandateSelectionDialog(item)
            }

        } else
            if (!isAccessKeyVerifiedStatus ) {
            // Step 8: App Install
            startActivity(Intent(this, AppScanInstallPage::class.java))
        }
        else {
            Toast.makeText(this, "Loan process is already completed for this customer.", Toast.LENGTH_SHORT).show()
        }

    }



    private fun showEmandateSelectionDialog(item: CustomerShortCutDataItem) {
        val dialog = Dialog(this, com.bosandroidapp.aopayfinance.R.style.FullScreenDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(com.bosandroidapp.aopayfinance.R.layout.dialog_emandate_selection)

        dialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        dialog.setCanceledOnTouchOutside(false)


        val btnProceed = dialog.findViewById<LinearLayout>(com.bosandroidapp.aopayfinance.R.id.nextlayout)
        val home = dialog.findViewById<ImageView>(com.bosandroidapp.aopayfinance.R.id.home)

        home.setOnClickListener {
            val intent = Intent(this, DashBoard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            onBackPressed()
        }

        fetchEmandateOptionsForDialog(dialog)

        btnProceed.setOnClickListener {
            if (selectedAuthType.isNotBlank()) {
                dialog.dismiss()
                hitApiForBankList(item)
            } else {
                Toast.makeText(this, "Please select an E-Mandate service", Toast.LENGTH_SHORT).show()
            }
        }


        dialog.show()

    }



    private fun fetchEmandateOptionsForDialog(dialog: Dialog) {
        val req = EmandateOptionSelectetionReq(
            mode = LoanMode,
            registrationID = if (LoanMode == ConstantClass.online) {
                ConstantClass.Enach_Option_Online_REGISTRATION_ID
            } else {
                ConstantClass.Enach_Option_Offline_REGISTRATION_ID
            }
        )
        setupEmandateOptionsInDialog(req, dialog)
    }




    private fun setupEmandateOptionsInDialog(req: EmandateOptionSelectetionReq, dialog: Dialog) {
        val layout = dialog.findViewById<LinearLayout>(com.bosandroidapp.aopayfinance.R.id.emandateOptionLayout)
        val rv = dialog.findViewById<RecyclerView>(com.bosandroidapp.aopayfinance.R.id.selectOptionForeMandate)

        Log.d("EmandateOptionReq", Gson().toJson(req))

        viewModel.geteMandateSelectOptionRequest(req).observe(this) { resources ->
            when (resources.apiStatus) {
                ApiStatus.SUCCESS -> {
                    resources.data?.let { users ->
                        if (users.isSuccessful) {
                            users.body()?.let { response ->
                                if (response.code == 200) {

                                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()

                                    val getdata = response.data
                                    if (!getdata.isNullOrEmpty()) {
                                        layout.visibility = View.VISIBLE
                                        emandateSelectList.clear()
                                        emandateSelectList.addAll(listOf(Pair(req.mode!!, getdata.filterNotNull()) as Pair<String, List<EmandateSelectDataItem>>?))

                                        val adapter = EmandateOptionAdapter(emandateSelectList) { selectedOption ->
                                                val option = selectedOption.second
                                                selectedAuthType = option.apiName ?: ""
                                                Log.d("SelectedMandateDialog", " $selectedAuthType")
                                        }
                                        rv.adapter = adapter
                                        adapter.selectFirstOption()
                                    }
                                    else {
                                        layout.visibility = View.GONE
                                    }
                                }
                                else {
                                    layout.visibility = View.GONE
                                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Toast.makeText(this, resources.message ?: "EMandate Options Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                ApiStatus.ERROR -> {
                    Toast.makeText(this, resources.message ?: "EMandate Options Error", Toast.LENGTH_SHORT).show()
                }
                ApiStatus.LOADING -> {}
            }
        }

    }


    fun hitApiForEnach(request: EMandateRequest, customerDataForShortCut: CustomerShortCutDataItem, check: Boolean) {
        Log.d("eManadateReq", Gson().toJson(request))

        var bankData = customerDataForShortCut.bankDetails
        var customerData = customerDataForShortCut.customerDetails
        var loanData = customerDataForShortCut.createLoanDetails

        if (LoanMode == ConstantClass.offline) {

            panViewModel.getEMandateRequestReq(request).observe(this) { resources ->
                when (resources.apiStatus) {
                    ApiStatus.SUCCESS -> {
                        ConstantClass.dialog!!.dismiss()
                        resources.data?.let { users ->
                            if (users.isSuccessful) {
                                users.body()?.let { response ->
                                    Log.d("eMandateRes", Gson().toJson(response))

                                    if (response.data?.customer != null) {
                                        webUrl = response.data.url
                                        startActivity(Intent(this@CustomerListForCreatingShortCutLoanProcessPage, RetailerEMandateVerifyPage::class.java))
                                    }
                                    else
                                    {
                                        isEnachCancelled = true
                                        Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                                    }

                                    var verifiedStatus = "No"

                                    if (response.data?.customer != null) {
                                        verifiedStatus = "Yes"
                                    }

                                    val uploadReq = EnachDateUploadReq(
                                        isEmandateVerified = verifiedStatus,
                                        emAccountType = bankData!!.accountType,
                                        isPannydropVerified = bankData.isPannydropVerified,
                                        emAccountNumber = bankData.accountNumber,
                                        customerCode = customerData!!.customerCode,
                                        retailerCode = customerData!!.retailerCode,
                                        loanCode = loanData!!.loanCode,
                                        emBankName = bankData.bankName,
                                        emIfscCode = bankData.bankIFSCCode,
                                        emumrn = ""
                                    )

                                    hitApiForUploadEnachMandateDataResponse(uploadReq)
                                }

                                val error = users.body()?.statusDesc ?: users.message()
                                ?: "Something went wrong"

                                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                            } else {
                                var error = resources.data.toString()
                                Toast.makeText(
                                    this@CustomerListForCreatingShortCutLoanProcessPage,
                                    error,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        } ?: run {
                            resources.message?.let { message ->
                                showError(message)
                            }

                        }
                    }

                    ApiStatus.ERROR -> {
                        ConstantClass.dialog!!.dismiss()
                        Toast.makeText(
                            this,
                            resources.message ?: "Server error occurred",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    ApiStatus.LOADING -> {
                        if (check) {
                            ConstantClass.OpenPopUpForVeryfyOTP(this)
                        }
                    }
                }
            }

        }
        else {

            if(selectedAuthType!!.contains(ConstantClass.UPIAUTOPAY)){
                var request = UPIMandateRequest(
                    registrationID = ConstantClass.PAN_VERIFICATION_REGISTRATION_ID
                )
                Log.d("UPIMandateReq", Gson().toJson(request))
                dikshifinsureViewModel.getUpiMandateOnlineRequest(request).observe(this) { resources ->
                    resources.let {
                        when (it.apiStatus) {
                            ApiStatus.SUCCESS -> {
                                it.data.let { users ->

                                    if(users!!.isSuccessful){
                                        users!!.body().let { response ->
                                            Log.d("eMandateUpiOnlineRes", Gson().toJson(response))

                                            if (ConstantClass.dialog != null && ConstantClass.dialog!!.isShowing) {
                                                ConstantClass.dialog!!.dismiss()
                                            }

                                            if (response!!.code=="200" ) {
                                                webUrl = response!!.intentUrl
                                                Log.d("webUrl", webUrl.toString())
                                                var MarchentOrderID = response.marchentOrderID
                                                var intent = Intent(this@CustomerListForCreatingShortCutLoanProcessPage, RetailerEMandateVerifyPage::class.java)
                                                intent.putExtra(ConstantClass.MarchentOrderID_UPIAUTOPAY,MarchentOrderID)
                                                intent.putExtra(ConstantClass.RegistrationID_UPIAUTOPAY,request.registrationID)
                                                startActivity(intent)
                                            }

                                            else {
                                                ConstantClass.dialog!!.dismiss()
                                                isEmandateVerified= "No"
                                                isEnachCancelled = true
                                                Toast.makeText(this, response.errorMessage.toString(), Toast.LENGTH_SHORT).show()
                                            }

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
                                                    emumrn = ""
                                                )

                                                hitApiForUploadEnachMandateDataResponse(request)
                                            }

                                        }

                                    }
                                    else {
                                        Toast.makeText(this, resources.message ?: "Server error occurred", Toast.LENGTH_LONG).show()
                                    }

                                }

                            }

                            ApiStatus.ERROR -> {
                                ConstantClass.dialog!!.dismiss()
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
                                if(check){
                                    ConstantClass.OpenPopUpForVeryfyOTP(this)
                                }

                            }

                        }

                    }

                }
            }

            else{
                val onlineRequest = EMandateOnlineRequest(
                    loanNo = request.loanNo,
                    seqType = request.seqType,
                    firstCollectionDate = request.firstCollectionDate,
                    finalCollectionDate = request.finalCollectionDate,
                    collectCollectionUntilCancle = request.collectCollectionUntilCancle,
                    collectionAmount = request.collectionAmount,
                    mobileNumber = request.mobileNumber,
                    teleNumber = request.teleNumber,
                    emailAddress = request.emailAddress,
                    categoryID = request.categoryID,
                    accountHolderName = request.accountHolderName,
                    bankID = request.bankID,
                    authType = request.authType,
                    accountType = request.accountType,
                    iFSCCode = request.iFSCCode,
                    bankAccountNumber = request.bankAccountNumber,
                    bankAccountNumberConfirmation = request.bankAccountNumberConfirmation,
                    frequncy = request.frequncy,
                    debitType = request.debitType,
                    addIn2 = request.addIn2,
                    addIn3 = request.addIn3,
                    registrationID = ConstantClass.PAN_VERIFICATION_REGISTRATION_ID
                )
                onlineEnachViewModel.getEMandateOnlineRequest(onlineRequest).observe(this) { resources ->
                    when (resources.apiStatus) {
                        ApiStatus.SUCCESS -> {
                            ConstantClass.dialog!!.dismiss()
                            resources.data?.let { users ->
                                if (users.isSuccessful) {
                                    users.body()?.let { response ->
                                        Log.d("eMandateOnlineRes", Gson().toJson(response))

                                        if (response.data?.customer != null) {
                                            webUrl = response.data.url
                                            startActivity(Intent(this@CustomerListForCreatingShortCutLoanProcessPage, RetailerEMandateVerifyPage::class.java))
                                        }
                                        else {
                                            isEnachCancelled = true
                                            Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                                        }

                                        var verifiedStatus = "No"

                                        if (response.data?.customer != null) {
                                            verifiedStatus = "Yes"
                                        }

                                        val uploadReq = EnachDateUploadReq(
                                            isEmandateVerified = verifiedStatus,
                                            emAccountType = bankData!!.accountType,
                                            isPannydropVerified = bankData.isPannydropVerified,
                                            emAccountNumber = bankData.accountNumber,
                                            customerCode = customerData!!.customerCode,
                                            retailerCode = customerData.retailerCode,
                                            loanCode = loanData!!.loanCode,
                                            emBankName = bankData.bankName,
                                            emIfscCode = bankData.bankIFSCCode,
                                            emumrn = ""
                                        )

                                        hitApiForUploadEnachMandateDataResponse(uploadReq)
                                    }

                                    val error = users.body()?.statusDesc ?: users.message() ?: "Something went wrong"

                                    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                                }
                                else {
                                    var error = resources.data.toString()
                                    Toast.makeText(this@CustomerListForCreatingShortCutLoanProcessPage, error, Toast.LENGTH_SHORT).show()
                                }

                            }
                        }

                        ApiStatus.ERROR -> {
                            ConstantClass.dialog!!.dismiss()
                            Toast.makeText(this, resources.message ?: "Server error occurred", Toast.LENGTH_LONG).show()
                        }

                        ApiStatus.LOADING -> {
                            if (check) {
                                ConstantClass.OpenPopUpForVeryfyOTP(this)
                            }
                        }
                    }
                }
            }

        }

    }


    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


    fun hitApiForUploadEnachMandateDataResponse(request: EnachDateUploadReq) {
        viewModel.UpdateEmandateDetails(request).observe(this) { resources ->
            when (resources.apiStatus) {
                ApiStatus.SUCCESS -> {
                    Log.d("UploadEnachRes", "Success")
                }

                ApiStatus.ERROR -> {
                    Log.e("UploadEnachRes", "Error: ${resources.message}")
                }

                ApiStatus.LOADING -> {}
            }
        }
    }


    fun hitApiForGetReports(searchText: String = "", isAutoClick: Boolean = false) {

        val retailerCode = preference.getStringValue(ConstantClass.RetailerCode, "")

        val reportreq = ShortCutCustomerRequest(
            searchText = searchText,
            retailerCode = retailerCode
        )

        Log.d("RetailerCustomerListReq", Gson().toJson(reportreq))

        viewModel.getCustomerDataSummaryForShortCut(reportreq).observe(this) { resources ->
                when (resources.apiStatus) {

                    ApiStatus.SUCCESS -> {
                        ConstantClass.dialog!!.dismiss()
                        resources.data?.let { users ->
                            users.body()?.let { response ->
                                Log.d("RetailerCustomerListResponse", Gson().toJson(response))
                                customerList.clear()
                                var getdata = response.data
                                var errorCode = response.code
                                var status = response.success

                                if(errorCode==200 && status==true){
                                    customerList = response.data as MutableList<CustomerStepDataItem>
                                    if (customerList.isNotEmpty()) {
                                        binding.showCustomerreports.visibility = View.VISIBLE
                                        binding.notfoundimage.visibility = View.GONE
                                        customerAdapter.updateData(customerList)

                                    }
                                    else {
                                        binding.showCustomerreports.visibility = View.GONE
                                        binding.notfoundimage.visibility = View.VISIBLE
                                        if (isAutoClick) {
                                            Toast.makeText(this, "Customer not found", Toast.LENGTH_SHORT).show()
                                        }
                                    }

                                }
                                else {
                                    binding.showCustomerreports.visibility = View.GONE
                                    binding.notfoundimage.visibility = View.VISIBLE
                                    Toast.makeText(this@CustomerListForCreatingShortCutLoanProcessPage, response.message, Toast.LENGTH_SHORT).show()
                                }

                            }
                        }
                    }

                    ApiStatus.ERROR -> {
                        ConstantClass.dialog!!.dismiss()
                        Toast.makeText(this@CustomerListForCreatingShortCutLoanProcessPage, resources.message ?: "Error occurred", Toast.LENGTH_SHORT).show()
                    }

                    ApiStatus.LOADING -> {
                        ConstantClass.OpenPopUpForVeryfyOTP(this)
                    }

                }
            }
    }


    fun customerDataAccordingToStatus(customerList: MutableList<CustomerStepDataItem>){
        if (customerList.isNotEmpty()) {
            binding.showCustomerreports.visibility = View.VISIBLE
            binding.notfoundimage.visibility = View.GONE
            customerAdapter.updateData(customerList)
        }
        else {
            binding.showCustomerreports.visibility = View.GONE
            binding.notfoundimage.visibility = View.VISIBLE
        }
    }


}
