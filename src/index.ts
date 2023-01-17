import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to ExpoAthmovil.web.ts
// and on native platforms to ExpoAthmovil.ts
import ExpoAthmovilModule from './ExpoAthmovilModule';
import ExpoAthmovilView from './ExpoAthmovilView';
import { ChangeEventPayload, ExpoAthmovilViewProps } from './ExpoAthmovil.types';

// Get the native constant value.
export const PI = ExpoAthmovilModule.PI;

export function hello(): string {
  return ExpoAthmovilModule.hello();
}

export async function setValueAsync(value: string) {
  return await ExpoAthmovilModule.setValueAsync(value);
}

const emitter = new EventEmitter(ExpoAthmovilModule ?? NativeModulesProxy.ExpoAthmovil);

export function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onChange', listener);
}

export { ExpoAthmovilView, ExpoAthmovilViewProps, ChangeEventPayload };
