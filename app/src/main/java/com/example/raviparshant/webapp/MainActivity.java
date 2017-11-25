package com.example.raviparshant.webapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    private RelativeLayout container;
    private Button nextButton, closeButton;
    private EditText findBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    webView = (WebView)findViewById(R.id.webView1);

    WebSettings webSetting = webView.getSettings();
    webSetting.setBuiltInZoomControls(true);
    webSetting.setJavaScriptEnabled(true);

    webView.setWebViewClient(new WebViewClient());
   // webView.loadUrl("file:///android_asset/index.html");

        if (savedInstanceState != null)
            webView.restoreState(savedInstanceState.getBundle("webViewState"));
        else {
            webView.loadUrl("file:///android_asset/index.html");
        }


}

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle bundle = new Bundle();
        webView.saveState(bundle);
        outState.putBundle("webViewState", bundle);
    }

    private class WebViewClient extends android.webkit.WebViewClient
{
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        return super.shouldOverrideUrlLoading(view, url);
    }
}

    private static final int SEARCH_MENU_ID = Menu.FIRST;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        menu.add(0, SEARCH_MENU_ID, 0, "Search");

        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu){
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case SEARCH_MENU_ID:
                search();
                return true;
        }
        return true;
    }


    public void search(){
        container = (RelativeLayout)findViewById(R.id.layoutId);

        nextButton = new Button(this);
        nextButton.setText("Next");
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                webView.findNext(true);
            }
        });
        container.addView(nextButton);

        closeButton = new Button(this);
        closeButton.setText("Close");
        closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                container.removeAllViews();

            }
        });
        container.addView(closeButton);

        findBox = new EditText(this);
        findBox.setMinEms(30);
        findBox.setSingleLine(true);
        findBox.setHint("Search");

        findBox.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if((event.getAction() == KeyEvent.ACTION_DOWN) && ((keyCode == KeyEvent.KEYCODE_ENTER))){
                    webView.findAll(findBox.getText().toString());

                    try{
                        Method m = WebView.class.getMethod("setFindIsUp", Boolean.TYPE);
                        m.invoke(webView, true);
                    }catch(Exception ignored){}
                }
                return false;
            }
        });

        container.addView(findBox);
    }

}
