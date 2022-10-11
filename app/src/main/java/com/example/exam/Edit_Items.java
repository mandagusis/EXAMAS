package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_Items extends AppCompatActivity {

    EditText mEdit_Mark, mEdit_Model;

    Button btn_edit;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        mEdit_Mark = findViewById(R.id.edit_Mark);
        mEdit_Model = findViewById(R.id.edit_Model);

        btn_edit = findViewById(R.id.btn_edit);

        mDatabase = FirebaseDatabase.getInstance("https://exam-40237-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Car");

        Bundle bundle = getIntent().getExtras();

        String Uid = bundle.getString("Uid");
        String Mark = bundle.getString("Mark");
        String Model = bundle.getString("Model");

        mEdit_Mark.setText(Mark);
        mEdit_Model.setText(Model);

        btn_edit.setOnClickListener(view -> {
            DatabaseReference ref = mDatabase.child(Uid);

            String edit_Mark = mEdit_Mark.getText().toString();
            String edit_Model = mEdit_Model.getText().toString();

            ref.child("Mark").setValue(edit_Mark);
            ref.child("Model").setValue(edit_Model);

            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        });


    }
}