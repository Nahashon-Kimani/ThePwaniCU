package com.pk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pk.R;
import com.pk.model.TodayModel;

import java.util.ArrayList;

public class TodayRecyclerAdapter extends RecyclerView.Adapter<TodayRecyclerAdapter.MyHolder> {
    Context context;
    ArrayList<TodayModel> todaySermonList = new ArrayList<>();

    public TodayRecyclerAdapter(Context context, ArrayList<TodayModel> todaySermonList) {
        this.context = context;
        this.todaySermonList = todaySermonList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyHolder(inflater.inflate(R.layout.today_sermon_strip, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.date.setText(todaySermonList.get(i).gettDate());
        myHolder.verse.setText(todaySermonList.get(i).gettDayVerse());
        myHolder.narration.setText(todaySermonList.get(i).gettVerseNarration());
        myHolder.thought.setText(todaySermonList.get(i).gettThoughtOfDay());
        myHolder.prayer.setText(todaySermonList.get(i).gettPrayerOfDay());
        
        //myHolder.prayer.setMovementMethod(new ScrollingMovementMethod());

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView date, verse, narration, thought, prayer;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.t_date);
            verse = itemView.findViewById(R.id.t_verse);
            narration = itemView.findViewById(R.id.t_narration);
            thought = itemView.findViewById(R.id.t_thought);
            prayer = itemView.findViewById(R.id.t_prayer);
        }
    }
}
