import { requireNativeViewManager } from 'expo-modules-core';
import * as React from 'react';

import { ExpoAthmovilViewProps } from './ExpoAthmovil.types';

const NativeView: React.ComponentType<ExpoAthmovilViewProps> =
  requireNativeViewManager('ExpoAthmovil');

export default function ExpoAthmovilView(props: ExpoAthmovilViewProps) {
  return <NativeView {...props} />;
}
