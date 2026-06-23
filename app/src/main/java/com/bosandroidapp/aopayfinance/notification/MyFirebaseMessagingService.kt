package com.bosandroidapp.aopayfinance.notification

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bosandroidapp.aopayfinance.kioskmode.KioskDeviceAdminReceiver
import com.bosandroidapp.aopayfinance.kioskmode.KioskPolicyService
import com.bosandroidapp.aopayfinance.kioskmode.isAdmin
import com.bosandroidapp.aopayfinance.kioskmode.startLockSituation
import com.bosandroidapp.aopayfinance.kioskmode.stopLockSituation
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {



    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)


        val action = remoteMessage.data["action"]

        val intent = Intent(this, KioskPolicyService::class.java).apply {
            putExtra("action", action)
        }
        ContextCompat.startForegroundService(this, intent)

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

}