package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeScreen extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener{

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    CapstoneDB db;

    Intent intent;

    FloatingActionButton addTripFAB;

    List<TripDetails> tripDetailsList;

    int empID;

    EditText search;
    TextView stamp;

    Button sort;

    int sortcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        sortcode = 2;

        search = findViewById(R.id.homeScreen_Search);
        stamp = findViewById(R.id.startStamp);


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });


        sort = findViewById(R.id.sortByDateBtn);

        addTripFAB = findViewById(R.id.addTripFAB);

        intent = getIntent();
        empID = intent.getIntExtra("empID", -1);

        db = CapstoneDB.getInstance(getApplicationContext());
        tripDetailsList = db.TripDetailsDAO().getTripDetailsList(empID);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(db.TripDetailsDAO().getTripDetailsList(empID), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (sortcode % 2 == 0) {
                    sortcode++;
                    Collections.sort(tripDetailsList, new Comparator<TripDetails>() {
                        public int compare(TripDetails o1, TripDetails o2) {
                            return o2.getTimestamp().compareTo(o1.getTimestamp());
                        }
                    });
                }

                else{
                    //if (sortcode % 2 == 0){
                    sortcode++;
                    Collections.sort(tripDetailsList, new Comparator<TripDetails>() {
                        public int compare(TripDetails o1, TripDetails o2) {
                            return o1.getTimestamp().compareTo(o2.getTimestamp());
                        }
                    });
                }

                recyclerAdapter.notifyDataSetChanged();
                recyclerAdapter.setData(tripDetailsList);
            }
        });


        addTripFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.util.Date utilDate = new java.util.Date();
                System.out.println(empID + "-----888888888888888888888888888888888888888888888888888888");
                TripDetails newTD = new TripDetails();
                newTD.setEmployee_id_fk(empID);
                newTD.setDuration(0);
                newTD.setEstimated_cost(0);
                newTD.setTitle("New Trip");
                //newTD.setTimestamp(new Timestamp(System.currentTimeMillis()));
                newTD.setTimestamp(new java.sql.Timestamp(utilDate.getTime()).toString());
                tripDetailsList.add(newTD);
                db.TripDetailsDAO().insertTripDetails(newTD);
                //recyclerAdapter.setData(tripDetailsList);
                recyclerAdapter.notifyDataSetChanged();
                recyclerAdapter.setData(tripDetailsList);
            }
        });

        
    }

    private void filter(String text) {

        ArrayList<TripDetails> filteredList = new ArrayList<>();

        for (TripDetails td : tripDetailsList) {
            if (td.getTitle().toLowerCase().contains(text)){
                filteredList.add(td);
            }
        }

        recyclerAdapter.filterList(filteredList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.setData(tripDetailsList);

    }

    @Override
    protected void onStart() {
        super.onStart();
        List<TripDetails> newTD = db.TripDetailsDAO().getTripDetailsList(empID);

        recyclerAdapter = new RecyclerAdapter(newTD, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onItemClick(int position) {

        TripDetails td = tripDetailsList.get(position);
        Intent intent = new Intent(getApplicationContext(), EditTripDetails.class);
        intent.putExtra("td", td);
        intent.putExtra("empID", empID);

        startActivity(intent);

    }

}