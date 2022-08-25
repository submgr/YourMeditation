package com.aramvirabyan.yourmeditation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.logging.Logger;

public class WebViewClientImpl extends WebViewClient {
    private Activity activity = null;
    private Context context = null;

    public WebViewClientImpl(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        Log.e("APP", context.getString(R.string.frontend_url));
        if(url.indexOf(context.getString(R.string.frontend_url)) > -1 ) return false;

        CharSequence text = "Can't access this domain. Sorry.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        /** Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent); **/

        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finishAffinity();
        return true;
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description,
                                String failingUrl) {

        if (1 < 2) {
            CharSequence text = "Code: " + errorCode + "\n" + description;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            if(errorCode == -2){
                Intent intent = new Intent(activity, NoConnection.class);
                activity.startActivity(intent);

            }

        }
    }
}
