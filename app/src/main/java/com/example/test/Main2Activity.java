package com.example.test;

import android.app.PictureInPictureParams;
import android.os.Build;
import android.os.Bundle;
import android.util.Rational;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    private WebView webView;
    private WebSettings webSettings;

    private ImageView imageView;
    private boolean minimize;
    private final Object pictureInPictureParamsBuilder;
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pictureInPictureParamsBuilder = new PictureInPictureParams.Builder();
        } else {
            pictureInPictureParamsBuilder = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        webView = (WebView) findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportMultipleWindows(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);

        webView.loadUrl("https://huvleview.com");



//        imageView = findViewById(R.id.ImgV);
    }

    private void minimize() {
        if (imageView == null){
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (pictureInPictureParamsBuilder instanceof PictureInPictureParams.Builder) {
                Rational aspectRatio = new Rational(2,1);
                PictureInPictureParams build = ((PictureInPictureParams.Builder) pictureInPictureParamsBuilder).setAspectRatio(aspectRatio).build();
                minimize = true;
                enterPictureInPictureMode(build);
            }
        }
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        if (isInPictureInPictureMode) {

        } else {
            minimize = false;
        }
    }

    @Override
    public void onUserLeaveHint () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (pictureInPictureParamsBuilder instanceof PictureInPictureParams.Builder) {
                Rational aspectRatio = new Rational(1,1);
                PictureInPictureParams build = ((PictureInPictureParams.Builder) pictureInPictureParamsBuilder).setAspectRatio(aspectRatio).build();
                minimize = true;
                enterPictureInPictureMode(build);
            }
        }
    }
}
