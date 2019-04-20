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

import com.pk.R;

public class ThemeVerse extends Fragment {
    View view;
    TextView themeVerse, themeNarration, themeYear, themeSemName, themeVersion;
    BottomNavigationView navigationView;

    public ThemeVerse() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.theme_verse_fragment, container, false);

        themeVerse = view.findViewById(R.id.theme_verse);
        themeNarration = view.findViewById(R.id.theme_narration);
        themeYear = view.findViewById(R.id.theme_year);
        themeSemName = view.findViewById(R.id.theme_sem_name);
        themeVersion = view.findViewById(R.id.theme_version);
        navigationView = view.findViewById(R.id.theme_next_previous);

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
