package com.example.part2.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.part2.data.AppDatabase;
import com.example.part2.data.CourseDao;
import com.example.part2.models.Course;
import com.example.part2.models.Student;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseRepository {

    private final CourseDao courseDao;
    private final ExecutorService executorService;

    public CourseRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        courseDao = db.courseDao();
        executorService = Executors.newSingleThreadExecutor();
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
}
