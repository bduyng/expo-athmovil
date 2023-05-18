package expo.modules.athmovil

import android.content.Context
import android.util.Log
import java.util.ArrayList
import java.util.Date

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import expo.modules.kotlin.Promise
import expo.modules.kotlin.records.Field
import expo.modules.kotlin.records.Record
import expo.modules.kotlin.exception.CodedException
import expo.modules.kotlin.exception.Exceptions

import com.evertecinc.athmovil.sdk.checkout.OpenATHM
import com.evertecinc.athmovil.sdk.checkout.PayButton
import com.evertecinc.athmovil.sdk.checkout.objects.ATHMPayment
import com.evertecinc.athmovil.sdk.checkout.objects.Items
import com.evertecinc.athmovil.sdk.checkout.PaymentResponse
import com.evertecinc.athmovil.sdk.checkout.interfaces.PaymentResponseListener

class ExpoAthmovilModule : Module() {
  private var listener: PaymentResponseListener = object : PaymentResponseListener {
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
  private val context: Context
    get() = requireNotNull(appContext.reactContext) {
      "React Application Context is null"
    }
  private val currentActivity
    get() = appContext.currentActivity ?: throw Exceptions.MissingActivity()

  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  override fun definition() = ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('ExpoAthmovil')` in JavaScript.
    Name("ExpoAthmovil")

    // OnActivityEntersForeground {
    //   Log.i("ExpoAthmovilModule", "OnActivityEntersForeground")
    //   // Get the extras from the intent
    //   Log.d("ExpoAthmovilModule", currentActivity.getIntent().toString())

    //   // PaymentResponse.validatePaymentResponse(currentActivity.getIntent(), listener)
    //   if (currentActivity != null) {
    //     PaymentResponse.validatePaymentResponse(currentActivity.getIntent(), listener)
    //   }
    // }

    // Sets constant properties on the module. Can take a dictionary or a closure that returns a dictionary.
    Constants(
      "PI" to Math.PI
    )

    // Defines event names that the module can send to JavaScript.
    Events("onChange")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("hello") {
      "Hello world 👋"
    }

    // Defines a JavaScript function that always returns a Promise and whose native code
    // is by default dispatched on the different thread than the JavaScript runtime runs on.
    AsyncFunction("setValueAsync") { value: String ->
      // Send an event to JavaScript.
      sendEvent("onChange", mapOf(
        "value" to value
      ))
    }

    AsyncFunction("payWithATHMovil") { params: ParamsType, promise: Promise ->
      try {
        val athmPayment = ATHMPayment(currentActivity)
        athmPayment.setCallbackSchema(params.urlScheme) //Replace this value with the Callback Schema of your app.
        athmPayment.setPublicToken(params.businessAccount) //Replace this value with the Public Token of your ATH Móvil Business account.
        athmPayment.setTimeout(600)
        athmPayment.setTotal(params.total)
        athmPayment.setSubtotal(params.subtotal)
        athmPayment.setTax(params.tax)
        athmPayment.setMetadata1(params.metadata1)
        athmPayment.setMetadata2(params.metadata2)
        athmPayment.setItems(arrayListOf())
        athmPayment.setBuildType("")
        
        OpenATHM.validateData(athmPayment, currentActivity)
      } catch (e: Exception) {
        Log.e("ExpoAthmovilModule", "Can not switch to ATHMovil app" + params.urlScheme, e)
        promise.reject("ExpoAthmovilModule", "payWithATHMovil", e)
      }
    }

    // Enables the module to be used as a native view. Definition components that are accepted as part of
    // the view definition: Prop, Events.
    View(ExpoAthmovilView::class) {
      // Defines a setter for the `name` prop.
      Prop("name") { view: ExpoAthmovilView, prop: String ->
        println(prop)
      }
    }
  }
}

class ParamsType: Record {
  @Field
  var businessAccount: String = "utf8"

  @Field
  var urlScheme: String = ""

  @Field
  var total: Double = 0.0
  
  @Field
  var subtotal: Double = 0.0
  
  @Field
  var tax: Double = 0.0
  
  @Field
  var metadata1: String = ""
  
  @Field
  var metadata2: String = ""
  
  @Field
  var items: List<PaymentItemType> = emptyList()
}

class PaymentItemType: Record {
  @Field
  var name: String = ""
  
  @Field
  var price: Double = 0.0
  
  @Field
  var quantity: Int = 0
  
  @Field
  var desc: String = ""
  
  @Field
  var metadata: String = ""
  
}