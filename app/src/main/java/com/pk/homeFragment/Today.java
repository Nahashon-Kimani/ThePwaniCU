package com.pk.homeFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pk.R;
import com.pk.adapter.TodayRecyclerAdapter;
import com.pk.model.TodayModel;

import java.util.ArrayList;
import java.util.Collections;

public class Today extends Fragment {
    View view;
    RecyclerView recyclerView;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    ArrayList<TodayModel> programModelArrayList = new ArrayList<>();

    public Today() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);

        //Database INIT
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("New").child("Today Sermon");

        //String eventInputDate = DateFormat.getDateTimeInstance().format(new Date());


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    programModelArrayList.add(snapshot.getValue(TodayModel.class));
                }
                Collections.reverse(programModelArrayList);
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                manager.setOrientation(1);
               /* manager.setReverseLayout(true);
                manager.setStackFromEnd(true);*/
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(new TodayRecyclerAdapter(getContext(), programModelArrayList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}
