package com.pk.activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.pk.model.UserSignUpModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Forum extends AppCompatActivity {
    FirebaseAuth mAuth;
    RecyclerView fRecyclerView;
    EditText fTypeMessage;
    ImageView fSendMessage;
    MediaPlayer player;
    ArrayList<ChatModel> chatList = new ArrayList<>();
    String sender, myUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        mAuth = FirebaseAuth.getInstance();
        fRecyclerView = findViewById(R.id.f_recycler_view);
        fTypeMessage = findViewById(R.id.f_type_message);
        fSendMessage = findViewById(R.id.f_send_message);

        //Getting the key of the login user;
        myUserId = mAuth.getUid();


        fSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        //populateRecyclerView();//This method populate recycler view
        //Setting the Button disabled if no text detected.

        //fSendMessage.setEnabled(false);
        //Here we need to activate send button once editText has something.
        /*fTypeMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim().length() == 0) {
                    fSendMessage.setEnabled(false);
                } else {
                    fSendMessage.setEnabled(true);
                    //fSendMessage.setBackgroundColor(Color.parseColor("#43A047"));//color Accent.
                    fSendMessage.setColorFilter(getResources().getColor(R.color.send_color));
                    Toast.makeText(Forum.this, Integer.toString(count), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(Forum.this, Integer.toString(count), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                fSendMessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendMessage();
                    }
                });
            }
        });*/




    }

    private void sendMessage() {
        String message = fTypeMessage.getText().toString().trim();
        String currentTime = DateFormat.getDateTimeInstance().format(new Date());
        fSendMessage.setEnabled(true);

        if (message.isEmpty()) {
            fTypeMessage.setError("Message Cannot be empty.");
            fTypeMessage.requestFocus();
        } else if (message.length() <= 3) {
            fTypeMessage.setError("Message too short");
            fTypeMessage.requestFocus();
        } else {
            ChatModel chatModel = new ChatModel(message, currentTime, myUserId);
            FirebaseDatabase.getInstance().getReference().child("New").child("Chat")
                    .setValue(chatModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Forum.this, "Successfully Sent", Toast.LENGTH_SHORT).show();
                        MediaPlayer player = MediaPlayer.create(Forum.this, R.raw.notification);
                        player.start();
                        fTypeMessage.setText("");
                    } else {
                        Toast.makeText(Forum.this, "Try again Later", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
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
        FirebaseDatabase.getInstance().getReference().child("New").child("Chat")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            chatList.add(snapshot.getValue(ChatModel.class));
                        }
                        //Collections.reverse(chatList);
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
