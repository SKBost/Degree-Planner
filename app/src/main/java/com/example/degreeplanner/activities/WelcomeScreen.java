package com.example.degreeplanner.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.degreeplanner.R;
import com.example.degreeplanner.ui.profile.ProfileFragment;

public class WelcomeScreen extends AppCompatActivity {
    EditText major;
    EditText minor;
    EditText college;
    EditText yearEntered;
    EditText graduatingYear;


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
        intent.putExtra("Major", major.getText().toString());
        intent.putExtra("Minor", minor.getText().toString());
        intent.putExtra("College", college.getText().toString());
        intent.putExtra("Year Entered", yearEntered.getText().toString());
        intent.putExtra("Graduating Year", graduatingYear.getText().toString());
        startActivity(intent);
    }

    /*
     * Function to open home screen
     */
    public void openSecondWelcomeScreen() {
        setContentView(R.layout.activity_welcome_screen);
        // Inflate all text views
        major = findViewById(R.id.major);
        minor = findViewById(R.id.minor);
        college = findViewById(R.id.college);
        yearEntered = findViewById(R.id.year_entered);
        graduatingYear = findViewById(R.id.graduating_year);
        // Move to home screen once button is pressed
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // Open home screen
                openHomeScreen();
            }
        });
    }

}