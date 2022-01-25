package com.example.projet;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class CodeActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        Button button = findViewById(R.id.join);
        EditText code =findViewById(R.id.code);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String C = code.getText().toString();
                if(C.matches("AB123456")){
                    Intent intent = new Intent(CodeActivity.this, addpost.class);
                    startActivity(intent);
                }else if(C.matches("CD123456")){
                    Intent intent1 = new Intent(CodeActivity.this, addpost1.class);
                    startActivity(intent1);
                }else if(C.matches("EF123456")){
                    Intent intent2 = new Intent(CodeActivity.this, addpost2.class);
                    startActivity(intent2);
                }else{
                    showMessage("the code is invalid please try again");
                }

            }
        });
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        updateNavHeader();

    }
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void updateNavHeader() {

        TextView navUsername =findViewById(R.id.userinfo);
        ImageView navUserPhot =findViewById(R.id.userprofil);

        navUsername.setText("Hi, "+currentUser.getDisplayName()+" !");

        // now we will use Glide to load user image
        // first we need to import the library

        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhot);
    }
}