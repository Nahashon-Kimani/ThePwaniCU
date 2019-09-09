package com.pk.drawerFragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pk.R;
import com.pk.adapter.ThemeTrial;
import com.pk.adapter.ThemeVerseAdapter;
import com.pk.model.ThemeVerseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThemeVerse extends Fragment {
    View view;
    TextView themeVerse, themeNarration, themeYear, themeSemName, themeVersion;
    //BottomNavigationView navigationView;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    List<ThemeVerseModel> themeVerseModelArrayList = new ArrayList<>();
    //RecyclerView mRecyclerviewer;
    AdapterViewFlipper flipper;
    ThemeTrial themeTrial;
    FloatingActionButton nextThemeVerse, previousThemeVerse;

    public ThemeVerse() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.theme_trial, container, false);
        //mRecyclerviewer = view.findViewById(R.id.recycler_view);

        flipper = view.findViewById(R.id.flipper);
        nextThemeVerse = view.findViewById(R.id.next_theme_verse);
        previousThemeVerse = view.findViewById(R.id.previous_theme_verse);

        //Disable the previous button
        previousThemeVerse.setEnabled(false);
        previousThemeVerse.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D3D3D3")));


        themeTrial = new ThemeTrial(themeVerseModelArrayList);

        //database init
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("New").child("Theme Verse");
        mRef.keepSynced(true);

        //TextView INIT
        themeVerse = view.findViewById(R.id.theme_verse);
        themeNarration = view.findViewById(R.id.theme_narration);
        themeYear = view.findViewById(R.id.theme_year);
        themeSemName = view.findViewById(R.id.theme_sem_name);
        themeVersion = view.findViewById(R.id.theme_version);
        //navigationView = view.findViewById(R.id.theme_next_previous);


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    themeVerseModelArrayList.add(snapshot.getValue(ThemeVerseModel.class));
                }
                Collections.reverse(themeVerseModelArrayList);
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);

                flipper.setAdapter(themeTrial);

                /*flipper.setFlipInterval(1000);
                flipper.setAutoStart(true);*/
                //mRecyclerviewer.setLayoutManager(manager);
                //mRecyclerviewer.setAdapter(new ThemeVerseAdapter(themeVerseModelArrayList, getContext()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "An error Occurred. Please connect to Internet.", Toast.LENGTH_SHORT).show();
            }
        });

        previousThemeVerse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flipper.getDisplayedChild() ==0) {
                    //flipper.stopFlipping();
                    previousThemeVerse.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D3D3D3")));
                    previousThemeVerse.setEnabled(false);
                    Toast.makeText(getContext(), "You have reached the First Theme Verse", Toast.LENGTH_SHORT).show();

                } else {
                    flipper.showPrevious();
                    nextThemeVerse.setEnabled(true);
                    nextThemeVerse.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff9100")));
                }
            }

        });

        nextThemeVerse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousThemeVerse.setEnabled(true);
                previousThemeVerse.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff9100")));

                if (flipper.getDisplayedChild() == themeVerseModelArrayList.size()-1) {
                    //flipper.stopFlipping();
                    nextThemeVerse.setEnabled(false);
                    Toast.makeText(getContext(), "You have reached the last Theme Verse", Toast.LENGTH_SHORT).show();
                    nextThemeVerse.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D3D3D3")));
                } else {
                    flipper.showNext();
                }

            }
        });

       /* navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.previous:
                        Toast.makeText(getContext(), "Last Semester Theme verse", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.next:
                        Toast.makeText(getContext(), "Next Semester Theme verse", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });*/

        return view;
    }
}
