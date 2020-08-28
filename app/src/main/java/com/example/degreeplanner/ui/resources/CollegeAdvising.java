package com.example.degreeplanner.ui.resources;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
        ImageButton closeButton = findViewById(R.id.college_back_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });

        findViewById(R.id.revelle_advising).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resource_clicked("https://revelle.ucsd.edu/academics/index.html");
            }
        }

        );

        findViewById(R.id.muir_advising).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resource_clicked("https://muir.ucsd.edu/academics/");
            }
        }

        );

        findViewById(R.id.marshall_advising).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resource_clicked("https://marshall.ucsd.edu/academics/academic-advising/index.html");
            }
        }

        );

        findViewById(R.id.warren_advising).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resource_clicked("https://warren.ucsd.edu/academics/advising/index.html");
            }
        }

        );

        findViewById(R.id.erc_advising).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               resource_clicked("https://roosevelt.ucsd.edu/academics/Advising%20Services/index.html");
           }
       }

       );

        findViewById(R.id.sixth_advising).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resource_clicked("https://sixth.ucsd.edu/academics/index.html");
            }
        }

        );

        findViewById(R.id.seventh_advising).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resource_clicked("https://seventh.ucsd.edu/academics/advising-services/index.html");
            }
        }

        );

    }

    public void resource_clicked(String url) {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}