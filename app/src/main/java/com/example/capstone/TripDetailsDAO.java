package com.example.capstone;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TripDetailsDAO {

    @Insert
    void insertTripDetails(TripDetails td);

    @Update
    void updateTripDetails(TripDetails td);

    @Delete
    void deleteTripDetails(TripDetails td);

    @Query("SELECT * FROM tripDetails_table WHERE trip_id = :trip_id")
    TripDetails getTripDetailsByID(int trip_id);

    //hnng
    @Query("SELECT * FROM tripDetails_table WHERE employee_id_fk = :employee_id")
    List<TripDetails> getTripDetailsList(int employee_id);

    @Query("DELETE FROM tripDetails_table")
    void nukeTripDetailsTable();

}
