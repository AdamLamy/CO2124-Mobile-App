package com.example.part2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StudentDetailsActivity extends AppCompatActivity {

    TextView textViewName, textViewEmail, textViewMatric;
    ListView listViewCourses;
    ArrayAdapter<String> coursesAdapter;
    ArrayList<String> coursesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        textViewName = findViewById(R.id.textViewStudentName);
        textViewEmail = findViewById(R.id.textViewStudentEmail);
        textViewMatric = findViewById(R.id.textViewStudentMatric);
        listViewCourses = findViewById(R.id.listViewCourses);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String matric = intent.getStringExtra("matric");
        coursesList = intent.getStringArrayListExtra("courses");

        textViewName.setText("Name: " + name);
        textViewEmail.setText("Email: " + email);
        textViewMatric.setText("Matric No: " + matric);

        coursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coursesList);
        listViewCourses.setAdapter(coursesAdapter);
    }
}
