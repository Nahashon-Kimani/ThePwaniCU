package com.pk.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
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
    EditText rFullName, rEmail, rPassword, rPhoneNo, rConfirmPassword;
    Spinner rYearOfStudy;
    Button rRegister;
    FirebaseAuth mAuth;
    String[] years;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init
        rFullName = findViewById(R.id.r_full_name);
        rEmail = findViewById(R.id.r_email);
        rPassword = findViewById(R.id.r_password);
        rConfirmPassword = findViewById(R.id.r_confirm_password);
        rYearOfStudy = findViewById(R.id.r_year_of_study);
        rPhoneNo = findViewById(R.id.r_phone);
        rRegister = findViewById(R.id.r_register_btn);

        //FireBaseAuth
        mAuth = FirebaseAuth.getInstance();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, list);
        //SignUp
        rRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        years = getResources().getStringArray(R.array.year_of_study);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(RegisterActivity.this, R.layout.spinner_layout, years);
        rYearOfStudy.setAdapter(yearAdapter);
    }

    private void signUp() {
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Registering");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(true);
        progressDialog.show();


        final String fullName = rFullName.getText().toString().trim();
        final String email = rEmail.getText().toString().trim();
        String password = rPassword.getText().toString().trim();
        String confirmPassword = rConfirmPassword.getText().toString().trim();
        final String yearOfStudy = rYearOfStudy.getSelectedItem().toString();
        final String phoneNo = rPhoneNo.getText().toString().trim();

        if (fullName.isEmpty()) {
            rFullName.setError("Name is required.");
            rFullName.requestFocus();
            progressDialog.hide();
        } else if (fullName.length() <= 6) {
            rFullName.setError("Please provide full name");
            rFullName.requestFocus();
            progressDialog.hide();
        } else if (email.isEmpty()) {
            rEmail.requestFocus();
            rEmail.setError("Email Address is Required.");
            progressDialog.hide();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            rEmail.setError("Please provide a valid email address");
            rEmail.requestFocus();
            progressDialog.hide();
        } else if (password.isEmpty()) {
            rPassword.setError("Password required.");
            rPassword.requestFocus();
            progressDialog.hide();
        } else if (password.length() < 6) {
            rPassword.setError("Please provide a stronger password");
            rPassword.requestFocus();
            progressDialog.hide();
        } else if (confirmPassword.isEmpty()) {
            rConfirmPassword.setError("Password Confirmation required.");
            rConfirmPassword.requestFocus();
            progressDialog.hide();
        } else if (!password.equals(confirmPassword)) {
            rPassword.setError("Passwords do not match.");
            rConfirmPassword.setError("Passwords do not match.");
            rPassword.requestFocus();
            progressDialog.hide();
        } else if (yearOfStudy.equals("SELECT YEAR OF STUDY")) {
            Toast.makeText(RegisterActivity.this, "Please Select Current Year of Study",
                    Toast.LENGTH_SHORT).show();
            rYearOfStudy.requestFocus();
            rYearOfStudy.setEnabled(true);
            progressDialog.hide();
        } else if (phoneNo.isEmpty()) {
            rPhoneNo.setError("Phone Number Required.");
            rPhoneNo.requestFocus();
            progressDialog.hide();
        } else if (phoneNo.length() != 10) {
            rPhoneNo.setError("Please Provide a Valid Phone Number");
            rPhoneNo.requestFocus();
            progressDialog.hide();
        } else {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String userId = mAuth.getUid();
                        UserSignUpModel userDetail = new UserSignUpModel(fullName, yearOfStudy, email, phoneNo);
                        FirebaseDatabase.getInstance().getReference("New").child("App Users").child(userId).push().setValue(userDetail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                    MediaPlayer player = MediaPlayer.create(RegisterActivity.this, R.raw.notification);
                                    player.start();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "An Error Occurred\n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    progressDialog.setMessage("An Error Occurred\n" + task.getException().getMessage());
                                    progressDialog.hide();
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
}
