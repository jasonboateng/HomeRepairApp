package com.uottawa.shize.homerepairapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    //to be defined as users root
    private DatabaseReference dRef;

    //user input fields
    EditText editUsername;
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dRef = FirebaseDatabase.getInstance().getReference("users");

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
    }

    public void onClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickLogin(View view){
        final String username = editUsername.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();

        //read from database
        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean foundUser = false;
                String id = null;
                //go through all users
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    User u = ds.getValue(User.class);
                    //if user exists, save the id
                    if (u.getUsername().equals(username) && u.getPassword().equals(password)){
                        foundUser = true;
                        id = ds.getKey();
                        break;
                    }
                }
                //go to welcome screen if user exists
                if (foundUser && id != null){
                    Intent intent = new Intent(getApplicationContext(), WelcomeActivityAdmin.class);
                    //Log.d("l thrown id", id);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
