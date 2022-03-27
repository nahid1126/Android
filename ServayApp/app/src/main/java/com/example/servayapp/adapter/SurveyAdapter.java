package com.example.servayapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
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

        holder.txtName.setText(surveyModel.getSurveyName());
        holder.txtDIST.setText(surveyModel.getSurveyHomeDist());
        holder.txtEdu.setText(surveyModel.getSurveyEducation());
        holder.txtdob.setText(surveyModel.getSurveyDOB());
        holder.txtarea.setText(surveyModel.getSurveyArea());
        holder.txtkids.setText(surveyModel.getSurveyKids());
        holder.txtwife.setText(surveyModel.getSurveyHouse());
        holder.txttime.setText(surveyModel.getCurrentTime());

        String imagepath = surveyModel.getImagePath();
        holder.imageItem.setImageBitmap(BitmapFactory.decodeFile(imagepath));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surveyInterface.onClickItem(position, surveyModel);
            }
        });

        holder.menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surveyInterface.onMenuButtonClick(position, surveyModel, v);
            }
        });

    }

    @Override
    public int getItemCount() {
        return surveyModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtName)
        TextView txtName;

        @BindView(R.id.txtDIST)
        TextView txtDIST;

        @BindView(R.id.txtEdu)
        TextView txtEdu;

        @BindView(R.id.txtdob)
        TextView txtdob;

        @BindView(R.id.txtarea)
        TextView txtarea;

        @BindView(R.id.txtkids)
        TextView txtkids;

        @BindView(R.id.txtwife)
        TextView txtwife;

        @BindView(R.id.txttime)
        TextView txttime;

        @BindView(R.id.menuIcon)
        ImageView menuIcon;

        @BindView(R.id.imageItem)
        ImageView imageItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
