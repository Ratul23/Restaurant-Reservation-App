package com.example.pleasereserve;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class Signup extends AppCompatActivity {





    EditText textSignEmail, textSignPassword, textConfirmSignPassword;
    Button button;
    ProgressBar progressBar;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Signup Form");

        textSignEmail=(EditText) findViewById(R.id.signupEmailID);
        textSignPassword=(EditText)findViewById(R.id.signupPasswordID);
        textConfirmSignPassword=(EditText)findViewById(R.id.signupConfirmPasswordID);
        button=(Button)findViewById(R.id.signupButtonID);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);


        auth=FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =textSignEmail.getText().toString().trim();
                String pass=textSignPassword.getText().toString().trim();
                String cpass=textConfirmSignPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Signup.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(Signup.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(cpass)){
                    Toast.makeText(Signup.this, "Please enter password again here", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(pass.length()<6){
                    Toast.makeText(Signup.this, " too short", Toast.LENGTH_SHORT).show();
                    return;

                }

                progressBar.setVisibility(View.VISIBLE);

                if(pass.equals(cpass)){
                    auth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),Login.class));
                                        Toast.makeText(Signup.this, "Authentication Successful", Toast.LENGTH_SHORT).show();


                                    } else {

                                        Toast.makeText(Signup.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });

                }

            }
        });
    }
}