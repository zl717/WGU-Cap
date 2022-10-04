package com.example.capstone;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(value = AndroidJUnit4.class)
public class CapstoneDBTest {

    private CapstoneDB db;
    Employee employee;
    TripDetails tripDetails;

    @Before
    public void setUp() throws Exception {

        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, CapstoneDB.class).build();

        employee = new Employee();
        employee.setType("test");
        employee.setPassword("test");
        employee.setUsername("test");
        employee.setName("test");
        employee.setEmployee_id(1);
        db.EmployeeDAO().insertEmployee(employee);

        tripDetails = new TripDetails();
        tripDetails.setDuration(0);
        tripDetails.setEstimated_cost(0);
        tripDetails.setTitle("title");
        tripDetails.setEmployee_id_fk(1);
        tripDetails.setTimestamp("");
        tripDetails.setTrip_id(1);
        db.TripDetailsDAO().insertTripDetails(tripDetails);
    }

    @After
    public void closeDB(){
        db.close();
    }

    @Test
    public void testEmployeeDAO(){
        List<Employee> test = db.EmployeeDAO().getAllEmployees();
        Assert.assertEquals(employee.getEmployee_id(), test.get(0).getEmployee_id());
    }

    @Test
    public void testTripDetailsDAO(){
        List<TripDetails> test = db.TripDetailsDAO().getTripDetailsList(employee.getEmployee_id());
        Assert.assertEquals(tripDetails.getTrip_id(), test.get(0).getTrip_id());
    }

    @Test
    public void testUpdateEmployee(){
        Employee updatedEmployee = employee;
        updatedEmployee.setName("Chester");
        db.EmployeeDAO().updateEmployee(employee);

        List<Employee> test = db.EmployeeDAO().getAllEmployees();
        Assert.assertEquals(employee.getName(), test.get(0).getName());

    }

    @Test
    public void testUpdateTripDetails(){
        TripDetails updatedTripDetails = tripDetails;
        updatedTripDetails.setTitle("new title");
        db.TripDetailsDAO().updateTripDetails(updatedTripDetails);

        List<TripDetails> test = db.TripDetailsDAO().getTripDetailsList(employee.getEmployee_id());
        Assert.assertEquals(updatedTripDetails.getTitle(), test.get(0).getTitle());

    }

    @Test
    public void testDeleteEmployee(){
        db.EmployeeDAO().deleteEmployee(employee);
        List<Employee> test = db.EmployeeDAO().getAllEmployees();
        assertEquals(0, test.size());

    }

    @Test
    public void testDeleteTripDetails(){
        db.TripDetailsDAO().deleteTripDetails(tripDetails);
        List<TripDetails> test = db.TripDetailsDAO().getTripDetailsList(employee.getEmployee_id());
        assertEquals(0, test.size());
    }

    //@Test
    /*public void allTests(){
        testDeleteEmployee();
        testDeleteTripDetails();
        testEmployeeDAO();
        testTripDetailsDAO();
        testUpdateEmployee();
        testUpdateTripDetails();

    }*/

}