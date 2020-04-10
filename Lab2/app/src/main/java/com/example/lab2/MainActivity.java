package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.Menu;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String GAME_STATE_KEY = "";



    String gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            gameState = savedInstanceState.getString(GAME_STATE_KEY);
        }

        setContentView(R.layout.activity_main);



        Button listview = findViewById(R.id.button_listview);
        Button saveload = findViewById(R.id.button_saveload);
        Button sensors = findViewById(R.id.button_sensors);
        Button GPS = findViewById(R.id.button_gps);
        Button Camera = findViewById(R.id.button_camera);

        listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openList();
            }
        });

        saveload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSaveLoad();
            }
        });

        sensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSensors();
            }
        });

        GPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGps();
            }
        });

        Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });


    }
    public void openList(){
        Intent intent = new Intent(this, ListView.class);
        startActivity(intent);
    }

    public void openSaveLoad(){
        Intent intent = new Intent(this, SaveLoad.class);
        startActivity(intent);
    }

     public void openSensors(){
         Intent intent = new Intent(this, Sensors.class);
         startActivity(intent);
     }

    public void openGps(){
        Intent intent = new Intent(this, GPS.class);
        startActivity(intent);
    }

    public void openCamera(){
        Intent intent = new Intent(this, Camera.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2:
                Toast.makeText(this, "Afisez ce vreau eu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                openHelp();
                return true;
            case R.id.item4:
                openSettings();
                return true;
             default:
                 return super.onOptionsItemSelected(item);
        }
    }

    public void openHelp(){
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }

    public void openSettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }




    //////////////////////////////////////////////////////////////////




}
