package com.example.part2.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.part2.data.AppDatabase;
import com.example.part2.data.StudentDao;
import com.example.part2.models.Student;
import com.example.part2.models.StudentWithCourses;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentRepository {

    private final StudentDao studentDao;
    private final ExecutorService executorService;

    public StudentRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        studentDao = db.studentDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(Student student) {
        executorService.execute(() -> studentDao.insertStudent(student));
    }

    public void update(Student student) {
        executorService.execute(() -> studentDao.updateStudent(student));
    }

    public void delete(Student student) {
        executorService.execute(() -> studentDao.deleteStudent(student));
    }

    public LiveData<List<Student>> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public Student getStudentByUserName(String userName) {
        return studentDao.getStudentByUserName(userName);
    }

    public Student getStudentById(int studentId) {
        return studentDao.getStudentById(studentId);
    }

    public StudentWithCourses getCoursesForStudent(int studentId) {
        return studentDao.getCoursesForStudent(studentId);
    }


    public long insertAndReturnId(Student student) {
        return studentDao.insertAndReturnId(student);  // this is a synchronous method, call it in a background thread!
    }
}

