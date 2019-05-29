package com.pk.drawerFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pk.R;
import com.pk.adapter.SocialMediaAdapter;

public class CuSocialMedia extends Fragment {
    ConstraintLayout constraintLayout;
    RecyclerView recyclerView;
    View view;
    public CuSocialMedia() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        constraintLayout = view.findViewById(R.id.main_recycler_view);
        recyclerView = view.findViewById(R.id.recycler_view);

        //setting the background of constrained layout
        constraintLayout.setBackgroundResource(R.drawable.b3);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new SocialMediaAdapter(getContext()));

        return view;
    }
}
