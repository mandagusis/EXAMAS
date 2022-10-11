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

import java.util.ArrayList;

public class carAdapter extends RecyclerView.Adapter<carAdapter.MyViewHolder>{
    ArrayList<String> Mark;
    ArrayList<String> Model;
    ArrayList<String> Uid;
    DatabaseReference mDatabase;
    Context ctx;

    public carAdapter(ArrayList<String> Mark, ArrayList<String> Model, ArrayList<String> Uid, Context ctx, DatabaseReference mDatabase) {
        this.Mark = Mark;
        this.Model = Model;
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

        holder.Mark.setText(Mark.get(position));
        holder.Model.setText(Model.get(position));
        holder.btn_update.setOnClickListener(view -> {

            Intent edit = new Intent(ctx.getApplicationContext(), Edit_Items.class);

            Bundle bundle = new Bundle();

            bundle.putString("Uid", Uid.get(position));
            bundle.putString("Mark", Mark.get(position));
            bundle.putString("Model", Model.get(position));
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
                Toast.makeText(ctx, Mark.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Mark.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Mark, Model;
        Button btn_update, btn_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            Mark = itemView.findViewById(R.id.Mark);
            Model = itemView.findViewById(R.id.Model);
            btn_update = itemView.findViewById(R.id.btn_update);
            btn_delete = itemView.findViewById(R.id.btn_delete);



        }

    }
}
