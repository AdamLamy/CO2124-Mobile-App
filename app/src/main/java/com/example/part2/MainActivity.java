package com.example.part2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.adapters.CourseAdapter;
import com.example.part2.viewmodel.CourseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private CourseViewModel courseViewModel;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        courseAdapter = new CourseAdapter();
        recyclerView.setAdapter(courseAdapter);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCoursesLiveData().observe(this, courses -> {
            courseAdapter.setCourseList(courses);
        });

        FloatingActionButton fab = findViewById(R.id.fabAddCourse);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateCourseActivity.class);
            startActivity(intent);
        });

        courseAdapter.setOnCourseClickListener(course -> {
            Intent intent = new Intent(MainActivity.this, CourseDetailsActivity.class);
            intent.putExtra("COURSE_ID", course.getCourseId());
            startActivity(intent);
        });

        courseAdapter.setOnCourseLongClickListener(course -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Delete Course")
                    .setMessage("Are you sure you want to delete " + course.getCourseCode() + "?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        courseViewModel.deleteCourse(course);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        TextView textEmptyCourses = findViewById(R.id.textEmptyCourses);

        // Observe the course list
        courseViewModel.getAllCoursesLiveData().observe(this, courses -> {
            courseAdapter.setCourseList(courses);
            if (courses == null || courses.isEmpty()) {
                textEmptyCourses.setVisibility(View.VISIBLE);
            } else {
                textEmptyCourses.setVisibility(View.GONE);
            }
        });

    }
}

