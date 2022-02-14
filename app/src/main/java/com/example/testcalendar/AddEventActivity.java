package com.example.testcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddEventActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //добавить сохренение данных из полей формы в элемент класса Task

                    Intent intent = new Intent(AddEventActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception exception){

                }
            }
        });
    }
}
