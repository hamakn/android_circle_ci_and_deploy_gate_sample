package net.hamakn.circleci_and_deploygate_sample;

import android.app.Application;

public class MyApplication extends Application {
    static private MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getApp() {
        return sInstance;
    }
}
