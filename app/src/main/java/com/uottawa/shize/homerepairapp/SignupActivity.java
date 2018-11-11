package com.uottawa.shize.homerepairapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {

    //to be defined as users root
    private DatabaseReference dRef;

    //user input fields
    private EditText editUsername;
    private EditText editPassword;
    private RadioGroup radioSignupType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dRef = FirebaseDatabase.getInstance().getReference("users");

        //remove admin option if admin already exists
        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RadioButton radioAdmin = (RadioButton) findViewById(R.id.radioAdmin);
                boolean foundAdmin = false;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    User u = ds.getValue(User.class);
                    if (u.getUserType().equals("administrator")){
                        foundAdmin = true;
                        break;
                    }
                }
                radioAdmin.setVisibility(foundAdmin? View.GONE : View.VISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        radioSignupType = (RadioGroup) findViewById(R.id.radioSignupType);
    }

    public void onClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickSignup(View view){
        final String username = editUsername.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();
        final String userType;

        //get user type from radio buttons group
        switch (radioSignupType.getCheckedRadioButtonId()){
            case R.id.radioHomeowner:
                userType = "home owner";
                break;
            case R.id.radioService:
                userType = "service provider";
                break;
            case R.id.radioAdmin:
                userType = "administrator";
                break;
            default:
                userType = "null";
        }

        //check if account already exists
        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean foundConflict = false;
                //go through all users
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    User u = ds.getValue(User.class);
                    //if the same username exists
                    if (u.getUsername().equals(username)){
                        foundConflict = true;
                        break;
                    }
                }
                //display warning
                if (foundConflict){
                    Toast.makeText(getApplicationContext(), "Username taken", Toast.LENGTH_SHORT).show();
                }
                else {
                    //otherwise add new user to database
                    String id = dRef.push().getKey();
                    dRef.child(id).setValue(new User(username, password, userType));

                    //go to welcome screen
                    Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                    //Log.d("s thrown id", id);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
