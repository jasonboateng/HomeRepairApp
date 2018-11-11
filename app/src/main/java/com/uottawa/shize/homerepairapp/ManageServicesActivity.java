package com.uottawa.shize.homerepairapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageServicesActivity extends AppCompatActivity {

    private DatabaseReference dRef;

    private ArrayList<Service> serviceArray;

    private ListView listServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageservices);

        /*
        dRef = FirebaseDatabase.getInstance().getReference("services");

        listServices = (ListView) findViewById(R.id.listServices);

        serviceArray = new ArrayList<>();

        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                serviceArray.clear();
                ArrayList<String> serviceDesc = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Service s = ds.getValue(Service.class);
                    serviceArray.add(s);
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
                //launch edit service activity
            }
        });
        */
    }

}
