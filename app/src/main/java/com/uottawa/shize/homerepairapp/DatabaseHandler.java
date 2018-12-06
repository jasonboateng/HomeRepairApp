package com.uottawa.shize.homerepairapp;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DatabaseHandler {

    private static DatabaseHandler databaseSingleton;

    private ArrayList<Service> services;
    private ArrayList<String> serviceKeys;

    private ArrayList<Homeowner> homeowners;
    private ArrayList<String> homeownerKeys;

    private ArrayList<ServiceProvider> serviceProviders;
    private ArrayList<String> serviceProviderKeys;

    private ArrayList<Administrator> administrators;
    private ArrayList<String> administratorKeys;

    private DatabaseHandler() {

        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference();

        services = new ArrayList<>();
        serviceKeys = new ArrayList<>();
        dRef.child("services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();
                serviceKeys.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Service s = ds.getValue(Service.class);
                    services.add(s);
                    serviceKeys.add(ds.getKey());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("[Database Error]", databaseError.getMessage());
            }
        });

        homeowners = new ArrayList<>();
        homeownerKeys = new ArrayList<>();
        dRef.child("homeowners").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                homeowners.clear();
                homeownerKeys.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Homeowner ho = ds.getValue(Homeowner.class);
                    homeowners.add(ho);
                    homeownerKeys.add(ds.getKey());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("[Database Error]", databaseError.getMessage());
            }
        });

        serviceProviders = new ArrayList<>();
        serviceProviderKeys = new ArrayList<>();
        dRef.child("serviceProviders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                serviceProviders.clear();
                serviceProviderKeys.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ServiceProvider sp = ds.getValue(ServiceProvider.class);
                    serviceProviders.add(sp);
                    serviceProviderKeys.add(ds.getKey());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("[Database Error]", databaseError.getMessage());
            }
        });

        administrators = new ArrayList<>();
        administratorKeys = new ArrayList<>();
        dRef.child("administrators").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                administrators.clear();
                administratorKeys.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Administrator a = ds.getValue(Administrator.class);
                    administrators.add(a);
                    administratorKeys.add(ds.getKey());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("[Database Error]", databaseError.getMessage());
            }
        });
    }

    public static DatabaseHandler getInstance() {
        if (databaseSingleton == null) {
            databaseSingleton = new DatabaseHandler();
        }
        return databaseSingleton;
    }

    public ArrayList<Service> getAllServices() {
        return services;
    }

    public Service getService(String key) {
        int idx = serviceKeys.indexOf(key);
        return idx == -1 ? null : services.get(idx);
    }

    public boolean addService(Service service) {
        for (Service s : services) {
            if (s.toString().equals(service.toString())) {
                return false;
            }
        }
        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("services");
        String key = dRef.push().getKey();
        dRef.child(key).setValue(service);
        return true;
    }

    public boolean setService(String key, Service service) {
        int idx = serviceKeys.indexOf(key);
        if (idx == -1) {
            return false;
        }
        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("services");
        dRef.child(key).setValue(service);
        return true;
    }

    public ArrayList<Homeowner> getAllHomeowners() {
        return homeowners;
    }

    public Homeowner getHomeowner(String key) {
        int idx = homeownerKeys.indexOf(key);
        return idx == -1 ? null : homeowners.get(idx);
    }

    public boolean addHomeowner(Homeowner homeowner) {
        for (Homeowner ho : homeowners) {
            if (ho.getUsername().equals(homeowner.getUsername())) {
                return false;
            }
        }
        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("homeowners");
        String key = dRef.push().getKey();
        dRef.child(key).setValue(homeowner);
        return true;
    }

    public ArrayList<ServiceProvider> getAllServiceProviders() {
        return serviceProviders;
    }

    public ServiceProvider getServiceProvider(String key) {
        int idx = serviceProviderKeys.indexOf(key);
        return idx == -1 ? null : serviceProviders.get(idx);
    }

    public boolean addServiceProvider(ServiceProvider serviceProvider) {
        for (ServiceProvider sp : serviceProviders) {
            if (sp.getUsername().equals(serviceProvider.getUsername())) {
                return false;
            }
        }
        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("serviceProviders");
        String key = dRef.push().getKey();
        dRef.child(key).setValue(serviceProvider);
        return true;
    }

    public ArrayList<Administrator> getAllAdministrators() {
        return administrators;
    }

    public Administrator getAdministrator(String key) {
        int idx = administratorKeys.indexOf(key);
        return idx == -1 ? null : administrators.get(idx);
    }

    public boolean addAdministrator(Administrator administrator) {
        if (administrators.size() > 0) {
            return false;
        }
        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("administrators");
        String key = dRef.push().getKey();
        dRef.child(key).setValue(administrator);
        return true;
    }
}
