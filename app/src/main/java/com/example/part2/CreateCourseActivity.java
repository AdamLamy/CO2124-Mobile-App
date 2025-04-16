package com.example.part2;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.part2.models.Course;
import com.example.part2.viewmodel.CourseViewModel;

public class CreateCourseActivity extends AppCompatActivity {

    private EditText editCourseCode, editCourseName, editLecturerName;
    private CourseViewModel courseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        editCourseCode = findViewById(R.id.editCourseCode);
        editCourseName = findViewById(R.id.editCourseName);
        editLecturerName = findViewById(R.id.editLecturerName);
        Button createButton = findViewById(R.id.buttonCreateCourse);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        createButton.setOnClickListener(v -> saveCourse());
    }

    private void saveCourse() {
        String code = editCourseCode.getText().toString().trim();
        String name = editCourseName.getText().toString().trim();
        String lecturer = editLecturerName.getText().toString().trim();

        if (TextUtils.isEmpty(code) || TextUtils.isEmpty(name) || TextUtils.isEmpty(lecturer)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        Course newCourse = new Course();
        newCourse.setCourseCode(code);
        newCourse.setCourseName(name);
        newCourse.setLecturerName(lecturer);

        courseViewModel.insertCourse(newCourse);
        Toast.makeText(this, "Course Created!", Toast.LENGTH_SHORT).show();
        finish(); // return to MainActivity
    }
}

