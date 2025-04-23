package com.example.part2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.part2.repository.EnrollmentRepository;

public class StudentViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;
    private final EnrollmentRepository enrollmentRepository;

    public StudentViewModelFactory(Application application) {
        this.application = application;
        this.enrollmentRepository = new EnrollmentRepository(application); // Or pass externally if needed
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StudentViewModel.class)) {
            return (T) new StudentViewModel(application, enrollmentRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

