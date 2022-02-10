package com.example.weatherapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.weatherapp.db.dao.cityDAO;
import com.example.weatherapp.db.entities.cityName;
import com.example.weatherapp.db.entities.cityWeather;




@Database(entities = {cityWeather.class , cityName.class} ,version = 1)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract cityDAO CityDAO();
}
