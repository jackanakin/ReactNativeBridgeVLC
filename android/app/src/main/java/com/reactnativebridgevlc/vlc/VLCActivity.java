package com.reactnativebridgevlc;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

public class VLCActivity extends Activity implements IVLCVout.Callback {
    private LibVLC libvlc;
    private MediaPlayer mMediaPlayer;
    private String url;

    public static int mWidthPixels;
    public static int mHeightPixels;
    public static SurfaceView mSurface;
    public static SurfaceHolder holder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");

        setContentView(R.layout.video_screen);

        mSurface = findViewById(R.id.surface);
        handleScreenResolution();

        startPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (libvlc == null) {
            startPlayer();
        }
    }

    private void handleScreenResolution() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;
    }

    public void startPlayer() {
        holder = mSurface.getHolder();
        libvlc = new LibVLC(this);
        holder.setKeepScreenOn(true);
        mMediaPlayer = new MediaPlayer(libvlc);
        final IVLCVout vout = mMediaPlayer.getVLCVout();
        vout.setVideoView(mSurface);
        vout.setWindowSize(mWidthPixels, mHeightPixels);
        vout.addCallback(this);
        vout.attachViews();

        Media m = new Media(libvlc, Uri.parse(url));
        mMediaPlayer.setAspectRatio("16:9");
        mMediaPlayer.setMedia(m);
        mMediaPlayer.play();
    }

    public void releasePlayer() {
        try {
            if (libvlc == null)
                return;
            final IVLCVout vout = mMediaPlayer.getVLCVout();
            vout.removeCallback(this);
            vout.detachViews();
            holder = null;
            mMediaPlayer.stop();
            mMediaPlayer.setMedia(null);
            libvlc.release();
            libvlc = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfacesCreated(IVLCVout vout) {
    }

    @Override
    public void onSurfacesDestroyed(IVLCVout vout) {
    }
}