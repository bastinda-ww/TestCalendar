package com.example.testcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import static java.sql.Types.TIMESTAMP;

public class DescriptionActivity extends AppCompatActivity {
    public static Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_description);


        TextView titleTextView = findViewById(R.id.titleOfEvent);
        TextView startDateTextView = findViewById(R.id.textDateStart);
        TextView startTimeTextView = findViewById(R.id.textTimeStart);
        TextView endDateTextView = findViewById(R.id.textDateEnd);
        TextView endTimeTextView = findViewById(R.id.textTimeEnd);
        TextView descriptionTextView = findViewById(R.id.description);

        titleTextView.setText(task.getTitle());
        Timestamp tmstmp;
        tmstmp = task.getStartDate();
        LocalDateTime ldt = LocalDateTime.ofInstant(task.getStartDate().toInstant(), ZoneId.systemDefault());

        //распарсить Timestamp на год, месяц, день, часы, минуты, секунды(если есть)
        startDateTextView.setText(ldt.getDayOfMonth() + "." + ldt.getMonth().getValue());
        startTimeTextView.setText(ldt.getHour() + ":" + ldt.getMinute());

        ldt = LocalDateTime.ofInstant(task.getEndDate().toInstant(), ZoneId.systemDefault());
        endDateTextView.setText(ldt.getDayOfMonth() + "." + ldt.getMonth().getValue());
        endTimeTextView.setText(ldt.getHour() + ":" + ldt.getMinute());
        descriptionTextView.setText(task.getDescription());


        Button saveButton = findViewById(R.id.backButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception exception){

                }
            }
        });
    }
}
