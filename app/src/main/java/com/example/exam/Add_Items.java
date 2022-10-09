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

    EditText mText_Mark, mText_Model;

    Button btn_save;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        mText_Mark = findViewById(R.id.text_Mark);
        mText_Model = findViewById(R.id.text_Model);

        btn_save = findViewById(R.id.btn_save);

        //Firebase get instance pasikeistite į savo
        mDatabase = FirebaseDatabase.getInstance("https://exam-40237-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

        btn_save.setOnClickListener(view -> {

            String text_Mark = mText_Mark.getText().toString().trim();
            String text_Model = mText_Model.getText().toString().trim();

            writeNewCar(text_Mark, text_Model);

            startActivity(new Intent(getApplicationContext(), MainActivity.class));



        });




    }

    @IgnoreExtraProperties
    public class Car {

        public String Mark;
        public String Model;

        public Car() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Car(String Mark, String Model) {
            this.Mark = Mark;
            this.Model = Model;
        }

    }

    public void writeNewCar( String Mark, String Model) {
        Car car = new Car(Mark, Model);

        mDatabase.child("Car").child("1").setValue(car);
    }
}