package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.db.WeatherDatabase;
import com.example.weatherapp.db.entities.cityName;
import com.example.weatherapp.db.entities.cityWeather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

public class Screen2 extends AppCompatActivity {



    private WeatherDatabase db;
    private String  name, url;
    private TextView cityTextView , countryTextView , temperatureTextView , descriptionTextView;
    private TextView windSpeedTextView, windDirectionTextView, pressureTextView , humdidityTextView;
    private JSONObject find;
    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_screen2);

         cityTextView= (TextView) findViewById(R.id.city_name_textView);
         countryTextView= (TextView) findViewById(R.id.country_name_textView);
         temperatureTextView= (TextView) findViewById(R.id.temperature_textView);
         descriptionTextView= (TextView) findViewById(R.id.description_textView);
         windSpeedTextView= (TextView) findViewById(R.id.wind_speed_textView);
         windDirectionTextView= (TextView) findViewById(R.id.wind_direction_textView);
         pressureTextView= (TextView) findViewById(R.id.pressure_textView);
         humdidityTextView= (TextView) findViewById(R.id.humidity_textView);

        db = Room.databaseBuilder(this, WeatherDatabase.class, "student-db").allowMainThreadQueries().build();


        name= getIntent().getStringExtra("name");
        url = "https://api.weatherbit.io/v2.0/current?&city=" + name + "&country=PK&key=04fbd49de9854da4a685138e47758298";


        Search();




    }


    private void Search() {


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {


                cityName city = db.CityDAO().getCity(name.toLowerCase());
                if(city ==null)
                {

                    cityName c = new cityName();
                    c.cityName = name;
                    db.CityDAO().insert(c);
                    Toast.makeText(this, "Hello ", Toast.LENGTH_SHORT).show();


                }

                cityName city1 = db.CityDAO().getCity(name);
                Toast.makeText(this, "City name is : "+ city1.cityName, Toast.LENGTH_SHORT).show();




                JSONArray details = response.getJSONArray("data");





                find = details.getJSONObject(0);
                char tmp = 0x00B0;

                cityTextView.setText(find.getString("city_name"));
                if(find.getString("country_code").equals("PK")) {
                    countryTextView.setText("Pakistan");
                }
                temperatureTextView.setText(find.getString("temp")+" C");
                JSONObject weather = find.getJSONObject("weather");
                String desc = weather.getString("description");
                descriptionTextView.setText(desc);
                windSpeedTextView.setText(find.getString("wind_spd"));
                windDirectionTextView.setText(find.getString("wind_dir"));
                pressureTextView.setText(find.getString("pres"));
                humdidityTextView.setText(find.getString("rh"));

                cityWeather cityWeathers = new cityWeather();
                cityWeathers.cityid = city1.cityid;
                cityWeathers.description = weather.getString("description");
                cityWeathers.windSpeed = find.getString("wind_spd");
                cityWeathers.humidity = find.getString("rh");
                cityWeathers.percip = find.getString("pres");
                cityWeathers.windDir = find.getString("wind_dir");
                cityWeathers.recordDate =  new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                cityWeathers.temperature = find.getString("temp")+" C";

                db.CityDAO().insert(cityWeathers);





               





            } catch (JSONException e) {
                Toast.makeText(this, "Something went wrong, Please try again and check the city name", Toast.LENGTH_SHORT).show();

            }
        }, error -> {

        });

        Volley.newRequestQueue(this).add(request);

    }

    public void backtoscreen1(View view) {
        Intent intent = new Intent(this,Screen1.class);
        startActivity(intent);
        finish();
    }


    public void movetohistory(View view) {
        Intent intent = new Intent(this,WeatherHistoryActivity.class);
        startActivity(intent);

    }





}