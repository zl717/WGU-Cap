package com.example.capstone;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Timestamp;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "tripDetails_table",
        foreignKeys = @ForeignKey(
        entity = Employee.class,
        parentColumns = "employee_id",
        childColumns = "employee_id_fk",
        onDelete = CASCADE))

public class TripDetails implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int trip_id;

    //foreign key
    @ColumnInfo(name = "employee_id_fk", index = true)
    private int employee_id_fk;

    @ColumnInfo(name = "duration")
    private int duration;

    @ColumnInfo(name = "estimated_cost")
    private double Estimated_cost;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "start")
    private String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public void setEmployee_id_fk(int employee_id_fk) {
        this.employee_id_fk = employee_id_fk;
    }

    public int getEmployee_id_fk() {
        return employee_id_fk;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getEstimated_cost() {
        return Estimated_cost;
    }

    public void setEstimated_cost(double estimated_cost) {
        Estimated_cost = estimated_cost;
    }

}
