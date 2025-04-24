package com.example.part2.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.CourseDetailsActivity;
import com.example.part2.R;
import com.example.part2.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList = new ArrayList<>();
    private Context context;

    public StudentAdapter(Context context) {
        this.context = context;
    }

    public void setStudentList(List<Student> students) {
        this.studentList = students;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.textViewName.setText(student.getName());
        holder.textViewEmail.setText(student.getEmail());
        holder.textViewMatric.setText(student.getUserName());

        holder.itemView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Select Action")
                    .setItems(new CharSequence[]{"Edit", "Remove"}, (dialog, which) -> {
                        if (context instanceof CourseDetailsActivity) {
                            ((CourseDetailsActivity) context).handleStudentAction(which, student, position);
                        }
                    });
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void removeStudent(int position) {
        studentList.remove(position);
        notifyItemRemoved(position);
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewEmail;
        TextView textViewMatric;

        StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName); // Your layout should have this
            textViewEmail = itemView.findViewById(R.id.textViewStudentEmail);
            textViewMatric = itemView.findViewById(R.id.textViewStudentMatric);
        }
    }
}
