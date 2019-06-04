package com.pk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pk.R;
import com.pk.model.ThemeVerseModel;

import java.util.ArrayList;

public class ThemeVerseAdapter extends RecyclerView.Adapter<ThemeVerseAdapter.MyHolder> {
    ArrayList<ThemeVerseModel> themeList = new ArrayList<>();
    Context context;

    public ThemeVerseAdapter(ArrayList<ThemeVerseModel> themeList, Context context) {
        this.themeList = themeList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyHolder(inflater.inflate(R.layout.theme_verse_strip, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.themeVerse.setText(themeList.get(i).getVerse());
        myHolder.themeNarration.setText(themeList.get(i).getNarration());
        myHolder.themeSemName.setText(themeList.get(i).getSemester());
        myHolder.themeYear.setText(themeList.get(i).getYear());
        myHolder.themeVersion.setText(themeList.get(i).getVersion());
    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView themeVerse, themeNarration, themeSemName, themeYear, themeVersion;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            themeVerse = itemView.findViewById(R.id.theme_verse);
            themeNarration = itemView.findViewById(R.id.theme_narration);
            themeSemName = itemView.findViewById(R.id.theme_sem_name);
            themeYear = itemView.findViewById(R.id.theme_year);
            themeVersion = itemView.findViewById(R.id.theme_version);

        }
    }
}
