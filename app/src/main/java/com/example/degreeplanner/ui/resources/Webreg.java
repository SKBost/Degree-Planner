package com.example.degreeplanner.ui.resources;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.degreeplanner.R;

public class Webreg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webreg);

        // Close page once button is pressed
        ImageButton closeButton = findViewById(R.id.webreg_back_button);
        closeButton.setOnClickListener(v -> finish());

        findViewById(R.id.webreg_link).setOnClickListener(view -> resource_clicked("https://act.ucsd.edu/webreg2"));
    }

    public void resource_clicked(String url) {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}