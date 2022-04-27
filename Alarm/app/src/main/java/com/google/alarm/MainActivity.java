package com.google.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnTurnOn;
    Button btnTurnOff;
    TextView tvTimer;
    TimePicker timePicker;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // khởi tạo
        btnTurnOn = (Button) findViewById(R.id.alarm_on);
        btnTurnOff = (Button) findViewById(R.id.alarm_off);
        tvTimer = (TextView) findViewById(R.id.update_text);
        timePicker = (TimePicker) findViewById(R.id.timPicker);
        calendar = Calendar.getInstance();

        // cho phép truy cập vào hệ thống báo động
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // tạo intent để truyền dữ liệu từ main sang AlarmReceiver
        Intent intent = new Intent(MainActivity.this, AlarmManager.class);

        // set click vào turn on
        btnTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());

                // lấy giá trị và chuyển về string
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String strhour = String.valueOf(hour);
                String strmunite = String.valueOf(minute);

                //thếm số 0 nếu phút nhỏ hơn 10
                if(minute < 10 ) strmunite = "0" + strmunite;

                pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
                );

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                tvTimer.setText("Your time is " + strhour + ":" + strmunite);
            }
        });

        // set click vào turn off
        btnTurnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTimer.setText("Turn off");
            }
        });

    }
}