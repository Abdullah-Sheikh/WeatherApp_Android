package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class Screen1 extends AppCompatActivity {

    private EditText cityName;
    private String name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_screen1);

        cityName = (EditText) findViewById(R.id.city_name);
    }

    public void movetoScreen2(View view) {
        name = cityName.getText().toString();
        if (name.equals("")) {
            Toast.makeText(this, "Enter City name.....", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, Screen2.class);
            intent.putExtra("name",name );
            startActivity(intent);
            finish();
        }
    }

    public void backtoSplash(View view) {
        Intent intent = new Intent(this,SplashScreen.class);
        startActivity(intent);
        finish();
    }
}