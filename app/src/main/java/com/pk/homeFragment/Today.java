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
    TextView todayDate, todayVerse, todayNarration, todayThoght, todayPrayer;

    public Today() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.today_fragment, container, false);

        //Database init
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("New").child("Today Sermon");

        //Textview init
        todayDate = view.findViewById(R.id.t_date);
        todayVerse = view.findViewById(R.id.t_verse);
        todayNarration = view.findViewById(R.id.t_narration);
        todayThoght = view.findViewById(R.id.t_thought);
        todayPrayer = view.findViewById(R.id.t_prayer);

        String currentDate = DateFormat.getDateInstance().format(new Date());
        String desc = getResources().getString(R.string.about_cu_desc);

        final TodayModel todayModel = new TodayModel(currentDate, "James 3:17", desc, desc, desc);
        mRef.push().setValue(todayModel);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TodayModel todayModel1 = dataSnapshot.getValue(TodayModel.class);
                todayDate.setText(todayModel1.gettDate());
                todayVerse.setText(todayModel1.gettDayVerse());
                todayNarration.setText(todayModel1.gettVerseNarration());
                todayThoght.setText(todayModel1.gettThoughtOfDay());
                todayPrayer.setText(todayModel1.gettPrayerOfDay());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "An Error Occurred" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
