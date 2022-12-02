package com.example.quanlyquanao.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quanlyquanao.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    Activity activity = SignUpActivity.this;
    TextInputEditText userNameET, passET;
    Button signUpBT, logInBT;
    String userString, passString;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        AnhXa();
        mAuth = FirebaseAuth.getInstance();
        signUpBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
        logInBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertFromSignUpToLogIn();
            }
        });
    }
    public void AnhXa(){
        userNameET = findViewById(R.id.usernameSU);
        passET = findViewById(R.id.passwordSU);
        signUpBT = findViewById(R.id.buttonSignUp);
        logInBT = findViewById(R.id.buttonLogin);
    }
    public void GetData(){
        userString = userNameET.getText().toString();
        passString = passET.getText().toString();
    }
    public void ConvertFromSignUpToLogIn(){
        Intent intent = new Intent(activity, LogInActivity.class);
        startActivity(intent);
    }
    public void SignUp(){
        GetData();
        mAuth.createUserWithEmailAndPassword(userString, passString).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(activity, "SignUp Successful", Toast.LENGTH_SHORT).show();
                    ConvertFromSignUpToLogIn();
                }
                else
                    Log.w(TAG, "SignUp Failed", task.getException());
            }
        });
    }
}