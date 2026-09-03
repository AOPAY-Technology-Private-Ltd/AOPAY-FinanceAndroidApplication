# Implementation Plan - Enhanced Error Handling for Customer and Loan Creation

Implement robust error handling for customer registration and loan creation on the `QRCodePage`. This includes displaying specific toast messages for HTTP error codes such as 400, 404, and 500.

## User Review Required

> [!NOTE]
> The error handling will use a centralized `handleApiError` method in `QRCodePage` to ensure consistency.

## Proposed Changes

### [Component] Authentication ViewModel

#### [MODIFY] [AuthenticationViewModel.kt](file:///E:/GIT/AOPAY-FinanceAndroidApplication/app/src/main/java/com/bosandroidapp/aopayfinance/ui/viewmodel/AuthenticationViewModel.kt)
- Update `getRetailerLoanCreatedReq` to include the full `Response` object in the `ApiResponse.error` state. This allows the Activity to access the HTTP status code.

### [Component] UI - QRCodePage

#### [MODIFY] [QRCodePage.kt](file:///E:/GIT/AOPAY-FinanceAndroidApplication/app/src/main/java/com/bosandroidapp/aopayfinance/ui/view/activity/retailer/QRCodePage.kt)
- Update `handleApiError` to provide more descriptive messages for 400, 404, and 500 errors as requested.
- In `hitApiForCustomerRegister`, ensure that all non-successful API responses call `handleApiError`. Currently, some error cases only log the error.
- In `hitApiForRetailerCreatedLoan`, update the `ApiStatus.ERROR` observer block to call `handleApiError` using the response code provided by the updated ViewModel.

## Verification Plan

### Automated Tests
- N/A (Project seems to lack automated UI tests for this flow).

### Manual Verification
- Deploy the app to a device.
- Navigate to `QRCodePage`.
- Trigger customer registration and loan creation.
- Simulate/Observe different error scenarios (e.g., disconnect internet for network error, or use invalid data to trigger 400).
- Verify that Toast messages correctly reflect the error types (400, 404, 500).
