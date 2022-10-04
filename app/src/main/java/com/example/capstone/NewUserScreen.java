package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class NewUserScreen extends AppCompatActivity {

    EditText newUsername, newPassword, employeeName, employeeType;
    Button save, cancel;
    CapstoneDB db;

    List<Employee> employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_screen);
        db = CapstoneDB.getInstance(getApplicationContext());

        save = findViewById(R.id.newUser_SaveBtn);
        cancel = findViewById(R.id.newUser_CancelBtn);

        newUsername = findViewById(R.id.newUser_Username);
        newPassword = findViewById(R.id.newUser_Password);
        employeeName = findViewById(R.id.newUser_EmployeeName);
        employeeType = findViewById(R.id.newUser_EmployeeType);

        employeeList = db.EmployeeDAO().getAllEmployees();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Employee employee = new Employee();
                employee.setUsername(newUsername.getText().toString());
                employee.setPassword(newPassword.getText().toString());
                employee.setName(employeeName.getText().toString());
                employee.setType(employeeType.getText().toString());

                boolean clear = true;

                    for (int i = 0; i < employeeList.size(); i++) {
                        if (employeeList.get(i).getUsername().equals(employee.getUsername())) {
                            Toast.makeText(NewUserScreen.this, "Username already exists", Toast.LENGTH_SHORT).show();
                            clear = false;
                            break;
                        }

                    }

                    if (clear){
                        db.EmployeeDAO().insertEmployee(employee);
                        Toast.makeText(NewUserScreen.this, "Login created", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        //intent.putExtra("empID", employee.getEmployee_id());
                        startActivity(intent);
                    }

            }
        });

    }
}