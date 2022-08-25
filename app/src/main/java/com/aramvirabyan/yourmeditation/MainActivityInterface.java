package com.aramvirabyan.yourmeditation;

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class MainActivityInterface extends MainActivity {
    Context mContext;

    MainActivityInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface public void toast(String text) {
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(mContext, text, duration);
        toast.show();
    }

    @JavascriptInterface public void storage_edit(String key, String value) {
        SharedPreferences settings = mContext.getSharedPreferences("main", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);

        editor.apply();
    }

    @JavascriptInterface public String storage_get(String key) {
        SharedPreferences settings = mContext.getSharedPreferences("main", 0);
        return settings.getString(key, "");
    }


}