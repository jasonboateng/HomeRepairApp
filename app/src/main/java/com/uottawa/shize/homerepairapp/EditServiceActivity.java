package com.uottawa.shize.homerepairapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class EditServiceActivity extends AppCompatActivity {

    private String adminID;

    private String serviceID;

    private DatabaseReference dRef;

    private TextView editServiceName;
    private TextView editRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editservice);

        boolean isAdding = getIntent().getBooleanExtra("addingNewService", true);

        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete.setClickable(!isAdding);
        btnSave.setClickable(!isAdding);
        btnAdd.setClickable(isAdding);
        btnDelete.setVisibility(isAdding? View.GONE : View.VISIBLE);
        btnSave.setVisibility(isAdding? View.GONE : View.VISIBLE);
        btnAdd.setVisibility(isAdding? View.VISIBLE : View.GONE);

        adminID = getIntent().getStringExtra("adminID");
        serviceID = getIntent().getStringExtra("serviceID");
        dRef = FirebaseDatabase.getInstance().getReference("services");
        editServiceName = (TextView) findViewById(R.id.editServiceName);
        editRate = (TextView) findViewById(R.id.editRate);

        if (!isAdding) {
            dRef.child(serviceID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Service s = dataSnapshot.getValue(Service.class);
                    editServiceName.setText(s.getServiceName());
                    editRate.setText(String.format(Locale.CANADA, "%.2f", s.getRate()));
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(getApplicationContext(), ManageServicesActivity.class);
        intent.putExtra("adminID", adminID);
        startActivity(intent);
        finish();
    }

    public void onClickDelete(View view) {
        dRef.child(serviceID).removeValue();
        onClickBack(view);
    }

    public void onClickSave(View view) {
        String serviceName = editServiceName.getText().toString().trim();
        double rate = Double.parseDouble(editRate.getText().toString().trim());
        dRef.child(serviceID).setValue(new Service(serviceName, rate));
        onClickBack(view);
    }

    public void onClickAdd(View view) {
        serviceID = dRef.push().getKey();
        onClickSave(view);
    }
}
