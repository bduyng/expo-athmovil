import { requireNativeViewManager } from 'expo-modules-core';
import * as React from 'react';
const NativeView = requireNativeViewManager('ExpoAthmovil');
export default function ExpoAthmovilView(props) {
    return React.createElement(NativeView, { ...props });
}
//# sourceMappingURL=ExpoAthmovilView.js.map