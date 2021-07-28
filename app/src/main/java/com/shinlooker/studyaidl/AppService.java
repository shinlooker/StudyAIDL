package com.shinlooker.studyaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class AppService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IWebInterface.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public String setData(String data) throws RemoteException {
                AppService.this.data = data;
                return "22222";
            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Service1111", "Service started");

        new Thread(){
            @Override
            public void run() {
                super.run();
                running = true;
                while (running){
                    Log.i("service---",data);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Service1111", "Service stoped");
        running = false;
    }

    private String data = "默认数据";
    private boolean running = false;
}
