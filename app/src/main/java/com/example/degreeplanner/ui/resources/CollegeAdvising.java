package com.example.degreeplanner.ui.resources;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.degreeplanner.R;

public class CollegeAdvising extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_advising);

        // Close page once button is pressed
        /* note: commented out until you actually make this button so I can compile; TODO
        ImageButton closeButton = findViewById(R.id.college_back_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });
         */
    }
}