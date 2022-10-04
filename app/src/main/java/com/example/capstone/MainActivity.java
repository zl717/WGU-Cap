package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    CapstoneDB db;

    List<TripDetails> tripDetailsList;

    EditText username_TV, password_TV;
    Button newUserBtn;
    Button loginBtn;

    List<Employee> employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = CapstoneDB.getInstance(getApplicationContext());

        username_TV = findViewById(R.id.username);
        password_TV = findViewById(R.id.password);
        newUserBtn = findViewById(R.id.newUserBtn);
        loginBtn = findViewById(R.id.loginBtn);

        newUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewUserScreen.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //db.EmployeeDAO().getEmployeeByID(empID);
                try {

                    String user = username_TV.getText().toString();
                    String pass = password_TV.getText().toString();

                    int id = db.EmployeeDAO().employeeLoginGetId(user,pass);

                    if (id > 0) {


                        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                        intent.putExtra("empID", id);
                        startActivity(intent);
                    }

                    else
                        Toast.makeText(MainActivity.this, "Username doesn't exist", Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}