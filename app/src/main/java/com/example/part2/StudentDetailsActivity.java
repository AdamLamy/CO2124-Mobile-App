package com.example.part2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.adapters.CourseAdapter;
import com.example.part2.data.AppDatabase;
import com.example.part2.models.Course;
import com.example.part2.models.StudentWithCourses;

import java.util.ArrayList;

public class StudentDetailsActivity extends AppCompatActivity {

    TextView textViewName, textViewEmail, textViewMatric;
    RecyclerView recyclerViewCourses;
    CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        textViewName = findViewById(R.id.textViewStudentName);
        textViewEmail = findViewById(R.id.textViewStudentEmail);
        textViewMatric = findViewById(R.id.textViewStudentMatric);
        recyclerViewCourses = findViewById(R.id.recyclerViewCourses);

        recyclerViewCourses.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter = new CourseAdapter();
        recyclerViewCourses.setAdapter(courseAdapter);

        // Get studentId
        int studentId = getIntent().getIntExtra("studentId", -1);

        if (studentId != -1) {
            // Fetch student details and courses from the database
            new Thread(() -> {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                StudentWithCourses studentWithCourses = db.studentDao().getStudentWithCourses(studentId);

                runOnUiThread(() -> {
                    if (studentWithCourses != null) {
                        textViewName.setText("Name: " + studentWithCourses.student.getName());
                        textViewEmail.setText("Email: " + studentWithCourses.student.getEmail());
                        textViewMatric.setText("Matric No: " + studentWithCourses.student.getUserName());

                        courseAdapter.setCourseList(studentWithCourses.courses);
                    }
                });
            }).start();
        }
    }
}

