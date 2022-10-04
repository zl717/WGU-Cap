package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditTripDetails extends AppCompatActivity {

    TextView title, duration, cost;

    Button save, cancel, delete;

    TripDetails td;
    int empID;

    CapstoneDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip_details);

        td = (TripDetails)getIntent().getSerializableExtra("td");
        db = CapstoneDB.getInstance(getApplicationContext());
        empID = getIntent().getIntExtra("empID", -1);

        title = findViewById(R.id.editTrip_TitleTV);
        duration = findViewById(R.id.editTrip_DurationTV);
        cost = findViewById(R.id.editTrip_CostTV);

        //assert td != null;
        title.setText(td.getTitle());
        duration.setText(String.valueOf(td.getDuration()));
        cost.setText(String.valueOf(td.getEstimated_cost()));

        save = findViewById(R.id.editTrip_SaveBtn);
        cancel = findViewById(R.id.editTrip_CancelBtn);
        delete = findViewById(R.id.editTrip_DeleteBtn);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.TripDetailsDAO().deleteTripDetails(td);
                Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                intent.putExtra("empID", empID);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                intent.putExtra("empID", empID);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    td.setTitle(title.getText().toString());
                    td.setEstimated_cost(Double.parseDouble(cost.getText().toString()));
                    td.setDuration(Integer.parseInt(duration.getText().toString()));
                    db.TripDetailsDAO().updateTripDetails(td);
                    Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                    intent.putExtra("td", td);
                    intent.putExtra("empID", empID);
                    startActivity(intent);
                }

                catch (Exception e){
                    Toast.makeText(EditTripDetails.this, "failed to save", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}