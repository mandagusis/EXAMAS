package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

public class Add_Items extends AppCompatActivity {

    EditText mText_Name, mText_Course;

    Button btn_save;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        mText_Name = findViewById(R.id.text_Name);
        mText_Course = findViewById(R.id.text_Course);

        btn_save = findViewById(R.id.btn_save);


        mDatabase = FirebaseDatabase.getInstance("https://stuff-a6bee-default-rtdb.europe-west1.firebasedatabase.app/").getReference();


        btn_save.setOnClickListener(view -> {

            String text_Name = mText_Name.getText().toString().trim();
            String text_Course = mText_Course.getText().toString().trim();

            writeNewStudent(text_Name, text_Course);

            startActivity(new Intent(getApplicationContext(), MainActivity.class));



        });




    }

    @IgnoreExtraProperties
    public class Student {

        public String Name;
        public String Course;

        public Student() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Student(String Name, String Course) {
            this.Name = Name;
            this.Course = Course;
        }

    }

    public void writeNewStudent( String Name, String Course) {
        Student student = new Student(Name, Course);

        mDatabase.child("Student").push().setValue(student);

    }
}