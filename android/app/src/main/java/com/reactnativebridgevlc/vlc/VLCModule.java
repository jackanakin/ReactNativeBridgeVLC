package com.reactnativebridgevlc;
import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import javax.annotation.Nonnull;

public class VLCModule extends ReactContextBaseJavaModule {

    public VLCModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Nonnull
    @Override
    public String getName() {
        return "VLCModule";
    }

    @ReactMethod
    void play(String url) {
        Activity activity = getCurrentActivity();
        Intent intent = new Intent(activity, VLCActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }
}