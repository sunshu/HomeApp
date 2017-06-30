package me.sunshu.homeapp.application;

import android.app.Application;

/**
 * 创建时间：  17-6-29.
 * 编写人： sunshu
 * 功能描述：
 */

public class HomeApplication extends Application {
    private static HomeApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
