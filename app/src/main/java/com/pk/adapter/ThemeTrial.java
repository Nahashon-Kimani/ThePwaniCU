package com.pk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pk.R;
import com.pk.model.ThemeVerseModel;

import java.util.List;

public class ThemeTrial extends BaseAdapter {
    List<ThemeVerseModel> themes;

    public ThemeTrial(List<ThemeVerseModel> themes) {
        this.themes = themes;
    }

    @Override
    public int getCount() {
        return themes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View itemView, ViewGroup parent) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_verse_trial_strip, parent, false);

        TextView themeVerse, themeNarration, themeSemName, themeYear, themeVersion, themeTopic;

        themeVerse = itemView.findViewById(R.id.theme_verse);
        themeNarration = itemView.findViewById(R.id.theme_narration);
        themeSemName = itemView.findViewById(R.id.theme_sem_name);
        themeYear = itemView.findViewById(R.id.theme_year);
        themeVersion = itemView.findViewById(R.id.theme_version);
        themeTopic = itemView.findViewById(R.id.theme_topic);

        themeVerse.setText(themes.get(i).getVerse());
        themeNarration.setText(themes.get(i).getNarration());
        themeSemName.setText(themes.get(i).getSemester());
        themeYear.setText(themes.get(i).getYear());
        themeVersion.setText(themes.get(i).getVersion());
        themeTopic.setText(themes.get(i).getTopic());

        return itemView;
    }
}
