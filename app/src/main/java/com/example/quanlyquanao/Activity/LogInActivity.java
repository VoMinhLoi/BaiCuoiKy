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
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlyquanao.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    Activity activity = LogInActivity.this;
    EditText userNameET, passET;
    Button logInBT, signInBT;
    String userString, passString;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        GetDataFromEditText();
        mAuth = FirebaseAuth.getInstance();
        logInBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogIn();
            }
        });
        signInBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertFromLogInToSignUp();
            }
        });
    }
    public void AnhXa(){
        userNameET = findViewById(R.id.username);
        passET = findViewById(R.id.password);
        logInBT = findViewById(R.id.loginbtn);
        signInBT = findViewById(R.id.buttonSignUp_Login);
    }
    public void GetDataFromEditText(){
        userString = userNameET.getText().toString();
        passString = passET.getText().toString();
    }
    public void ConvertFromLogInToHome(){
        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
    }
    public void LogIn(){
        GetDataFromEditText();
        mAuth.signInWithEmailAndPassword(userString, passString).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(activity, "LogIn Successful", Toast.LENGTH_SHORT).show();
                    ConvertFromLogInToHome();
                }
                else
                    Log.w(TAG, "LogIn Failed", task.getException());
            }
        });
    }
    public void ConvertFromLogInToSignUp(){
        Intent intent = new Intent(activity, SignUpActivity.class);
        startActivity(intent);
    }
}