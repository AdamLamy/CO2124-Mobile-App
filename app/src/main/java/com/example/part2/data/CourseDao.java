package com.example.part2.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.part2.models.Course;
import com.example.part2.models.Student;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    void insertCourse(Course course);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);

    @Query("SELECT * FROM courses WHERE courseId = :courseId LIMIT 1")
    LiveData<Course> getCourseById(int courseId);

    @Query("SELECT * FROM courses ORDER BY courseName ASC")
    List<Course> getAllCourses();

    @Query("SELECT * FROM students INNER JOIN CourseStudentCrossRef ON students.studentId = CourseStudentCrossRef.studentId WHERE CourseStudentCrossRef.courseId = :courseId")
    LiveData<List<Student>> getStudentsForCourse(int courseId);

    @Query("SELECT * FROM courses ORDER BY courseName ASC")
    LiveData<List<Course>> getAllCoursesLiveData();

}

