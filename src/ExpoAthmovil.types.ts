import { StyleProp, ViewStyle } from "react-native";

export type ChangeEventPayload = {
  value: string;
};

export type ExpoAthmovilViewProps = {
  style?: StyleProp<Omit<ViewStyle, 'backgroundColor'>>;
  onButtonPress?(): void;
};
