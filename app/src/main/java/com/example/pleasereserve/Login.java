package com.example.pleasereserve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {
    EditText txtEmail, txtPassword;
    Button loginButton,signupButton;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login Form");

        txtEmail=(EditText)findViewById(R.id.loginEmailID);
        txtPassword=(EditText)findViewById(R.id.loginPasswordID);
        loginButton=(Button)findViewById(R.id.loginButtonID);
        signupButton=(Button)findViewById(R.id.loginToSignupButtonID);
        auth=FirebaseAuth.getInstance();




        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =txtEmail.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(txtPassword.length()<6){
                    Toast.makeText(Login.this, " too short", Toast.LENGTH_SHORT).show();
                    return;

                }

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }
        });






    }
    public void loginToSignupButton(View view){
        startActivity(new Intent(getApplicationContext(),Signup.class));
    }



}
