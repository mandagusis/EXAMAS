package com.example.exam;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    Button btn_logout, btn_addItem;

    RecyclerView recyclerView;

    ArrayList<String> modelText = new ArrayList<>();
    ArrayList<String> markText = new ArrayList<>();
    ArrayList<String> uidText = new ArrayList<>();

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_logout = findViewById(R.id.btn_logout);
        btn_addItem = findViewById(R.id.btn_addItem);

        recyclerView = findViewById(R.id.items_view);

        mDatabase = FirebaseDatabase.getInstance("https://exam-40237-default-rtdb.europe-west1.firebasedatabase.app").getReference("Car");


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ValueEventListener carListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1:snapshot.getChildren())
                {
                    markText.add(dataSnapshot1.child("Mark").getValue().toString());
                    modelText.add(dataSnapshot1.child("Model").getValue().toString());
                    uidText.add(dataSnapshot1.getKey());



                }
                carAdapter car_Adapter = new carAdapter( markText, modelText, uidText,MainActivity.this, mDatabase);
                recyclerView.setAdapter(car_Adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadCar:onCancelled", error.toException());
            }
        };
        mDatabase.addValueEventListener(carListener);

        btn_addItem.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Add_Items.class));
        });


        btn_logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext()
            , Login.class));
            finish();
        });

//        carAdapter car_Adapter = new carAdapter( markText, modelText,MainActivity.this);
//        recyclerView.setAdapter(car_Adapter);

    }


}

