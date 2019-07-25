package com.pk.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.pk.R;
import com.pk.model.NoticeModel;

import java.util.ArrayList;

public class NoticeRecyclerAdapter extends RecyclerView.Adapter<NoticeRecyclerAdapter.MyHolder> {
    private Context context;
    private ArrayList<NoticeModel> noticeList = new ArrayList<>();

    public NoticeRecyclerAdapter(Context context, ArrayList<NoticeModel> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyHolder(inflater.inflate(R.layout.notice_strip, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
        myHolder.nTitle.setText(noticeList.get(i).getNoticeTitle());
        myHolder.nDesc.setText(noticeList.get(i).getNoticeDesc());
        myHolder.nDate.setText(noticeList.get(i).getNoticeDate());
        myHolder.nCategory.setText(noticeList.get(i).getNoticeCategory());

        myHolder.nDesc.setMovementMethod(new ScrollingMovementMethod());

        myHolder.shareNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_TEXT, "Hello. Just a reminder of Pwani University Christian Union, "
                                + "on date " + noticeList.get(i).getNoticeDate()
                                + "we will have having " + noticeList.get(i).getNoticeTitle()
                                + ". Please purpose to attend. Thank You.\n"
                                + "#PwaniUniversityChristianUnion");
                intent.setType("text/plain");
                v.getContext().startActivity(Intent.createChooser(intent, "Share about " + noticeList.get(i).getNoticeTitle()));
            }
        });

        //Checking the date and automatically deleting past event.
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView nTitle, nDate, nCategory, nDesc;
        ImageView shareNotice;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            shareNotice = itemView.findViewById(R.id.share_event);
            nTitle = itemView.findViewById(R.id.event_name);
            nDate = itemView.findViewById(R.id.event_date);
            nCategory = itemView.findViewById(R.id.event_category);
            nDesc = itemView.findViewById(R.id.event_desc);
        }
    }
}
