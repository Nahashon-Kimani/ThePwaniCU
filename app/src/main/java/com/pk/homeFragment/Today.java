package com.pk.homeFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
import com.pk.model.TodayModel;

import java.text.DateFormat;
import java.util.Date;

public class Today extends Fragment {
    View view;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    TextView todayDate, todayVerse, todayNarration, todayThoght, todayPrayer, specialAnnouncement;
    String datee;
    String announcement;

    public Today() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.today_fragment, container, false);

        //Database init
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("New").child("Today Sermon");

        //TextView init
        todayDate = view.findViewById(R.id.t_date);
        todayVerse = view.findViewById(R.id.t_verse);
        todayNarration = view.findViewById(R.id.t_narration);
        todayThoght = view.findViewById(R.id.t_thought);
        todayPrayer = view.findViewById(R.id.t_prayer);
        specialAnnouncement = view.findViewById(R.id.t_special_announce);

        specialAnnouncement.setSelected(true);//making text in this textview marquee
        populateTodaySermon();//This method sets Today's Sermon;

        return view;
    }

    /*public void populateSpecialNotice() {
        FirebaseDatabase.getInstance().getReference().child("New").child("Special Notice")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Here we set text in-case of special announcement;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            SpecialNotice specialNotice = snapshot.getValue(SpecialNotice.class);
                            announcement = specialNotice.getMessage().trim();
                            if (announcement.length() > 0) {
                                announcement = announcement + ": by " + specialNotice.getSender();
                                specialAnnouncement.setText(announcement);
                            } else {
                                specialAnnouncement.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }*/

    public void populateTodaySermon() {
        //String currentDate = DateFormat.getDateInstance().format(new Date());

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TodayModel todayModel = snapshot.getValue(TodayModel.class);
                    datee = todayModel.gettDate() + " Sharing";
                    todayDate.setText(datee);
                    todayVerse.setText(todayModel.gettDayVerse());
                    todayNarration.setText(todayModel.gettVerseNarration());
                    todayThoght.setText(todayModel.gettThoughtOfDay());
                    todayPrayer.setText(todayModel.gettPrayerOfDay());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Please connect to the internet", Toast.LENGTH_SHORT).show();
                datee = DateFormat.getDateInstance().format(new Date());
                datee = datee + " Sharing";
                todayDate.setText(datee);
                todayVerse.setText(getResources().getString(R.string.semester_verse));
                todayNarration.setText(getResources().getString(R.string.verse));
                todayThoght.setText(getResources().getString(R.string.verse));//We gonna change for the users who are not connected.
                todayPrayer.setText(getResources().getString(R.string.verse));
            }
        });
    }

    /*@Override
    public void onStart() {
        super.onStart();
        populateSpecialNotice();
    }*/

}
