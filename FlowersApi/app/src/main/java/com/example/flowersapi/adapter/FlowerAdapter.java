package com.example.flowersapi.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flowersapi.R;
import com.example.flowersapi.api.ApiClient;
import com.example.flowersapi.model.FlowerModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.ViewHolder> {

    private Context context;
    private List<FlowerModel> flowerModelList;
    private String BASE_URL = "https://services.hanselandpetal.com/photos/";
    private static final String TAG = "FlowerAdapter";


    public FlowerAdapter(Context context, List<FlowerModel> flowerModelList) {
        this.context = context;
        this.flowerModelList = flowerModelList;
    }

    @Override
    public FlowerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_flower_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerAdapter.ViewHolder holder, int position) {
        FlowerModel flowerModel = flowerModelList.get(position);
        holder.txtName.setText(flowerModel.getName());
        holder.txtCategory.setText(flowerModel.getCategory());
        holder.txtPrice.setText(flowerModel.getPrice() + "");

        String imageURL = BASE_URL + flowerModel.getPhoto();
        Log.e(TAG, "url: "+ imageURL);
        holder.flowerImgView.setImageURI(Uri.parse(imageURL));
    }

    @Override
    public int getItemCount() {
        return flowerModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView flowerImgView;
        private TextView txtName;
        private TextView txtCategory;
        private TextView txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            flowerImgView = itemView.findViewById(R.id.flowerImgView);
            txtName = itemView.findViewById(R.id.txtName);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}
