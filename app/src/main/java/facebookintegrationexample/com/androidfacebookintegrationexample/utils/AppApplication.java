package facebookintegrationexample.com.androidfacebookintegrationexample.utils;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by Himanshu on 11/22/2016.
 */

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
