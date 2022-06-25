package com.nahid.setalarm;

import static android.app.AlarmManager.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.nahid.setalarm.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MaterialTimePicker timePicker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createNotificationChannel();

        binding.txtTime.setOnClickListener(view -> showTimePicker());

        binding.btnSetAlarm.setOnClickListener(view -> {
            if(binding.txtTime.getText().equals("Set Time")) {
                Toast.makeText(this, "Set the time first", Toast.LENGTH_SHORT).show();
            } else {
                setAlarm();
            }
        });

        binding.btnCancelAlarm.setOnClickListener(view -> cancelAlarm());
    }

    private void cancelAlarm() {
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Cancelled!!", Toast.LENGTH_SHORT).show();

    }

    private void setAlarm() {

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
       // alarmManager.setExact(RTC, calendar.getTimeInMillis(), pendingIntent);
        alarmManager.set(RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        //alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(this, "Alarm Set Successful!!", Toast.LENGTH_SHORT).show();

    }

    private void showTimePicker() {
        timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();

        timePicker.show(getSupportFragmentManager(), "nahidId");
        timePicker.addOnPositiveButtonClickListener(view -> {
            if(timePicker.getHour() > 12) {
                binding.txtTime.setText(String.format("%02d", (timePicker.getHour() - 12)) + " : " + String.format("%02d", timePicker.getMinute()) + " PM");
            } else if(timePicker.getHour() == 12) {
                binding.txtTime.setText(timePicker.getHour() + " : " + timePicker.getMinute() + " PM");
            } else {
                binding.txtTime.setText(timePicker.getHour() + " : " + timePicker.getMinute() + " AM");
            }

            calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
            calendar.set(Calendar.MINUTE, timePicker.getMinute());
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        });
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Nahid Reminder Channel";
            String desc = "Channel for Alarm manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("nahidId", name, importance);
            channel.setDescription(desc);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
}