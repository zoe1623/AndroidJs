package com.zoe.androidjs;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String PC_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidJsWebView webView = new AndroidJsWebView(this);
        setContentView(webView, new ViewGroup.LayoutParams(-1,-1));
        initWebView(webView);
        webView.loadUrl("file:///android_asset/test.html");
    }

    private void initWebView(final AndroidJsWebView view){
        view.setOnWebViewListener(new AndroidJsWebView.OnWebViewListener() {
            @Override
            public void finish() {
                MainActivity.this.finish();
            }

            @Override
            public void produceToken() {
                view.setToken2Js("token");
            }

        });

        WebSettings settings = view.getSettings();
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setDomStorageEnabled(true);

        //启用数据库
        settings.setDatabaseEnabled(true);
        //设置定位的数据库路径
        String dir = getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setGeolocationDatabasePath(dir);
        //启用地理定位
        settings.setGeolocationEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // 支持缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUserAgentString(PC_USER_AGENT);// 设置浏览器标识为PC

        settings.setUseWideViewPort(true);//关键点
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setAppCacheEnabled(true);
        settings.setLoadWithOverviewMode(true);

        view.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            }

        });

        view.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            }

            @Override
            public void onPageFinished(WebView view, String url) {
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });

    }
}
