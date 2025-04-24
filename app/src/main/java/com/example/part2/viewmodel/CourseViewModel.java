package com.example.part2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.part2.models.Course;
import com.example.part2.models.Student;
import com.example.part2.repository.CourseRepository;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private final CourseRepository repository;
    private final LiveData<List<Course>> allCoursesLiveData;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        repository = new CourseRepository(application);
        allCoursesLiveData = repository.getAllCoursesLiveData();
    }

    public LiveData<List<Course>> getAllCoursesLiveData() {
        return allCoursesLiveData;
    }

    public void insertCourse(Course course) {
        repository.insert(course);
    }

    public void updateCourse(Course course) {
        repository.update(course);
    }

    public void deleteCourse(Course course) {
        repository.delete(course);
    }

    public LiveData<Course> getCourseById(int courseId) {
        return repository.getCourseById(courseId);
    }

    public LiveData<List<Student>> getStudentsForCourse(int courseId) {
        return repository.getStudentsForCourse(courseId);
    }

    public void removeStudentFromCourse(int courseId, String userName) {
        repository.removeStudentFromCourse(courseId, userName);
    }

    public void updateStudent(String name, String email, String userName) {
        Student updatedStudent = new Student(name, email, userName);
        repository.updateStudent(updatedStudent);
    }

}
