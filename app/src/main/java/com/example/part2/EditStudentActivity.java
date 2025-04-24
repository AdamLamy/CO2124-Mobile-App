package com.example.part2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.part2.models.Student;
import com.example.part2.viewmodel.CourseViewModel;
import com.example.part2.viewmodel.StudentViewModel;
import com.example.part2.viewmodel.StudentViewModelFactory;

public class EditStudentActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;

    EditText editTextName, editTextEmail, editTextMatric;
    Button buttonSave;
    String studentId; // Assuming you have a unique ID to identify students

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        StudentViewModelFactory factory = new StudentViewModelFactory(getApplication());
        studentViewModel = new ViewModelProvider(this, factory).get(StudentViewModel.class);


        editTextName = findViewById(R.id.editTextStudentName);
        editTextEmail = findViewById(R.id.editTextStudentEmail);
        editTextMatric = findViewById(R.id.editTextStudentMatric);
        buttonSave = findViewById(R.id.buttonSave);

        Intent intent = getIntent();
        studentId = intent.getStringExtra("studentId");
        editTextName.setText(intent.getStringExtra("name"));
        editTextEmail.setText(intent.getStringExtra("email"));
        editTextMatric.setText(intent.getStringExtra("matric"));

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedName = editTextName.getText().toString();
                String updatedEmail = editTextEmail.getText().toString();
                String updatedMatric = editTextMatric.getText().toString();

                int studentIdInt = Integer.parseInt(studentId);
                Student updatedStudent = new Student(updatedName, updatedEmail, updatedMatric);
                updatedStudent.setStudentId(studentIdInt); // Important for update!

                // Update in database
                studentViewModel.updateStudent(updatedStudent);


                Intent resultIntent = new Intent();
                resultIntent.putExtra("studentId", studentId);
                resultIntent.putExtra("updatedName", updatedName);
                resultIntent.putExtra("updatedEmail", updatedEmail);
                resultIntent.putExtra("updatedMatric", updatedMatric);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}

