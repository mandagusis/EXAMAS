package com.example.exam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.text.BreakIterator;
import java.util.ArrayList;

public class studentAdapter extends RecyclerView.Adapter<studentAdapter.MyViewHolder>{
    ArrayList<String> Name;
    ArrayList<String> Course;
    ArrayList<String> Uid;
    DatabaseReference mDatabase;
    Context ctx;

    public studentAdapter(ArrayList<String> Name, ArrayList<String> Course, ArrayList<String> Uid, Context ctx, DatabaseReference mDatabase) {
        this.Name = Name;
        this.Course = Course;
        this.Uid = Uid;
        this.ctx = ctx;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.Name.setText(Name.get(position));
        holder.Course.setText(Course.get(position));
        holder.btn_update.setOnClickListener(view -> {

            Intent edit = new Intent(ctx.getApplicationContext(), Edit_Items.class);

            Bundle bundle = new Bundle();

            bundle.putString("Uid", Uid.get(position));
            bundle.putString("Name", Name.get(position));
            bundle.putString("Course", Course.get(position));
            edit.putExtras(bundle);

            ctx.startActivity(edit);

        });
        holder.btn_delete.setOnClickListener(view -> {
            DatabaseReference ref = mDatabase.child(Uid.get(position));
            ref.removeValue();

            ctx.startActivity(new Intent(ctx.getApplicationContext(),MainActivity.class));

        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, Name.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Name, Course;
        Button btn_update, btn_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            Name = itemView.findViewById(R.id.Name);
            Course = itemView.findViewById(R.id.Course);
            btn_update = itemView.findViewById(R.id.btn_update);
            btn_delete = itemView.findViewById(R.id.btn_delete);



        }

    }
}
