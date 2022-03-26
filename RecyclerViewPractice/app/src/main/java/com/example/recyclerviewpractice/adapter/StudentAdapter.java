package com.example.recyclerviewpractice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewpractice.R;
import com.example.recyclerviewpractice.activity.StudentListActivity;
import com.example.recyclerviewpractice.model.StudentModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private Context context;
    private List<StudentModel> studentAdapterList;
    private StudentDetailsInterface studentDetailsInterface;

    public StudentAdapter(Context context, List<StudentModel> studentAdapterList,
                          StudentDetailsInterface studentDetailsInterface) {
        this.context = context;
        this.studentAdapterList = studentAdapterList;
        this.studentDetailsInterface = studentDetailsInterface;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_student_item, parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
        StudentModel studentModel = studentAdapterList.get(position);

        holder.txtStudentName.setText(studentModel.getStudentName());
        holder.txtStudentID.setText(studentModel.getStudentId() + "");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentDetailsInterface.onClickItem(position, studentModel);
            }

        });
        holder.menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentDetailsInterface.onMenuButtonClick(position, studentModel, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentAdapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.studentName)
        TextView txtStudentName;
        @BindView(R.id.studentID)
        TextView txtStudentID;
        @BindView(R.id.menuIcon)
        ImageView menuIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
