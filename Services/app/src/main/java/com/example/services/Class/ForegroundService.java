package com.example.services.Class;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.services.R;

public class ForegroundService extends Service {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(
                () -> {
                    while (true) {
                        try {
                            Log.d("Service", "Foreground Service is running..............");
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();

        //put the foreground service
        final String id = "Foreground Service ID";
        NotificationChannel channel=new NotificationChannel(
                id,
                id,
                NotificationManager.IMPORTANCE_LOW
        );

        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notification = new Notification.Builder(this, id)
                .setContentText("Foreground Service is Running..")
                .setContentTitle("Foreground service enabled")
                .setSmallIcon(R.drawable.ic_launcher_background);

        startForeground(1001, notification.build());

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
