package com.example.degreeplanner.ui.requirements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.degreeplanner.R;
import com.example.degreeplanner.activities.MainActivity;

public class AddRequirement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_requirement);

        // Close page once button is pressed
        ImageButton closeButton = findViewById(R.id.close_page_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });
/*
        // Close and save page once button is pressed
        Button saveButton = findViewById(R.id.save_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // Save input data to a database
                // Save course name as a bundle
                finish();
            }
        });

 */
    }


}