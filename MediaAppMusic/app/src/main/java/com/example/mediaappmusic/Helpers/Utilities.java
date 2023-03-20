package com.example.mediaappmusic.Helpers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Utilities {
    public static Drawable LoadImageFromWebOperations(String url) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
    public static Drawable LoadImageFromAssets(Context context, String imgName) {
        try
        {
            // get input stream
            InputStream ims = context.getAssets().open(imgName);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            return  d;
        }
        catch (IOException e) {
            return  null;
        }
    }
}
