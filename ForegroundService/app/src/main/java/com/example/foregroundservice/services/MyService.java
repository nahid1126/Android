package com.example.foregroundservice.services;

import static com.example.foregroundservice.services.MyChannel.channelID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.foregroundservice.MainActivity;
import com.example.foregroundservice.R;

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //for notification drawer
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Notification notification = new NotificationCompat.Builder(this, channelID)
                .setContentTitle("My Notification")
                .setContentText("This is my notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent).build();

        startForeground(1, notification);
        return START_NOT_STICKY;
    }
}
