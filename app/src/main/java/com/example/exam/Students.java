package com.example.exam;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Students {
        public String Name;
        public String Course;
        public String Uid;
        public Map<String, Boolean> stars = new HashMap<>();

        public Students() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Students(String Name, String Course, String Uid) {
            this.Uid = Uid;
            this.Name = Name;
            this.Course = Course;
        }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Name", Name);
        result.put("Course", Course);
        result.put("stars", stars);

        return result;
    }

}
