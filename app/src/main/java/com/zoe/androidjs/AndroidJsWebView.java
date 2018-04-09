package com.zoe.androidjs;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.webkit.WebView;

public class AndroidJsWebView extends WebView {

    public AndroidJsWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public AndroidJsWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public AndroidJsWebView(Context context) {
        super(context);
    }

    public void setToken2Js(String token){
        callJs("getTokenFromAndroid('"+token+"')");
    }
    public void setLocation2Js(double longitude, double latitude){
        callJs("getLocationFromAndroid('"+longitude+"','"+latitude+"')");
    }

    Handler mHandler = new Handler();
    public void callJs(final String function){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= 19) {
                    evaluateJavascript(function, null);
                } else {
                    loadUrl("javascript:" + function);
                }
            }
        });
    }

    public void setOnWebViewListener(OnWebViewListener l){
        addJavascriptInterface(new AndroidJs(l), "android");
    }

    public interface OnWebViewListener{
        void finish();
        void produceToken();
    }
}
