package com.example.part2.repository;

import android.app.Application;

import com.example.part2.data.AppDatabase;
import com.example.part2.data.EnrollmentDao;
import com.example.part2.models.CourseStudentCrossRef;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EnrollmentRepository {

    private final EnrollmentDao enrollmentDao;
    private final ExecutorService executorService;

    public EnrollmentRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        enrollmentDao = db.enrollmentDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void enrollStudent(CourseStudentCrossRef crossRef) {
        executorService.execute(() -> enrollmentDao.enrollStudentInCourse(crossRef));
    }

    public boolean isStudentEnrolled(int courseId, int studentId) {
        return enrollmentDao.isStudentEnrolled(courseId, studentId) > 0;
    }

    public void removeStudentFromCourse(int courseId, int studentId) {
        executorService.execute(() -> enrollmentDao.deleteEnrollment(courseId, studentId));
    }
}
