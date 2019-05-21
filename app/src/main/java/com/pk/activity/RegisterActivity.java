package com.pk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.pk.R;
import com.pk.model.UserSignUpModel;

public class RegisterActivity extends AppCompatActivity {
    EditText rFullName, rEmail, rPassword, rPhoneNo;
    Spinner rYearOfStudy;
    Button rRegister;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init
        rFullName = findViewById(R.id.r_full_name);
        rEmail = findViewById(R.id.r_email);
        rPassword = findViewById(R.id.r_password);
        rYearOfStudy = findViewById(R.id.r_year_of_study);
        rPhoneNo = findViewById(R.id.r_phone);
        rRegister = findViewById(R.id.r_register_btn);

        //FireBaseAuth
        mAuth = FirebaseAuth.getInstance();

        //SignUp
        rRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        final String fullName = rFullName.getText().toString().trim();
        final String email = rEmail.getText().toString().trim();
        String password = rPassword.getText().toString().trim();
        final String yearOfStudy = rYearOfStudy.getSelectedItem().toString();
        final String phoneNo = rPhoneNo.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    UserSignUpModel detail = new UserSignUpModel(fullName, yearOfStudy, email, phoneNo);
                    FirebaseDatabase.getInstance().getReference("New").child("App Users").push().setValue(detail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "An Errsor Occurred" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "Please Try again" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
