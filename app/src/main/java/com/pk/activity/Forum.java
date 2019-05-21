package com.pk.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pk.R;
import com.pk.adapter.ChatRecyclerAdapter;
import com.pk.model.ChatModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Forum extends AppCompatActivity {
    FirebaseAuth mAuth;
    RecyclerView fRecyclerView;
    EditText fTypeMessage;
    ImageButton fSendMessage;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    MediaPlayer player;
    ArrayList<ChatModel> chatList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        mAuth = FirebaseAuth.getInstance();
        fRecyclerView = findViewById(R.id.f_recycler_view);
        fTypeMessage = findViewById(R.id.f_type_message);
        fSendMessage = findViewById(R.id.f_send_message);

        //FireBase
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("New").child("Chat");
        populateRecyclerView();//This method populate recycler view

        fSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

    }

    private void sendMessage() {
        String message = fTypeMessage.getText().toString().trim();
        String currentTime = DateFormat.getDateTimeInstance().format(new Date());

        ChatModel chatModel = new ChatModel(message, currentTime, "Sender");
        mRef.push().setValue(chatModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Forum.this, "Message Sent Successfully", Toast.LENGTH_SHORT).show();
                    player = MediaPlayer.create(Forum.this, R.raw.notification);
                    player.start();
                    fTypeMessage.setText("");

                } else {
                    Toast.makeText(Forum.this, "Message not sent. Please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(Forum.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }


    public void populateRecyclerView() {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    chatList.add(snapshot.getValue(ChatModel.class));
                }
                LinearLayoutManager manager = new LinearLayoutManager(Forum.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                fRecyclerView.setLayoutManager(manager);
                fRecyclerView.setAdapter(new ChatRecyclerAdapter(chatList, getApplicationContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.forum_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Forum.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.personal_profile:
                startActivity(new Intent(Forum.this, PersonalProfile.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
