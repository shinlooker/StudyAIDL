package com.shinlooker.anotherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.shinlooker.studyaidl.IWebInterface;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();
        intent.setComponent(new ComponentName("com.shinlooker.studyaidl", "com.shinlooker.studyaidl.AppService"));

    }

    public void startService(View view) {
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
//         startService(intent);
    }

    public void stopService(View view) {
        unbindService(connection);
        binder = null;
    }


    public void SyncData(View view) {
        if (binder != null) {
            try {
                String string = binder.setData("新数据");
                Log.i("1111",string);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }


    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = IWebInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IWebInterface binder = null;


}