package com.example.degreeplanner.ui.resources;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.degreeplanner.R;

public class DepartmentAdvising extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_advising);

        // Close page once button is pressed
        ImageButton closeButton = findViewById(R.id.department_back_button);
        closeButton.setOnClickListener(v -> finish());

        findViewById(R.id.all_department_advising).setOnClickListener(view -> resource_clicked("https://blink.ucsd.edu/instructors/resources/advisor-lists/departments.html"));
    }

    public void resource_clicked(String url) {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}