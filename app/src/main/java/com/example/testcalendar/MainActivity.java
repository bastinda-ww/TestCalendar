package com.example.testcalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.google.gson.Gson;

import java.io.FileReader;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView taskList;
    private TasksAdapter tasksAdapter;
    CalendarView calendarView;
    public static ArrayList<Task> listOfTask = new ArrayList<>();
    private final String json = "[{\"description\":\"some description 1\",\"endDate\":\"Feb 13, 2022 23:00:00\",\"id\":0,\"startDate\":\"Feb 13, 2022 22:22:22\",\"title\":\"my title 1\"},{\"description\":\"some description\",\"endDate\":\"Feb 13, 2022 23:00:00\",\"id\":1,\"startDate\":\"Feb 12, 2022 21:22:22\",\"title\":\"my title 2\"},{\"description\":\"some description\",\"endDate\":\"Feb 13, 2022 23:00:00\",\"id\":2,\"startDate\":\"Feb 13, 2022 20:22:22\",\"title\":\"my title 3\"},{\"description\":\"some description\",\"endDate\":\"Feb 13, 2022 23:00:00\",\"id\":3,\"startDate\":\"Feb 13, 2022 18:22:22\",\"title\":\"my title 4\"}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = findViewById(R.id.recyclerViewList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        taskList.setLayoutManager(layoutManager);
        taskList.setHasFixedSize(true);
        Gson gson = new Gson();

//        ArrayList<Task> tasks = new ArrayList<>();
//        Task task1 = new Task(0,"my title 1","some description 1", Timestamp.valueOf("2022-02-13 22:22:22"), Timestamp.valueOf("2022-02-13 23:00:00"));
//        Task task2 = new Task(1,"my title 2","some description", Timestamp.valueOf("2022-02-12 21:22:22"), Timestamp.valueOf("2022-02-13 23:00:00"));
//        Task task3 = new Task(2,"my title 3","some description", Timestamp.valueOf("2022-02-13 20:22:22"), Timestamp.valueOf("2022-02-13 23:00:00"));
//        Task task4 = new Task(3,"my title 4","some description", Timestamp.valueOf("2022-02-13 18:22:22"), Timestamp.valueOf("2022-02-13 23:00:00"));

//        tasks.add(task1);
//        tasks.add(task2);
//        tasks.add(task3);
//        tasks.add(task4);
        Task[] tasks = gson.fromJson(json, Task[].class);
        listOfTask.addAll(Arrays.asList(tasks));

        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOnMonth) {
                String [] titleArr = new String[31];
                listOfTask.forEach(task -> {
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(task.getStartDate().toInstant(), ZoneId.systemDefault());
                    if(localDateTime.getDayOfMonth() == dayOnMonth) {
                        titleArr[localDateTime.getHour()] = task.getTitle();
                    }
                });

                tasksAdapter = new TasksAdapter(titleArr);
                taskList.setAdapter(tasksAdapter);
            }
        });



//
//
//
//        System.out.println("test srt\n");
//        Task task1 = new Task(0,"my title ","some description", Timestamp.valueOf("2022-02-13 22:22:22"), Timestamp.valueOf("2022-02-13 23:00:00"));
//        Gson gson1 = new Gson();
//        String str = gson1.toJson(task1);
//        System.out.println(str);
//        Task task2 = gson1.fromJson(str, Task.class);
//        System.out.println(task2.toString());
//
//
//        //ArrayList<Task> listOfTask = new ArrayList<>();
//        listOfTask.add(task2);
//        listOfTask.add(new Task());
//        listOfTask.get(0).getDescription();
//        Task task3 = listOfTask.get(0);
//        listOfTask.size();
//
//
//        LocalDateTime ldt = LocalDateTime.ofInstant(task1.getStartDate().toInstant(), ZoneId.systemDefault());
//
//        //распарсить Timestamp на год, месяц, день, часы, минуты, секунды(если есть)
//        System.out.println(ldt.getDayOfMonth() + "." + ldt.getMonth().getValue());
//        System.out.println(ldt.getHour() + ":" + ldt.getMinute());
//
//        ldt = LocalDateTime.ofInstant(task1.getEndDate().toInstant(), ZoneId.systemDefault());
//        System.out.println(ldt.getDayOfMonth() + "." + ldt.getMonth().getValue());
//        System.out.println(ldt.getHour() + ":" + ldt.getMinute());


        //FileReader fileReader = new FileReader("task.json");
    }

}