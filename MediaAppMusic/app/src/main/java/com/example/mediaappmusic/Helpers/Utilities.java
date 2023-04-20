package com.example.mediaappmusic.Helpers;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mediaappmusic.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.regex.Pattern;

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
    public static void changeFragment(Context context, Fragment f, String id) {
        FragmentActivity fa = (FragmentActivity)context;
        FragmentManager fragmentManager = fa.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(
                context.getResources().getIdentifier(id, "id", context.getPackageName()),
                f
        );
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public static int getCOLOR_ACTIVE(Context context) {
        return ContextCompat.getColor(context, R.color.active);
    }
    public static int getCOLOR_WHITE(Context context) {
        return ContextCompat.getColor(context, R.color.white);
    }
    public static long roundingSeconds(long seconds) {
        return (long) Math.ceil(seconds / 1000f);
    }
    public static boolean checkMail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
