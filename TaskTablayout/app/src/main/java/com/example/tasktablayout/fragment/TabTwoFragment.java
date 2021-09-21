package com.example.tasktablayout.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tasktablayout.R;
import com.example.tasktablayout.adapter.FragmentAdapter;
import com.example.tasktablayout.model.FragmentModel;

import java.util.ArrayList;
import java.util.List;

public class TabTwoFragment extends Fragment {
    RecyclerView recyclerView;
    FragmentAdapter fragmentAdapter;
    FragmentModel fragmentModel;
    private List<FragmentModel> fragmentModelList;

    public TabTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerviewSecondFragment);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        fragmentModelList = new ArrayList<>();
        fragmentModel = new FragmentModel();
        fragmentModelList.add(fragmentModel);
        fragmentAdapter = new FragmentAdapter(getContext(), fragmentModelList);
        recyclerView.setAdapter(fragmentAdapter);
        fragmentAdapter.notifyDataSetChanged();
    }
}