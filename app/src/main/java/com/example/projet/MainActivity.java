package com.example.projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private EditText loginemail,loginpassword;
    private Button loginbtn;
    private ProgressBar loginProgress;
    private FirebaseAuth mAuth;
    private Intent CodeActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.sing);
         textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Sing.class);
                startActivity(intent);
            }
        });

        loginemail = findViewById(R.id.emaill);
        loginpassword = findViewById(R.id.passwordd);
        loginbtn = findViewById(R.id.btn);
        loginProgress = findViewById(R.id.loginProgressBar);
        mAuth = FirebaseAuth.getInstance();
        CodeActivity = new Intent(this, com.example.projet.CodeActivity.class);
        loginProgress.setVisibility(View.INVISIBLE);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginProgress.setVisibility(View.VISIBLE);
                loginbtn.setVisibility(View.INVISIBLE);

                final String email = loginemail.getText().toString();
                final String password = loginpassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    showMessage("Please Verify All Field");
                    loginbtn.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
                else
                {
                    signIn(email,password);
                }




            }
        });

    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {

                    loginProgress.setVisibility(View.INVISIBLE);
                    loginbtn.setVisibility(View.VISIBLE);
                    updateUI();

                }
                else {
                    showMessage(task.getException().getMessage());
                    loginbtn.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }


            }
        });
    }


    private void updateUI() {
        startActivity(CodeActivity);
        finish();
    }

    private void showMessage(String text) {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }
}