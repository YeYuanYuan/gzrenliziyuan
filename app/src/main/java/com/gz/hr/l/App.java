package com.gz.hr.l;

import android.app.Application;
import android.net.ConnectivityManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by linhui on 2017/8/16.
 */
public class App extends Application {

    public static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
