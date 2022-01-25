package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Models.Post;


public class addpost extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    ImageView UserImage,AddBtn;
    TextView Title,Description;
    ProgressBar Progress;
    Dialog popAddPost ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        iniPopup();


    }

    private void iniPopup() {
        UserImage = findViewById(R.id.post_user);
        Title = findViewById(R.id.post_title);
        Description = findViewById(R.id.post_desc);
        AddBtn = findViewById(R.id.post_add);
        Progress = findViewById(R.id.post_bar);
        Glide.with(this).load(currentUser.getPhotoUrl()).into(UserImage);
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBtn.setVisibility(View.INVISIBLE);
                Progress.setVisibility(View.VISIBLE);

                if (!Title.getText().toString().isEmpty()
                        && !Description.getText().toString().isEmpty()) {
                    Post post = new Post(Title.getText().toString(),
                            Description.getText().toString(),
                            currentUser.getUid(),
                            currentUser.getPhotoUrl().toString());

                    // Add post to firebase database

                    addPost(post);


                }else{
                    showMessage("Please verify all input fields") ;
                    AddBtn.setVisibility(View.VISIBLE);
                    Progress.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    private void addPost(Post post) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Posts").push();



        // get post unique ID and update post key
        String key = myRef.getKey();
        post.setPostKey(key);


        // add post data to firebase database

        myRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showMessage("Post Added successfully");
                Progress.setVisibility(View.INVISIBLE);
                AddBtn.setVisibility(View.VISIBLE);
                startActivity(new Intent(addpost.this,ListActivity.class));
            }
        });

    }

    private void showMessage(String message) {
        Toast.makeText(addpost.this,message,Toast.LENGTH_LONG).show();
    }


}
