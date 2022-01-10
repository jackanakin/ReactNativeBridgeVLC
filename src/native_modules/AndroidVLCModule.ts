import { NativeModules } from 'react-native';

const { VLCModule } = NativeModules;

interface AndroidVLCModuleInterface {
    play(url: string): void;
}

export default VLCModule as AndroidVLCModuleInterface;