import * as ExpoAthmovil from "expo-athmovil";
import { StyleSheet, View } from "react-native";

export default function App() {
  return (
    <View style={styles.container}>
      <ExpoAthmovil.ExpoAthmovilView
        style={{ width: 250, height: 60, borderRadius: 16}}
        onButtonPress={()=>{
          console.log('aaaaa');
          
          ExpoAthmovil.payWithATHMovil({
            businessAccount: 'dummy',
            urlScheme: 'expo.modules.athmovil.example',
            total: 2.0,
            subtotal: 1.0,
            tax: 1.0,
            metadata1: 'Test metadata1',
            metadata2: 'Test metadata2',
            items: [{name: 'Dish 1', price: 1.0, quantity: 1,}]
          }).then((response)=>{
            console.log('Success', JSON.stringify(response, null, 2));
          }).catch(error => {
            console.log('Error', error);
          });
        }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
