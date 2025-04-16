package com.example.part2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.part2.models.CourseStudentCrossRef;
import com.example.part2.models.Student;
import com.example.part2.models.StudentWithCourses;
import com.example.part2.repository.EnrollmentRepository;
import com.example.part2.repository.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentViewModel(@NonNull Application application, EnrollmentRepository enrollmentRepository) {
        super(application);
        studentRepository = new StudentRepository(application);
        this.enrollmentRepository = enrollmentRepository;
    }

    public void insertStudent(Student student) {
        studentRepository.insert(student);
    }

    public void updateStudent(Student student) {
        studentRepository.update(student);
    }

    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    public LiveData<List<Student>> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public StudentWithCourses getCoursesForStudent(int studentId) {
        return studentRepository.getCoursesForStudent(studentId);
    }
    public Student getStudentByUserNameSync(String userName) {
        return studentRepository.getStudentByUserName(userName);
    }

    public long insertAndReturnId(Student student) {
        return studentRepository.insertAndReturnId(student);
    }

    public boolean isStudentEnrolled(int courseId, int studentId) {
        return enrollmentRepository.isStudentEnrolled(courseId, studentId);
    }

    public void enrollStudent(CourseStudentCrossRef enrollment) {
        enrollmentRepository.enrollStudent(enrollment);
    }

}
