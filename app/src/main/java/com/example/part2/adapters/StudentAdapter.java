package com.example.part2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.R;
import com.example.part2.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList = new ArrayList<>();

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.name.setText(student.getName());
        holder.email.setText(student.getEmail());
        holder.matric.setText(student.getUserName());  // Assuming username is the matric number
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void setStudentList(List<Student> students) {
        this.studentList = students;
        notifyDataSetChanged();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, matric;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textStudentName);
            email = itemView.findViewById(R.id.textStudentEmail);
            matric = itemView.findViewById(R.id.textStudentMatric);
        }
    }
}

