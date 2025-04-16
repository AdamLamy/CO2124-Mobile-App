package com.example.part2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.adapters.StudentAdapter;
import com.example.part2.viewmodel.CourseViewModel;

public class CourseDetailsActivity extends AppCompatActivity {

    private CourseViewModel courseViewModel;
    private StudentAdapter studentAdapter;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        TextView courseCode = findViewById(R.id.textDetailCourseCode);
        TextView courseName = findViewById(R.id.textDetailCourseName);
        TextView lecturerName = findViewById(R.id.textDetailLecturerName);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewStudents);

        studentAdapter = new StudentAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentAdapter);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        courseId = getIntent().getIntExtra("COURSE_ID", -1);
        if (courseId != -1) {
            courseViewModel.getCourseById(courseId).observe(this, course -> {
                if (course != null) {
                    courseCode.setText(course.getCourseCode());
                    courseName.setText(course.getCourseName());
                    lecturerName.setText(course.getLecturerName());
                }
            });

            courseViewModel.getStudentsForCourse(courseId).observe(this, students -> {
                studentAdapter.setStudentList(students);
            });

            View fabAddStudent = findViewById(R.id.fab_add_student);
            fabAddStudent.setOnClickListener(v -> {
                Intent intent = new Intent(this, AddStudentActivity.class);
                intent.putExtra("courseId", courseId);  // pass course ID!
                startActivity(intent);
            });

        }
    }
}

