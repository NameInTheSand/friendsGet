package com.example.friendsget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<String> names ; // for setting fields
    private ArrayList<String> surnames;  // for setting fields
    private int size; // because setHasFixedSize(true)

    public RecyclerAdapter (ArrayList<String> names, ArrayList<String> surnames, int size) { //constructor
        this.size= size;
        this.names =(ArrayList<String>)names.clone();
        this.surnames=(ArrayList<String>)surnames.clone();
        // upper setting arrays and size
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) { //setting View elements
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.element_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_name.setText(names.get(i));//setting fields
        viewHolder.tv_surname.setText(surnames.get(i));
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_surname; // in file elements_item.xml

        public ViewHolder(View view) {
            super(view);

            tv_name = view.findViewById(R.id.tv_name);
            tv_surname = view.findViewById(R.id.tv_surname);

        }
    }
}
