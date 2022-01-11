/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * Generated with the TypeScript template
 * https://github.com/react-native-community/react-native-template-typescript
 *
 * @format
 */

import React from 'react';
import {
  Button,
  Platform,
  requireNativeComponent,
  SafeAreaView,
  ScrollView,
  StatusBar,
  useColorScheme,
  View,
} from 'react-native';
import { Colors, Header } from 'react-native/Libraries/NewAppScreen';
import AndroidVLCModule from './native_modules/AndroidVLCModule';

const CounterView = requireNativeComponent("CounterView")
const App = () => {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  function play() {
    if (Platform.OS === "android") AndroidVLCModule.play("http://streams.videolan.org/streams/mp4/Mr_MrsSmith-h264_aac.mp4");
  }

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={backgroundStyle}>
        <Header />
        <View
          style={{
            backgroundColor: isDarkMode ? Colors.black : Colors.white,
          }}>
          <Button onPress={play} title='PLAY WITH VLC' />
          {Platform.OS === "ios" && <CounterView style={{ alignItems: "center", justifyContent: "center", width: 200, height: 200 }} />}
        </View>
      </ScrollView>
    </SafeAreaView>
  );
};

export default App;
