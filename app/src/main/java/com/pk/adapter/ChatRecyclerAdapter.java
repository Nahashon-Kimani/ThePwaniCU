package com.pk.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pk.R;
import com.pk.activity.PersonalProfile;
import com.pk.model.ChatModel;

import java.util.ArrayList;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.MyHolder> {
    ArrayList<ChatModel> chatList = new ArrayList<>();
    Context context;

    public ChatRecyclerAdapter(ArrayList<ChatModel> chatModels, Context context) {
        this.chatList = chatModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyHolder(inflater.inflate(R.layout.chat_strip, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        myHolder.cMessage.setText(chatList.get(i).getMessage());
        myHolder.cTimeSent.setText(chatList.get(i).getTimeSent());
        myHolder.cSender.setText(chatList.get(i).getSender());

        //setting onclick to sender to view his/her profile
        myHolder.cSender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PersonalProfile.class);
                //here we need to send intent of the user id clicked to access the account.
                v.getContext().startActivity(intent);
            }
        });


        //I need once the user click if text is more than 5 line set the text view to wrap content; Applyin read more like in whatsApp.


    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView cMessage, cTimeSent, cSender;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cMessage = itemView.findViewById(R.id.c_message);
            cTimeSent = itemView.findViewById(R.id.c_time_sent);
            cSender = itemView.findViewById(R.id.c_sender);
        }
    }
}
