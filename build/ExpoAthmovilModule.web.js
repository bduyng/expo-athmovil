import { EventEmitter } from 'expo-modules-core';
const emitter = new EventEmitter({});
export default {
    PI: Math.PI,
    async setValueAsync(value) {
        emitter.emit('onChange', { value });
    },
    async payWithATHMovil(value) {
        emitter.emit('onChange', { value });
    },
    hello() {
        return 'Hello world! 👋';
    },
};
//# sourceMappingURL=ExpoAthmovilModule.web.js.map