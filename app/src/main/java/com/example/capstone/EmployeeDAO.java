package com.example.capstone;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDAO {

    @Insert
    void insertEmployee(Employee employee);

    @Update
    void updateEmployee(Employee employee);

    @Delete
    void deleteEmployee(Employee employee);

    @Query("SELECT * FROM employee_info_table ")
    List<Employee> getAllEmployees();

    @Query("SELECT * FROM Employee_info_table WHERE Username = :username AND password = :password")
    Employee employeeLoginCheck(String username, String password);

    @Query("SELECT employee_id FROM Employee_info_table WHERE Username = :username AND password = :password")
    int employeeLoginGetId(String username, String password);

    @Query("SELECT * FROM Employee_info_table WHERE employee_id = :employee_id")
    Employee getEmployeeByID(int employee_id);

    @Query("DELETE FROM Employee_info_table")
    void nukeEmployeeTable();

}
