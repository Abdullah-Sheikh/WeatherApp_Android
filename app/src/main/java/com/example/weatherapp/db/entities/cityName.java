package com.example.weatherapp.db.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cityName")
public  class cityName {



    @PrimaryKey(autoGenerate = true)
    public int cityid;

    @ColumnInfo(name= "city_name")
    public String cityName;

}