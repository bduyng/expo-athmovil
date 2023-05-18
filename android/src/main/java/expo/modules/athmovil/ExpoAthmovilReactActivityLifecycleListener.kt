package expo.modules.athmovil

import java.util.Date

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.os.Bundle
import expo.modules.core.interfaces.ReactActivityLifecycleListener

import com.evertecinc.athmovil.sdk.checkout.objects.Items
import com.evertecinc.athmovil.sdk.checkout.PaymentResponse
import com.evertecinc.athmovil.sdk.checkout.interfaces.PaymentResponseListener

class ExpoAthmovilReactActivityLifecycleListener : ReactActivityLifecycleListener {
  override fun onCreate(activity: Activity, savedInstanceState: Bundle?) {
    Log.e("ExpoAthmovilReactActivityLifecycleListener", "OnCreate")
  }

  override fun onNewIntent(intent: Intent?): Boolean {
    Log.e("ExpoAthmovilReactActivityLifecycleListener", "onNewIntent")
    var listener: PaymentResponseListener = object : PaymentResponseListener {
      override fun onCancelledPayment(date: Date, referenceNumber: String, dailyTransactionID: String, name: String, phoneNumber: String, email: String, total: Double, tax: Double, subtotal: Double, fee: Double, netAmount: Double, metadata1: String, metadata2: String, paymentId: String, items: ArrayList<Items>) {
        Log.e("ExpoAthmovilModule", "cancelled")
        // promise.reject("cancelled", "cancelled", null)
      }
      override fun onExpiredPayment(date: Date, referenceNumber: String, dailyTransactionID: String, name: String, phoneNumber: String, email: String, total: Double, tax: Double, subtotal: Double, fee: Double, netAmount: Double, metadata1: String, metadata2: String, paymentId: String, items: ArrayList<Items>) {
        Log.e("ExpoAthmovilModule", "expired")
        // promise.reject("expired", "expired", null)
      }
      override fun onCompletedPayment(date: Date, referenceNumber: String, dailyTransactionID: String, name: String, phoneNumber: String, email: String, total: Double, tax: Double, subtotal: Double, fee: Double, netAmount: Double, metadata1: String, metadata2: String, paymentId: String, items: ArrayList<Items>) {
        // promise.resolve("completed")
      }
      override fun onPaymentException(error: String, description: String) {
        Log.e("ExpoAthmovilModule " + error, description)
        // promise.reject(error, description, null)
      }
    }
    if (intent != null) {
      PaymentResponse.validatePaymentResponse(intent, listener)
      return true
    }
    return false
  }
}