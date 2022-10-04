package com.example.capstone;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    List<TripDetails> tripDetailsList;
    CapstoneDB db;
    //private final OnItemClickListener listener;
    private final OnItemClickListener monOnItemClickListener;

    public RecyclerAdapter(List<TripDetails> tripDetailsList, OnItemClickListener listener) {
        this.tripDetailsList = tripDetailsList;
        this.monOnItemClickListener = listener;
    }

    /*public RecyclerAdapter(List<TripDetails> tripDetailsList) {
        this.tripDetailsList = tripDetailsList;
    }*/



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, monOnItemClickListener);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.bind(tripDetailsList.get(position), listener);

        holder.id_TV.setText(String.valueOf(position));
        holder.title_TV.setText(tripDetailsList.get(position).getTitle());
        holder.stamp_TV.setText( "Created: " + tripDetailsList.get(position).getTimestamp());

    }

    @Override
    public int getItemCount() {
        return tripDetailsList.size();
    }

    public void setData(List<TripDetails> data){
        this.tripDetailsList = data;
        notifyDataSetChanged();
        // setting in adapter=new Adapter(this,db.getData());
    }

    public void filterList(ArrayList<TripDetails> filteredList) {
        tripDetailsList = filteredList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id_TV;// = itemView.findViewById(R.id.id_textView);
        TextView title_TV;// = itemView.findViewById(R.id.title_textView);
        TextView stamp_TV;
        OnItemClickListener listener;


        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            id_TV = itemView.findViewById(R.id.id_textView);
            title_TV = itemView.findViewById(R.id.title_textView);
            stamp_TV = itemView.findViewById(R.id.startStamp);
            id_TV.setTextSize(18);
            title_TV.setTextSize(18);
            stamp_TV.setTextSize(12);
            title_TV.setTypeface(Typeface.DEFAULT_BOLD);
            this.listener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
