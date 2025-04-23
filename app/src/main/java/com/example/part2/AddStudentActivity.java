package com.example.part2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.part2.models.CourseStudentCrossRef;
import com.example.part2.models.Student;
import com.example.part2.viewmodel.StudentViewModel;
import com.example.part2.viewmodel.StudentViewModelFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddStudentActivity extends AppCompatActivity {
    private EditText editTextName, editTextEmail, editTextMatric;
    private Button buttonAddStudent;
    private StudentViewModel studentViewModel;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        courseId = getIntent().getIntExtra("courseId", -1);


        //studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        StudentViewModelFactory factory = new StudentViewModelFactory(getApplication());
        studentViewModel = new ViewModelProvider(this, factory).get(StudentViewModel.class);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMatric = findViewById(R.id.editTextMatric);
        buttonAddStudent = findViewById(R.id.buttonAddStudent);

        buttonAddStudent.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String matric = editTextMatric.getText().toString().trim();

            if (courseId == -1) {
                Toast.makeText(this, "Error: Course ID not provided.", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            if (name.isEmpty() || email.isEmpty() || matric.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                Student existingStudent = studentViewModel.getStudentByUserNameSync(matric);

                if (existingStudent == null) {
                    Student newStudent = new Student(name, email, matric);
                    int newId = (int) studentViewModel.insertAndReturnId(newStudent);
                    CourseStudentCrossRef enrollment = new CourseStudentCrossRef(courseId, newId);
                    studentViewModel.enrollStudent(enrollment);

                    runOnUiThread(() -> {
                        Toast.makeText(this, "Student added and enrolled", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                } else {
                    boolean isEnrolled = studentViewModel.isStudentEnrolled(courseId, existingStudent.getStudentId());

                    runOnUiThread(() -> {
                        if (isEnrolled) {
                            Toast.makeText(this, "Student already enrolled", Toast.LENGTH_SHORT).show();
                        } else {
                            CourseStudentCrossRef enrollment = new CourseStudentCrossRef(courseId, existingStudent.getStudentId());
                            studentViewModel.enrollStudent(enrollment);
                            Toast.makeText(this, "Student enrolled to course", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
            });
        });
    }
}

