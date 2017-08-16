package com.gz.hr.l;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.gz.hr.l.entity.ConnextEntity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by linhui on 2017/8/16.
 */
public class ConnectReceiver extends BroadcastReceiver {

    /**
     * 功能：检查网络链接的状态
     */
    public static boolean isConnect() {
        // 获取手机所有连接管理对象
        ConnectivityManager connectivityManager = (ConnectivityManager) App.application.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            // 判断当前网络是否已经连接
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case ConnectivityManager.CONNECTIVITY_ACTION:
                EventBus.getDefault().post(new ConnextEntity());
                break;

        }
    }



}
