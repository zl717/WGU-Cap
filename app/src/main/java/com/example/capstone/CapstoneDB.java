package com.example.capstone;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class, TripDetails.class}, version = 6)
public abstract class CapstoneDB extends RoomDatabase {

    private static final String DB_NAME = "capstone.db";
    private static CapstoneDB instance;

    public static synchronized CapstoneDB getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CapstoneDB.class, DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }

        return instance;

    }

    public abstract TripDetailsDAO TripDetailsDAO();
    public abstract EmployeeDAO EmployeeDAO();

}
