package com.example.myproject.atapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.model.PostModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private static final String TAG = "ProjectAdapter";

    private Context context;
    private List<PostModel> postModelList;
    private PostDetailsInterface postDetailsInterface;

    public PostAdapter(Context context, List<PostModel> postModelList, PostDetailsInterface postDetailsInterface) {
        this.context = context;
        this.postModelList = postModelList;
        this.postDetailsInterface = postDetailsInterface;
    }

    public void setData(List<PostModel> postModelList) {
        this.postModelList = postModelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {

        PostModel postModel = postModelList.get(position);
        holder.txtTitle.setText(postModel.getTitle());
        holder.txtBody.setText(postModel.getBody());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDetailsInterface.onClick(position, postModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtBody)
        TextView txtBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
