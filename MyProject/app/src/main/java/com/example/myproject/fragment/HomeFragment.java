package com.example.myproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.atapter.PostAdapter;
import com.example.myproject.atapter.PostDetailsInterface;
import com.example.myproject.databasemanager.PostRepojetory;
import com.example.myproject.databasemanager.PostRepojetoryInterface;
import com.example.myproject.databasemanager.RealmDatabaseManager;
import com.example.myproject.model.PostModel;
import com.example.myproject.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    @BindView(R.id.fab)
//    FloatingActionButton fab;

    @OnClick(R.id.fab)
    public void onClick() {
        postDialogFragment = new PostDialogFragment();
        postDialogFragment.show(((AppCompatActivity) getContext()).getSupportFragmentManager(), "post dialog");
        postDialogFragment.setPostDialogInterface(new PostDialogInterface() {
            @Override
            public void addButtonClick(String tile, String body) {

                PostModel postModel = realmDatabaseManager.getPostModel(realmDatabaseManager.getPostModelList().size());
                Log.e(TAG, "model: " + postModel);
                int userId = postModel.getUserId() + 1;
                int id = postModel.getId() + 1;

                PostModel newPostModel = new PostModel();
                newPostModel.setUserId(userId);
                newPostModel.setId(id);
                newPostModel.setTitle(tile);
                newPostModel.setBody(body);
                RealmDatabaseManager.insertNewPostModel(newPostModel);
                postAdapter.setData(RealmDatabaseManager.getInstance().getPostModelList());
                postAdapter.notifyDataSetChanged();

                Toast.makeText(getContext(), "Successfully added", Toast.LENGTH_SHORT).show();
                postDialogFragment.dismiss();

            }
        });
        Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
    }

    private PostAdapter postAdapter;
    private PostRepojetory postRepojetory;
    private List<PostModel> postModelList;
    private int postModelSize = 0;
    private RealmDatabaseManager realmDatabaseManager;
    private PostDialogFragment postDialogFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        postRepojetory = new PostRepojetory(getContext());
        realmDatabaseManager = RealmDatabaseManager.getInstance();

        setUpRecyclerView();
        postModelSize = realmDatabaseManager.getPostModelList().size();

        if (postModelSize == 0) {
            getDataFromAPI();
            postAdapter.setData(RealmDatabaseManager.getInstance().getPostModelList());
            postAdapter.notifyDataSetChanged();
        }

        if (realmDatabaseManager.getPostModelList().size() > 0) {
            postAdapter.setData(realmDatabaseManager.getPostModelList());
        }
    }


    private void getDataFromAPI() {
        postRepojetory.getPostList(new PostRepojetoryInterface() {
            @Override
            public void onSuccess(List<PostModel> postModels) {

                RealmDatabaseManager.insertPostModel(postModels);
                Toast.makeText(getContext(), "" + new RealmDatabaseManager()
                        .getPostModelList().size(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailed() {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        postModelList = new ArrayList<>();
        postAdapter = new PostAdapter(getContext(), postModelList,
                new PostDetailsInterface() {
                    @Override
                    public void onClick(int position, PostModel postModel) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constants.POST_DETAILS, postModel);
                        PostDetailsFragment postDetailsFragment = new PostDetailsFragment();
                        postDetailsFragment.setArguments(bundle);
                        HomeFragment.this.getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.mainPanel, postDetailsFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                });
        recyclerView.setAdapter(postAdapter);
        postAdapter.notifyDataSetChanged();
    }
}