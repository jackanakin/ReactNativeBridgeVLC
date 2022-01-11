# How to add VLC lib
## Android
1. In dependencies at android/app/build.gradle add -> implementation group: 'org.videolan.android', name: 'libvlc-all', version: '3.2.6'
2. In the same file (android/app/buidl.gradle), at android add:
packagingOptions {
        pickFirst 'lib/x86/libc++_shared.so'
        pickFirst 'lib/x86_64/libc++_shared.so'
        pickFirst 'lib/armeabi-v7a/libc++_shared.so'
        pickFirst 'lib/arm64-v8a/libc++_shared.so'
} 
3. In android/gradle.properties uncomment the line -> org.gradle.jvmargs=-Xmx2048m -XX:MaxPermSize=512m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8