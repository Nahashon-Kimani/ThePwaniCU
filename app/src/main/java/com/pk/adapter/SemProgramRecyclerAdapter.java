package com.pk.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pk.R;
import com.pk.model.SemProgramModel;

import java.util.ArrayList;

public class SemProgramRecyclerAdapter extends RecyclerView.Adapter<SemProgramRecyclerAdapter.MyHolder> {
    Context context;
    ArrayList<SemProgramModel> programList = new ArrayList<>();

    public SemProgramRecyclerAdapter(Context context, ArrayList<SemProgramModel> programList) {
        this.context = context;
        this.programList = programList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyHolder(inflater.inflate(R.layout.sem_program_strip, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.title.setText(programList.get(i).getpTitle());
        myHolder.desc.setText(programList.get(i).getpDesc());
        myHolder.date.setText(programList.get(i).getpDate());
        myHolder.shareProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_TEXT,
                                "On " + programList.get(i).getpDate()
                                        + "Pwani CU will be hosting" + programList.get(i).getpTitle()
                                        + ". Feel much welcome.");
                intent.setType("text/plain");
                v.getContext().startActivity(Intent.createChooser(intent, "Share " + programList.get(i).getpTitle()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return programList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView title, desc, date;
        ImageView shareProgram;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.p_title);
            desc = itemView.findViewById(R.id.p_desc);
            date = itemView.findViewById(R.id.p_date);
            shareProgram = itemView.findViewById(R.id.p_share);
        }
    }
}
