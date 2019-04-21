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

public class Today extends Fragment {
    View view;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    TextView todayDate, todayVerse, todayNarration, todayThoght, todayPrayer, specialAnnouncement;

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

        //String currentDate = DateFormat.getDateInstance().format(new Date());


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TodayModel todayModel = snapshot.getValue(TodayModel.class);
                    todayDate.setText(todayModel.gettDate());
                    todayVerse.setText(todayModel.gettDayVerse());
                    todayNarration.setText(todayModel.gettVerseNarration());
                    todayThoght.setText(todayModel.gettThoughtOfDay());
                    todayPrayer.setText(todayModel.gettPrayerOfDay());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "An Error Occurred" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
