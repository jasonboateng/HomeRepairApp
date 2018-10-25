package com.uottawa.shize.homerepairapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //catch the user id and load the database object reference
        String userID = getIntent().getStringExtra("id");
        //Log.d("w caught id", userID);
        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("users").child(userID);

        //get message display area
        final TextView msg = (TextView) findViewById(R.id.welcomeText);

        //read from the database and formulate a message
        dRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                String a = u.getUserType().equals("administrator")? "the " : "a ";
                String s = "Welcome " + u.getUsername() + "! You are registered as "+ a + u.getUserType() + ".";
                msg.setText(s);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                msg.setText(databaseError.getMessage());
            }
        });
    }

    public void onClickLogout(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
