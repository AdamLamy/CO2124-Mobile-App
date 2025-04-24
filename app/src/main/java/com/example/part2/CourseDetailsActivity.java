package com.example.part2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.part2.models.Student;


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

        studentAdapter = new StudentAdapter(this);

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

    // Move this outside onCreate
    public void handleStudentAction(int action, Student student, int position) {
        switch (action) {
            case 0: // Edit
                Intent editIntent = new Intent(this, EditStudentActivity.class);
                editIntent.putExtra("studentId", String.valueOf(student.getStudentId())); // Pass as String or int
                editIntent.putExtra("name", student.getName());
                editIntent.putExtra("email", student.getEmail());
                editIntent.putExtra("matric", student.getUserName());
                startActivityForResult(editIntent, 1); // Handle result below
                break;

            case 1: // Remove
                courseViewModel.removeStudentFromCourse(courseId, student.getUserName());
                studentAdapter.removeStudent(position); // Update UI
                break;
        }
    }

    // Move this outside onCreate too
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String updatedName = data.getStringExtra("updatedName");
            String updatedEmail = data.getStringExtra("updatedEmail");
            String updatedMatric = data.getStringExtra("updatedMatric");

            courseViewModel.updateStudent(updatedName, updatedEmail, updatedMatric); // You need this in ViewModel
        }
    }
}


