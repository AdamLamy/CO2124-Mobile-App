package com.example.part2.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.part2.models.Student;
import com.example.part2.models.StudentWithCourses;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)  // avoid duplicate students (based on primary key)
    void insertStudent(Student student);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertAndReturnId(Student student);

    @Update
    void updateStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Transaction
    @Query("SELECT * FROM students WHERE studentId = :studentId")
    StudentWithCourses getCoursesForStudent(int studentId);

    @Query("SELECT * FROM students WHERE userName = :userName LIMIT 1")
    Student getStudentByUserName(String userName);

    @Query("SELECT * FROM students")
    LiveData<List<Student>> getAllStudents();

    @Query("SELECT * FROM students WHERE studentId = :id LIMIT 1")
    Student getStudentById(int id);
}

