package com.example.servayapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servayapp.R;
import com.example.servayapp.model.SurveyModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.ViewHolder> {

    private Context context;
    private List<SurveyModel> surveyModelList;


    private SurveyInterface surveyInterface;

    public SurveyAdapter(Context context, List<SurveyModel> surveyModelList, SurveyInterface surveyInterface) {
        this.context = context;
        this.surveyModelList = surveyModelList;
        this.surveyInterface = surveyInterface;
    }


    @NonNull
    @Override
    public SurveyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_survey_item, parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SurveyModel surveyModel = surveyModelList.get(position);

        holder.txtTitle.setText(surveyModel.getSurveyName());
        holder.txtBody.setText(surveyModel.getSurveyHomeDist());

        holder.itemView.setOnClickListener(v -> surveyInterface.onClickItem(position, surveyModel));

        holder.menuIcon.setOnClickListener(v -> surveyInterface.onMenuButtonClick(position, surveyModel, v));

    }

    @Override
    public int getItemCount() {
        return surveyModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtBody)
        TextView txtBody;
        @BindView(R.id.menuIcon)
        ImageView menuIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
