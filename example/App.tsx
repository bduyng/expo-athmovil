import * as ExpoAthmovil from "expo-athmovil";
import { StyleSheet, Text, View } from "react-native";

export default function App() {
  return (
    <View style={styles.container}>
      <Text>{ExpoAthmovil.hello()}</Text>
      <ExpoAthmovil.ExpoAthmovilView
        style={{ width: 220, height: 60, backgroundColor: "blue" }}
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
