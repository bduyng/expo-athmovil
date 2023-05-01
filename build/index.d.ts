import { Subscription } from 'expo-modules-core';
import { ChangeEventPayload, ExpoAthmovilViewProps } from './ExpoAthmovil.types';
import ExpoAthmovilView from './ExpoAthmovilView';
export declare const PI: any;
export declare function hello(): string;
export declare function setValueAsync(value: string): Promise<any>;
export declare function payWithATHMovil(params: {
    businessAccount: string;
    urlScheme: string;
    total: number;
    subtotal: number;
    tax: number;
    metadata1: string;
    metadata2: string;
    items: {
        name: string;
        price: number;
        quantity: number;
    }[];
}): Promise<any>;
export declare function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription;
export { ExpoAthmovilView, ExpoAthmovilViewProps, ChangeEventPayload };
//# sourceMappingURL=index.d.ts.map