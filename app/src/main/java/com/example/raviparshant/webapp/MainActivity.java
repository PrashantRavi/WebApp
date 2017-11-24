package com.example.raviparshant.webapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    webView = (WebView)findViewById(R.id.webView1);

    WebSettings webSetting = webView.getSettings();
    webSetting.setBuiltInZoomControls(true);
    webSetting.setJavaScriptEnabled(true);

    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl("file:///android_asset/index.html");


}

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    private class WebViewClient extends android.webkit.WebViewClient
{
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        return super.shouldOverrideUrlLoading(view, url);
    }
}
}
