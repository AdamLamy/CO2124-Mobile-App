package com.example.part2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.R;
import com.example.part2.models.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> courseList = new ArrayList<>();

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course currentCourse = courseList.get(position);
        holder.courseCode.setText(currentCourse.getCourseCode());
        holder.courseName.setText(currentCourse.getCourseName());
        holder.lecturerName.setText(currentCourse.getLecturerName());

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onCourseClick(currentCourse);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onCourseLongClick(currentCourse);
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public void setCourseList(List<Course> courses) {
        this.courseList = courses;
        notifyDataSetChanged();
    }

    public interface OnCourseClickListener {
        void onCourseClick(Course course);
    }

    private OnCourseClickListener clickListener;

    public void setOnCourseClickListener(OnCourseClickListener listener) {
        this.clickListener = listener;
    }

    public interface OnCourseLongClickListener {
        void onCourseLongClick(Course course);
    }

    private OnCourseLongClickListener longClickListener;

    public void setOnCourseLongClickListener(OnCourseLongClickListener listener) {
        this.longClickListener = listener;
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseCode;
        private final TextView courseName;
        private final TextView lecturerName;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseCode = itemView.findViewById(R.id.textCourseCode);
            courseName = itemView.findViewById(R.id.textCourseName);
            lecturerName = itemView.findViewById(R.id.textLecturerName);
        }
    }
}
