package com.example.myproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.api.ApiClient;
import com.example.myproject.api.ApiClientInterface;
import com.example.myproject.databasemanager.PostRepojetory;
import com.example.myproject.databasemanager.PostRepojetoryInterface;
import com.example.myproject.model.CommentModel;
import com.example.myproject.model.PostModel;
import com.example.myproject.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailsFragment extends Fragment {
    private static final String TAG = "PostDetailsFragment";
    @BindView(R.id.txtDetailsData)
    TextView txtDetails;
    @BindView(R.id.txtPostBody)
    TextView txtPostBody;
    @BindView(R.id.txtComment)
    TextView txtCommnet;
    public static PostModel details;

    private ApiClientInterface apiClientInterface;
    private final int RESPONSE_OK = 200;

    public PostDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            details = (PostModel) getArguments().getSerializable(Constants.POST_DETAILS);
            apiClientInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
            Log.d(TAG, "onCreate:" + details.getId());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poat_details, container, false);
        ButterKnife.bind(this, view);
        txtDetails.setText("Title: " + details.getTitle() + "\n");
        txtPostBody.setText("Body: " + details.getBody());
        Call<List<CommentModel>> call = apiClientInterface.getComment(details.getUserId());
        try {
            call.enqueue(new Callback<List<CommentModel>>() {
                @Override
                public void onResponse(Call<List<CommentModel>> call, Response<List<CommentModel>> response) {
                    if (response.isSuccessful() && response.code() == RESPONSE_OK) {
                        List<CommentModel> commentModels = response.body();
                        for (CommentModel comment : commentModels) {
                            String commentBody = "";
                            commentBody += "Post ID: " + comment.getPostId() + "\n";
                            commentBody += "ID: " + comment.getId() + "\n";
                            commentBody += "Name: " + comment.getName() + "\n";
                            commentBody += "Email: " + comment.getEmail() + "\n";
                            commentBody += "Text: " + comment.getBody() + "\n\n";

                            txtCommnet.append(commentBody);
                        }
                    } else {
                        Toast.makeText(getContext(), "Failed ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<CommentModel>> call, Throwable t) {
                    call.cancel();
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}