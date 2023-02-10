package expo.modules.athmovil

import android.content.Context
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.views.ExpoView

import com.evertecinc.athmovil.sdk.checkout.*
import com.evertecinc.athmovil.sdk.checkout.objects.ATHMPayment;
import com.evertecinc.athmovil.sdk.checkout.objects.Items;

// https://github.com/evertec/athmovil-android-sdk/blob/master/athmovil-checkout/src/main/java/com/evertecinc/athmovil/sdk/checkout/PayButton.java
class ExpoAthmovilView(context: Context, appContext: AppContext) : ExpoView(context, appContext) {
  private val payButton: PayButton = PayButton(context)
  private val athmPayment: ATHMPayment = ATHMPayment(context)

  init {
    payButton.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    payButton.setTheme(PayButton.ButtonTheme.ORIGINAL)
    payButton.setLanguage(PayButton.ButtonLanguage.DEFAULT)
    payButton.setOnClickListener { onClickPayButton() }
    addView(payButton)
  }
  
  fun onClickPayButton() {
    val items = arrayListOf<Items>()
    athmPayment.callbackSchema = "schema" //Replace this value with the Callback Schema of your app.
    athmPayment.publicToken = "dummy" //Replace this value with the Public Token of your ATH Móvil Business account.
    athmPayment.timeout = 600
    athmPayment.total = 1.00
    athmPayment.subtotal = 1.00
    athmPayment.tax = 1.00
    athmPayment.metadata1 = "metadata1 test"
    athmPayment.metadata2 = "metadata2 test"

    athmPayment.items = items
    athmPayment.buildType = ""
    OpenATHM.validateData(athmPayment, context)
  }
}
