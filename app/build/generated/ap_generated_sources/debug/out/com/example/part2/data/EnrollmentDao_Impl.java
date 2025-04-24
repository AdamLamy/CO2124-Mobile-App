package com.example.part2.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.part2.models.CourseStudentCrossRef;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EnrollmentDao_Impl implements EnrollmentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CourseStudentCrossRef> __insertionAdapterOfCourseStudentCrossRef;

  private final EntityDeletionOrUpdateAdapter<CourseStudentCrossRef> __deletionAdapterOfCourseStudentCrossRef;

  private final SharedSQLiteStatement __preparedStmtOfRemoveStudentFromCourse;

  private final SharedSQLiteStatement __preparedStmtOfRemoveAllStudentsFromCourse;

  private final SharedSQLiteStatement __preparedStmtOfRemoveStudentFromAllCourses;

  public EnrollmentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCourseStudentCrossRef = new EntityInsertionAdapter<CourseStudentCrossRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `CourseStudentCrossRef` (`courseId`,`studentId`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CourseStudentCrossRef entity) {
        statement.bindLong(1, entity.courseId);
        statement.bindLong(2, entity.studentId);
      }
    };
    this.__deletionAdapterOfCourseStudentCrossRef = new EntityDeletionOrUpdateAdapter<CourseStudentCrossRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `CourseStudentCrossRef` WHERE `courseId` = ? AND `studentId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CourseStudentCrossRef entity) {
        statement.bindLong(1, entity.courseId);
        statement.bindLong(2, entity.studentId);
      }
    };
    this.__preparedStmtOfRemoveStudentFromCourse = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM CourseStudentCrossRef WHERE courseId = ? AND studentId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveAllStudentsFromCourse = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM CourseStudentCrossRef WHERE courseId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveStudentFromAllCourses = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM CourseStudentCrossRef WHERE studentId = ?";
        return _query;
      }
    };
  }

  @Override
  public void enrollStudentInCourse(final CourseStudentCrossRef enrollment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCourseStudentCrossRef.insert(enrollment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void removeStudentFromCourse(final CourseStudentCrossRef enrollment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCourseStudentCrossRef.handle(enrollment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void removeStudentFromCourse(final int courseId, final int studentId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveStudentFromCourse.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, courseId);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, studentId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfRemoveStudentFromCourse.release(_stmt);
    }
  }

  @Override
  public void deleteEnrollment(final int courseId, final int studentId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveStudentFromCourse.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, courseId);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, studentId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfRemoveStudentFromCourse.release(_stmt);
    }
  }

  @Override
  public void removeAllStudentsFromCourse(final int courseId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveAllStudentsFromCourse.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, courseId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfRemoveAllStudentsFromCourse.release(_stmt);
    }
  }

  @Override
  public void removeStudentFromAllCourses(final int studentId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveStudentFromAllCourses.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, studentId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfRemoveStudentFromAllCourses.release(_stmt);
    }
  }

  @Override
  public int isStudentEnrolled(final int courseId, final int studentId) {
    final String _sql = "SELECT COUNT(*) FROM CourseStudentCrossRef WHERE courseId = ? AND studentId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, courseId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, studentId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
