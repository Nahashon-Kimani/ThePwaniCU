package com.pk.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pk.R;
import com.pk.model.SemProgramModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SemProgramRecyclerAdapter extends RecyclerView.Adapter<SemProgramRecyclerAdapter.MyHolder> {
    String today;
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

        today = DateFormat.getDateInstance().format(new Date());
        String date = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
        if (date.equals(programList.get(i).getpDate())) {
            myHolder.programCard.setBackgroundResource(R.color.colorPrimary);
            myHolder.desc.setTextColor(context.getResources().getColor(R.color.secondaryTextColor));
            myHolder.desc.setTextSize(16);
        }

        myHolder.shareProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_TEXT,
                                "Hello. On " + programList.get(i).getpDate()
                                        + " Pwani University Christian Union will be hosting "
                                        + programList.get(i).getpTitle()
                                        + ". Feel much welcome.\n"
                                        + "#PwaniUniversityChristianUnionApp");
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
        CardView programCard;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            programCard = itemView.findViewById(R.id.p_card_view);
            title = itemView.findViewById(R.id.p_title);
            desc = itemView.findViewById(R.id.p_desc);
            date = itemView.findViewById(R.id.p_date);
            shareProgram = itemView.findViewById(R.id.p_share);
        }
    }
}
