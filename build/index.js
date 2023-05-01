import { EventEmitter, NativeModulesProxy } from 'expo-modules-core';
import ExpoAthmovilModule from './ExpoAthmovilModule';
import ExpoAthmovilView from './ExpoAthmovilView';
// Get the native constant value.
export const PI = ExpoAthmovilModule.PI;
export function hello() {
    return ExpoAthmovilModule.hello();
}
export async function setValueAsync(value) {
    return await ExpoAthmovilModule.setValueAsync(value);
}
export async function payWithATHMovil(params) {
    return ExpoAthmovilModule.payWithATHMovil(params);
}
const emitter = new EventEmitter(ExpoAthmovilModule ?? NativeModulesProxy.ExpoAthmovil);
export function addChangeListener(listener) {
    return emitter.addListener('onChange', listener);
}
export { ExpoAthmovilView };
//# sourceMappingURL=index.js.map