package com.example.androiddetails;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * class description
 *
 * @author garry
 * @version 1.0.0
 * @date 2018-04-17 11:07
 */
public class BindServiceAct extends Activity {
    private MyService myService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((MyService.MyBinder) service).getService();
            //连接成功
            myService.doSomething();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
