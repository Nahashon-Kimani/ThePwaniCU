package com.pk.drawerFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pk.R;
import com.pk.model.ThemeVerseModel;

public class ThemeVerse extends Fragment {
    View view;
    TextView themeVerse, themeNarration, themeYear, themeSemName, themeVersion;
    BottomNavigationView navigationView;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;

    public ThemeVerse() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.theme_verse_strip, container, false);

        //database init
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("New").child("Theme Verse");

        //TextView INIT
        themeVerse = view.findViewById(R.id.theme_verse);
        themeNarration = view.findViewById(R.id.theme_narration);
        themeYear = view.findViewById(R.id.theme_year);
        themeSemName = view.findViewById(R.id.theme_sem_name);
        themeVersion = view.findViewById(R.id.theme_version);
        navigationView = view.findViewById(R.id.theme_next_previous);


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ThemeVerseModel verseModel = snapshot.getValue(ThemeVerseModel.class);
                    themeVerse.setText(verseModel.getVerse());
                    themeNarration.setText(verseModel.getNarration());
                    themeYear.setText(verseModel.getYear());
                    themeSemName.setText(verseModel.getSemester());
                    themeVersion.setText(verseModel.getVersion());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
        });

        return view;
    }
}
