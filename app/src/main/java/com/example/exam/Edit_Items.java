package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_Items extends AppCompatActivity {

    EditText mEdit_Name, mEdit_Course;

    Button btn_edit;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        mEdit_Name = findViewById(R.id.edit_Name);
        mEdit_Course = findViewById(R.id.edit_Course);

        btn_edit = findViewById(R.id.btn_edit);

        mDatabase = FirebaseDatabase.getInstance("https://stuff-a6bee-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Student");

        Bundle bundle = getIntent().getExtras();

        String Uid = bundle.getString("Uid");
        String Name = bundle.getString("Name");
        String Course = bundle.getString("Course");

        mEdit_Name.setText(Name);
        mEdit_Course.setText(Course);

        btn_edit.setOnClickListener(view -> {
            DatabaseReference ref = mDatabase.child(Uid);

            String edit_Mark = mEdit_Name.getText().toString();
            String edit_Model = mEdit_Course.getText().toString();

            ref.child("Mark").setValue(edit_Mark);
            ref.child("Model").setValue(edit_Model);

            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        });


    }
}