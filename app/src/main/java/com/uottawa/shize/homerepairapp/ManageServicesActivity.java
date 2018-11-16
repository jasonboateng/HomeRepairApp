package com.uottawa.shize.homerepairapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManageServicesActivity extends AppCompatActivity {

    private String adminID;

    private ListView listServices;

    private ArrayList<String> serviceKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageservices);

        adminID = getIntent().getStringExtra("adminID");

        listServices = (ListView) findViewById(R.id.listServices);

        serviceKeys = new ArrayList<>();

        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("services");
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                serviceKeys.clear();
                ArrayList<String> serviceDesc = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Service s = ds.getValue(Service.class);
                    serviceKeys.add(ds.getKey());
                    serviceDesc.add(s.toString());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, serviceDesc);
                listServices.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

        listServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String serviceID = serviceKeys.get(position);
                Intent intent = new Intent(getApplicationContext(), EditServiceActivity.class);
                intent.putExtra("addingNewService", false);
                intent.putExtra("serviceID", serviceID);
                intent.putExtra("adminID", adminID);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        intent.putExtra("id", adminID);
        startActivity(intent);
        finish();
    }

    public void onClickAdd(View view) {
        Intent intent = new Intent(getApplicationContext(), EditServiceActivity.class);
        intent.putExtra("addingNewService", true);
        intent.putExtra("adminID", adminID);
        startActivity(intent);
        finish();
    }
}
