package com.aramvirabyan.yourmeditation;

import static android.webkit.WebViewClient.ERROR_FILE_NOT_FOUND;
import static android.webkit.WebViewClient.ERROR_HOST_LOOKUP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.status_bar_color));
        }

        this.webView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("main", 0);
        switch(settings.getString("cached", "else")) {
            case "yes":
                // gonna make this in the future, don't forget add date check because if user will cache app, there will be updates of the app and we will need to re-cache content time-by-time.
                break;
        }


        WebViewClientImpl webViewClient = new WebViewClientImpl(this, this);
        webView.setWebViewClient(webViewClient);

        webView.addJavascriptInterface(new MainActivityInterface(this), "Android");

        webView.setWebChromeClient(new WebChromeClient());

        webView.loadUrl(getResources().getString(R.string.frontend_url));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}