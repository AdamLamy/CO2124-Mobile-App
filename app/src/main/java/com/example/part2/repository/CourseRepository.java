package com.example.part2.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.part2.data.AppDatabase;
import com.example.part2.data.CourseDao;
import com.example.part2.data.EnrollmentDao;
import com.example.part2.data.StudentDao;
import com.example.part2.models.Course;
import com.example.part2.models.Student;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseRepository {

    private final EnrollmentDao enrollmentDao;
    private final CourseDao courseDao;
    private final ExecutorService executorService;

    private final StudentDao studentDao;
//    private final CourseStudentCrossRefDao crossRefDao;

    public CourseRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        courseDao = db.courseDao();
        executorService = Executors.newSingleThreadExecutor();
        studentDao = db.studentDao();
        enrollmentDao = db.enrollmentDao();
//        crossRefDao = db.courseStudentCrossRefDao();
    }

    public void insert(Course course) {
        executorService.execute(() -> courseDao.insertCourse(course));
    }

    public void update(Course course) {
        executorService.execute(() -> courseDao.updateCourse(course));
    }

    public void delete(Course course) {
        executorService.execute(() -> courseDao.deleteCourse(course));
    }

    public LiveData<List<Course>> getAllCoursesLiveData() {
        return courseDao.getAllCoursesLiveData();
    }

    // Get students for a course (LiveData for UI)
    public LiveData<List<Student>> getStudentsForCourse(int courseId) {
        return courseDao.getStudentsForCourse(courseId);
    }

    public LiveData<Course> getCourseById(int courseId) {
        return courseDao.getCourseById(courseId);
    }

    public void updateStudent(Student student) {
        executorService.execute(() -> studentDao.updateStudent(student));
    }

    public void removeStudentFromCourse(int courseId, String userName) {
        executorService.execute(() -> {
            // Find student by userName
            Student student = studentDao.getStudentByUserName(userName);
            if (student != null) {
                int studentId = student.getStudentId();
                // Assuming crossRefDao exists and is used for enrollments
                enrollmentDao.removeStudentFromCourse(courseId, studentId);
            }
        });
    }


}
