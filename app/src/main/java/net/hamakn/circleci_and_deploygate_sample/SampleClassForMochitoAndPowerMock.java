package net.hamakn.circleci_and_deploygate_sample;

import android.content.Context;
import android.util.Log;

public class SampleClassForMochitoAndPowerMock {
    private static String LOG_TAG = "sample";
    private Context mContext;

    public static void staticWork1() {
        MyApplication.getApp().getApplicationContext();
    }

    public static int staticWork2() {
        MyApplication.getApp().getApplicationContext();
        return 42;
    }

    public SampleClassForMochitoAndPowerMock(Context context) {
        Log.d(LOG_TAG, "new");
        mContext = context;
    }

    public boolean flagByContext() {
        return mContext.isRestricted();
    }

    public int logicWithFlag() {
        if (flagByContext()) {
            return 1;
        } else {
            return -1;
        }
    }

    public int logicWithStatic() {
        staticWork1();
        return staticWork2();
    }

    public boolean hasError() {
        try {
            logicWithFlag();
            return false;
        } catch (RuntimeException e) {
            return true;
        }
    }

    public int work1(int i) {
        Log.d(LOG_TAG, "do work1");
        return i * i;
    }

    public void doSomeWorks(int i) {
        Log.d(LOG_TAG, "start doSomeWorks");

        staticWork1();
        work1(i);
        work1(i);
        work1(i);
        staticWork1();

        Log.d(LOG_TAG, "end doSomeWorks");
    }
}
