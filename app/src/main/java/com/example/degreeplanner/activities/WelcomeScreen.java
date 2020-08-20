package com.example.degreeplanner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.degreeplanner.R;

public class WelcomeScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_welcome_screen);

        // Move to second welcome screen once button is pressed
        Button button_initial = findViewById(R.id.button_first_welcome);
        button_initial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openSecondWelcomeScreen();
            }
        });

    }

    /*
     * Function to open home screen
     */
    public void openHomeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /*
     * Function to open home screen
     */
    public void openSecondWelcomeScreen() {
        setContentView(R.layout.activity_welcome_screen);
        // Move to home screen once button is pressed
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openHomeScreen();
            }
        });
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }

}