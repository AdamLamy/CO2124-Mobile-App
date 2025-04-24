package com.example.part2.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.part2.models.Course;
import com.example.part2.models.Student;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CourseDao_Impl implements CourseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Course> __insertionAdapterOfCourse;

  private final EntityDeletionOrUpdateAdapter<Course> __deletionAdapterOfCourse;

  private final EntityDeletionOrUpdateAdapter<Course> __updateAdapterOfCourse;

  public CourseDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCourse = new EntityInsertionAdapter<Course>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `courses` (`courseId`,`courseCode`,`courseName`,`lecturerName`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Course entity) {
        statement.bindLong(1, entity.getCourseId());
        if (entity.getCourseCode() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getCourseCode());
        }
        if (entity.getCourseName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCourseName());
        }
        if (entity.getLecturerName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getLecturerName());
        }
      }
    };
    this.__deletionAdapterOfCourse = new EntityDeletionOrUpdateAdapter<Course>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `courses` WHERE `courseId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Course entity) {
        statement.bindLong(1, entity.getCourseId());
      }
    };
    this.__updateAdapterOfCourse = new EntityDeletionOrUpdateAdapter<Course>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `courses` SET `courseId` = ?,`courseCode` = ?,`courseName` = ?,`lecturerName` = ? WHERE `courseId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Course entity) {
        statement.bindLong(1, entity.getCourseId());
        if (entity.getCourseCode() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getCourseCode());
        }
        if (entity.getCourseName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCourseName());
        }
        if (entity.getLecturerName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getLecturerName());
        }
        statement.bindLong(5, entity.getCourseId());
      }
    };
  }

  @Override
  public void insertCourse(final Course course) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCourse.insert(course);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteCourse(final Course course) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCourse.handle(course);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateCourse(final Course course) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCourse.handle(course);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<Course> getCourseById(final int courseId) {
    final String _sql = "SELECT * FROM courses WHERE courseId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, courseId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"courses"}, false, new Callable<Course>() {
      @Override
      @Nullable
      public Course call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfCourseCode = CursorUtil.getColumnIndexOrThrow(_cursor, "courseCode");
          final int _cursorIndexOfCourseName = CursorUtil.getColumnIndexOrThrow(_cursor, "courseName");
          final int _cursorIndexOfLecturerName = CursorUtil.getColumnIndexOrThrow(_cursor, "lecturerName");
          final Course _result;
          if (_cursor.moveToFirst()) {
            _result = new Course();
            final int _tmpCourseId;
            _tmpCourseId = _cursor.getInt(_cursorIndexOfCourseId);
            _result.setCourseId(_tmpCourseId);
            final String _tmpCourseCode;
            if (_cursor.isNull(_cursorIndexOfCourseCode)) {
              _tmpCourseCode = null;
            } else {
              _tmpCourseCode = _cursor.getString(_cursorIndexOfCourseCode);
            }
            _result.setCourseCode(_tmpCourseCode);
            final String _tmpCourseName;
            if (_cursor.isNull(_cursorIndexOfCourseName)) {
              _tmpCourseName = null;
            } else {
              _tmpCourseName = _cursor.getString(_cursorIndexOfCourseName);
            }
            _result.setCourseName(_tmpCourseName);
            final String _tmpLecturerName;
            if (_cursor.isNull(_cursorIndexOfLecturerName)) {
              _tmpLecturerName = null;
            } else {
              _tmpLecturerName = _cursor.getString(_cursorIndexOfLecturerName);
            }
            _result.setLecturerName(_tmpLecturerName);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public List<Course> getAllCourses() {
    final String _sql = "SELECT * FROM courses ORDER BY courseName ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
      final int _cursorIndexOfCourseCode = CursorUtil.getColumnIndexOrThrow(_cursor, "courseCode");
      final int _cursorIndexOfCourseName = CursorUtil.getColumnIndexOrThrow(_cursor, "courseName");
      final int _cursorIndexOfLecturerName = CursorUtil.getColumnIndexOrThrow(_cursor, "lecturerName");
      final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Course _item;
        _item = new Course();
        final int _tmpCourseId;
        _tmpCourseId = _cursor.getInt(_cursorIndexOfCourseId);
        _item.setCourseId(_tmpCourseId);
        final String _tmpCourseCode;
        if (_cursor.isNull(_cursorIndexOfCourseCode)) {
          _tmpCourseCode = null;
        } else {
          _tmpCourseCode = _cursor.getString(_cursorIndexOfCourseCode);
        }
        _item.setCourseCode(_tmpCourseCode);
        final String _tmpCourseName;
        if (_cursor.isNull(_cursorIndexOfCourseName)) {
          _tmpCourseName = null;
        } else {
          _tmpCourseName = _cursor.getString(_cursorIndexOfCourseName);
        }
        _item.setCourseName(_tmpCourseName);
        final String _tmpLecturerName;
        if (_cursor.isNull(_cursorIndexOfLecturerName)) {
          _tmpLecturerName = null;
        } else {
          _tmpLecturerName = _cursor.getString(_cursorIndexOfLecturerName);
        }
        _item.setLecturerName(_tmpLecturerName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Student>> getStudentsForCourse(final int courseId) {
    final String _sql = "SELECT * FROM students INNER JOIN CourseStudentCrossRef ON students.studentId = CourseStudentCrossRef.studentId WHERE CourseStudentCrossRef.courseId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, courseId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"students",
        "CourseStudentCrossRef"}, false, new Callable<List<Student>>() {
      @Override
      @Nullable
      public List<Student> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "studentId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "userName");
          final List<Student> _result = new ArrayList<Student>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Student _item;
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpUserName;
            if (_cursor.isNull(_cursorIndexOfUserName)) {
              _tmpUserName = null;
            } else {
              _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
            }
            _item = new Student(_tmpName,_tmpEmail,_tmpUserName);
            final int _tmpStudentId;
            _tmpStudentId = _cursor.getInt(_cursorIndexOfStudentId);
            _item.setStudentId(_tmpStudentId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Course>> getAllCoursesLiveData() {
    final String _sql = "SELECT * FROM courses ORDER BY courseName ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"courses"}, false, new Callable<List<Course>>() {
      @Override
      @Nullable
      public List<Course> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfCourseCode = CursorUtil.getColumnIndexOrThrow(_cursor, "courseCode");
          final int _cursorIndexOfCourseName = CursorUtil.getColumnIndexOrThrow(_cursor, "courseName");
          final int _cursorIndexOfLecturerName = CursorUtil.getColumnIndexOrThrow(_cursor, "lecturerName");
          final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Course _item;
            _item = new Course();
            final int _tmpCourseId;
            _tmpCourseId = _cursor.getInt(_cursorIndexOfCourseId);
            _item.setCourseId(_tmpCourseId);
            final String _tmpCourseCode;
            if (_cursor.isNull(_cursorIndexOfCourseCode)) {
              _tmpCourseCode = null;
            } else {
              _tmpCourseCode = _cursor.getString(_cursorIndexOfCourseCode);
            }
            _item.setCourseCode(_tmpCourseCode);
            final String _tmpCourseName;
            if (_cursor.isNull(_cursorIndexOfCourseName)) {
              _tmpCourseName = null;
            } else {
              _tmpCourseName = _cursor.getString(_cursorIndexOfCourseName);
            }
            _item.setCourseName(_tmpCourseName);
            final String _tmpLecturerName;
            if (_cursor.isNull(_cursorIndexOfLecturerName)) {
              _tmpLecturerName = null;
            } else {
              _tmpLecturerName = _cursor.getString(_cursorIndexOfLecturerName);
            }
            _item.setLecturerName(_tmpLecturerName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
