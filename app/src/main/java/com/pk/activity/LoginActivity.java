package com.pk.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pk.R;

public class LoginActivity extends AppCompatActivity {
    EditText passwordEdt, emailEdt;
    Button loginBtn;
    ProgressBar loginProgress;
    FirebaseAuth mAuth;
    TextView registerHere;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Init
        passwordEdt = findViewById(R.id.password_edt);
        emailEdt = findViewById(R.id.email_edt);
        loginBtn = findViewById(R.id.login_button);
        loginProgress = findViewById(R.id.login_progress);
        registerHere = findViewById(R.id.register_here);

        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        //Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loginProgress.setVisibility(View.VISIBLE);

                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setProgressStyle(0);//0 Means ProgressDialog.STYLE_SPINNER
                progressDialog.setMessage("Login in...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();


                String email = emailEdt.getText().toString().trim();
                String pass = passwordEdt.getText().toString().trim();

                if (email.isEmpty()) {
                    emailEdt.setError("Email should not be empty");
                    emailEdt.requestFocus();
                    //loginProgress.setVisibility(View.GONE);
                    progressDialog.hide();
                } else if (pass.isEmpty()) {
                    passwordEdt.setError("Please Provide a Valid Password.");
                    passwordEdt.requestFocus();
                    //loginProgress.setVisibility(View.GONE);
                    progressDialog.hide();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailEdt.requestFocus();
                    emailEdt.setError("Please Provide a Valid Email.");
                    //loginProgress.setVisibility(View.GONE);
                    progressDialog.hide();
                } else {
                    mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //loginProgress.setVisibility(View.GONE);
                                progressDialog.hide();

                                Intent intent = new Intent(LoginActivity.this, Forum.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "An error Occurred\n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                //loginProgress.setVisibility(View.GONE);
                                progressDialog.hide();
                                emailEdt.requestFocus();
                            }
                        }
                    });

                }
            }
        });
    }
}
