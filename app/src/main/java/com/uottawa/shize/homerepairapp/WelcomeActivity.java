package com.uottawa.shize.homerepairapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeActivity extends AppCompatActivity {

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //catch the user id and load the database object reference
        userID = getIntent().getStringExtra("id");
        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("users").child(userID);

        //get message display area
        final TextView msg = (TextView) findViewById(R.id.welcomeText);

        //get manage services button
        final Button btnMngServices = (Button) findViewById(R.id.btnManageServices);

        //read from the database and formulate a message
        dRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                boolean isAdmin = u.getUserType().equals("administrator");
                btnMngServices.setClickable(isAdmin);
                btnMngServices.setVisibility(isAdmin? View.VISIBLE : View.INVISIBLE);
                String a = isAdmin? "the " : "a ";
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

    public void onClickManageServices(View view){
        Intent intent = new Intent(getApplicationContext(), ManageServicesActivity.class);
        intent.putExtra("adminID", userID);
        startActivity(intent);
        finish();
    }
}
