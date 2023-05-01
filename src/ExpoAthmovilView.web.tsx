import * as React from 'react';

import { ExpoAthmovilViewProps } from './ExpoAthmovil.types';

export default function ExpoAthmovilView(props: ExpoAthmovilViewProps) {
  return (
    <div>
      <span>{props.name}</span>
    </div>
  );
}
