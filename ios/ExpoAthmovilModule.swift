import ExpoModulesCore
import athmovil_checkout

public class ExpoAthmovilModule: Module {
  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  public func definition() -> ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('ExpoAthmovil')` in JavaScript.
    Name("ExpoAthmovil")

    // Sets constant properties on the module. Can take a dictionary or a closure that returns a dictionary.
    Constants([
      "PI": Double.pi
    ])

    // Defines event names that the module can send to JavaScript.
    Events("onChange")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("hello") {
      return "Hello world! 👋"
    }

    // Defines a JavaScript function that always returns a Promise and whose native code
    // is by default dispatched on the different thread than the JavaScript runtime runs on.
    AsyncFunction("setValueAsync") { (value: String) in
      // Send an event to JavaScript.
      self.sendEvent("onChange", [
        "value": value
      ])
    }

    AsyncFunction("payWithATHMovil") { (message: String, promise: Promise) in
      let businessAccount = ATHMBusinessAccount(token: "Public Token of your ATH Móvil business account")
      let urlScheme = ATHMURLScheme(urlScheme: "URL Scheme of your application")
      let payment = ATHMPayment(total: 20.00)

      /// The object below will tell you the status of the payment after the end user has completed the payment process.
      /// The code inside onCompleted, onExpired, onCancelled or onException is on the main thread.

      let hander = ATHMPaymentHandler(onCompleted: { [weak self] (payment: ATHMPaymentResponse) in
          /// Handle the response when the payment is completed here.
          promise.resolve(message)

      }, onExpired: { [weak self] (payment: ATHMPaymentResponse) in
          /// Handle the response when the payment is expired here.
          promise.reject("404", "expired")

      }, onCancelled: { [weak self] (payment: ATHMPaymentResponse) in
          /// Handle the response when the payment is cancelled here.
          promise.reject("401", "cancelled")

      }) { [weak self] (error: ATHMPaymentError) in
          /// Handle any exception regarding a request or response here. See error section for more details.
          promise.reject("404", "cancelled")
      }

      let request = ATHMPaymentRequest(account: businessAccount, scheme: urlScheme, payment: payment)
      request.pay(handler: hander)

      /// At this point your app will open ATH Móvil and the payment process will start.
      /// If ATH Móvil is not installed on the end user's device the App Store will be automatically opened on ATH Móvil's listing.
    }

    // Enables the module to be used as a view manager. The view manager definition is built from
    // the definition components used in the closure passed to viewManager.
    // Definition components that are accepted as part of the view manager definition: `View`, `Prop`.
    ViewManager {
      // Defines the factory creating a native view when the module is used as a view.
      View {
        ExpoAthmovilView()
      }

      // Defines a setter for the `name` prop.
      Prop("name") { (view: ExpoAthmovilView, prop: String) in
        print(prop)
      }
    }
  }
}
