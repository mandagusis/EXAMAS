package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btn_logout, btn_addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btn_logout = findViewById(R.id.btn_logout);
        btn_addItem = findViewById(R.id.btn_addItem);

        btn_addItem.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Add_Items.class));
        });




        btn_logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext()
            , Login.class));
            finish();
        });

    }
}