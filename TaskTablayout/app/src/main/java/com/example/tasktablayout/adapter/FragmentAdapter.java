package com.example.tasktablayout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasktablayout.R;
import com.example.tasktablayout.model.FragmentModel;

import java.util.List;

public class FragmentAdapter extends RecyclerView.Adapter<FragmentAdapter.ViewHolder> {
    private Context context;
    private List<FragmentModel> fragmentModelList;

    public FragmentAdapter(Context context, List<FragmentModel> fragmentModelList) {
        this.context = context;
        this.fragmentModelList = fragmentModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item_layout,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentAdapter.ViewHolder holder, int position) {
        FragmentModel fragmentModel = new FragmentModel("Nahid",120);
        holder.txtName.setText(fragmentModel.getName());
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.Name);
        }
    }
}
