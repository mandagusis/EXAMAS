package com.example.exam;

import android.view.Display;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Car {
        public String Mark;
        public String Model;
        public String Uid;
        public Map<String, Boolean> stars = new HashMap<>();

        public Car() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Car(String Mark, String Model, String Uid) {
            this.Uid = Uid;
            this.Mark = Mark;
            this.Model = Model;
        }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Mark", Mark);
        result.put("Model", Model);
        result.put("stars", stars);

        return result;
    }

}
