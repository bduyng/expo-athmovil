import { EventEmitter, NativeModulesProxy, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to ExpoAthmovil.web.ts
// and on native platforms to ExpoAthmovil.ts
import { ChangeEventPayload, ExpoAthmovilViewProps } from './ExpoAthmovil.types';
import ExpoAthmovilModule from './ExpoAthmovilModule';
import ExpoAthmovilView from './ExpoAthmovilView';

// Get the native constant value.
export const PI = ExpoAthmovilModule.PI;

export function hello(): string {
  return ExpoAthmovilModule.hello();
}

export async function setValueAsync(value: string) {
  return await ExpoAthmovilModule.setValueAsync(value);
}

export async function payWithATHMovil() {
  return ExpoAthmovilModule.payWithATHMovil('hello');
}

const emitter = new EventEmitter(ExpoAthmovilModule ?? NativeModulesProxy.ExpoAthmovil);

export function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onChange', listener);
}

export { ExpoAthmovilView, ExpoAthmovilViewProps, ChangeEventPayload };
