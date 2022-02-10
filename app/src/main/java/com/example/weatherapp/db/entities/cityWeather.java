package com.example.weatherapp.db.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city_weather")
public  class cityWeather {

    @PrimaryKey(autoGenerate = true)
    public int recordid;

    @ColumnInfo(name= "city_id")
    public int cityid;

    @ColumnInfo(name= "record_date")
    public String recordDate;

    @ColumnInfo(name= "temperature")
    public String temperature;
    @ColumnInfo(name= "description")
    public String description;

    @ColumnInfo(name="windSpeed")
    public String windSpeed ;

    @ColumnInfo(defaultValue = "windDir")
    public String windDir;
    @ColumnInfo(defaultValue = "precip")
    public String percip;
    @ColumnInfo(defaultValue = "humidity")
    public String humidity;
}