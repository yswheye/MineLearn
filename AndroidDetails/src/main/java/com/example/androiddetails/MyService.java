package com.example.androiddetails;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * class description
 *
 * @author garry
 * @version 1.0.0
 * @date 2018-04-17 11:04
 */
public class MyService extends Service {
    private final IBinder binder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    public void doSomething() {
        //service连接成功后，可以调用来处理一些任务
    }

    public class MyBinder extends Binder {

        MyService getService() {
            return MyService.this;
        }
    }
}
