package com.example.part2.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.part2.models.CourseStudentCrossRef;

@Dao
public interface EnrollmentDao {

    @Insert
    void enrollStudentInCourse(CourseStudentCrossRef enrollment);

    @Delete
    void removeStudentFromCourse(CourseStudentCrossRef enrollment);

    @Query("DELETE FROM CourseStudentCrossRef WHERE courseId = :courseId AND studentId = :studentId")
    void deleteEnrollment(int courseId, int studentId);

    @Query("DELETE FROM CourseStudentCrossRef WHERE courseId = :courseId")
    void removeAllStudentsFromCourse(int courseId);

    @Query("DELETE FROM CourseStudentCrossRef WHERE studentId = :studentId")
    void removeStudentFromAllCourses(int studentId);

    @Query("SELECT COUNT(*) FROM CourseStudentCrossRef WHERE courseId = :courseId AND studentId = :studentId")
    int isStudentEnrolled(int courseId, int studentId);
}

