package com.zoe.androidjs;

import android.webkit.JavascriptInterface;

public class AndroidJs {
    private AndroidJsWebView.OnWebViewListener mListener;
    public AndroidJs(AndroidJsWebView.OnWebViewListener l){
        mListener = l;
    }
    @JavascriptInterface
    public void finish(){
        if(mListener != null) {
            mListener.finish();
        }
    }
    @JavascriptInterface
    public void produceToken(){
        if(mListener != null) {
            mListener.produceToken();
        }
    }
}
