package com.example.foregroundservice.services;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyChannel extends Application {
    public static final String channelID = "ChannelID";

    @Override
    public void onCreate() {
        super.onCreate();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //channel prototype
            NotificationChannel notificationChannel = new NotificationChannel(channelID, "My Service", NotificationManager.IMPORTANCE_DEFAULT);

            //channel start
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
    }
}
