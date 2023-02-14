import ExpoModulesCore
import athmovil_checkout

// This view will be used as a native component. Make sure to inherit from `ExpoView`
// to apply the proper styling (e.g. border radius and shadows).
class ExpoAthmovilView: ExpoView {
  let onButtonPress = EventDispatcher()
  var childView: ATHMButton?
  var needsUpdate = true
  
  required init(appContext: AppContext? = nil) {
    super.init(appContext: appContext)
    clipsToBounds = true
  }
  
  override func layoutSubviews() {
    updateChildIfNeeded()
  }

  func updateChildIfNeeded() {
    guard needsUpdate else {
      return
    }
    unmountChild()
    mountNewChild()
    needsUpdate = false
  }

  private func mountNewChild() {
    print(bounds)
    let newChildView = ATHMButton(frame: bounds)
    newChildView.theme = ATHMThemeClassic()
    newChildView.translatesAutoresizingMaskIntoConstraints = false
    newChildView.addTarget(self, action:#selector(onTouchUp), for: .touchUpInside)

    addSubview(newChildView)
    childView = newChildView

    NSLayoutConstraint.activate([
      newChildView.topAnchor.constraint(equalTo: self.topAnchor),
      newChildView.bottomAnchor.constraint(equalTo: self.bottomAnchor),
      newChildView.leadingAnchor.constraint(equalTo: self.leadingAnchor),
      newChildView.trailingAnchor.constraint(equalTo: self.trailingAnchor)
    ])
  }

  private func unmountChild() {
    childView?.removeTarget(self, action: #selector(onTouchUp), for: .touchUpInside)
    childView?.removeFromSuperview()
    childView = nil
  }

  @objc
  private func onTouchUp() {
    onButtonPress()
  }
}
