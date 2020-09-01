package com.example.degreeplanner.activities;

import android.os.Bundle;
import android.util.Log;

import com.example.degreeplanner.R;
import com.example.degreeplanner.ui.home.SharedHomeViewModel;
import com.example.degreeplanner.ui.profile.ProfileFragment;
import com.example.degreeplanner.ui.profile.ProfileViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private ProfileViewModel profileViewModel;
    String myMajor;
    String myMinor;
    String myCollege;
    String myYearEntered;
    String myGraduatingYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
          //      R.id.navigation_home, R.id.navigation_requirements, R.id.navigation_resources)
          //      .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        myMajor = getIntent().getStringExtra("Major");
        myMinor = getIntent().getStringExtra("Minor");
        myCollege = getIntent().getStringExtra("College");
        myYearEntered = getIntent().getStringExtra("Year Entered");
        myGraduatingYear = getIntent().getStringExtra("Graduating Year");

        this.errorCheck();

        // Send profile information to the view model
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.setProfileInformation(myMajor, myMinor, myCollege, myYearEntered,
                myGraduatingYear);

        // Create schedule to be used across child fragments
        SharedHomeViewModel mViewModel = new ViewModelProvider(this).get(SharedHomeViewModel.class);
        mViewModel.setYearEntered(profileViewModel.getYearEntered());
        mViewModel.setNumYears(profileViewModel.getNumYears());
        mViewModel.createQuarters();

    }

    /*
     * Error check welcome page inputs
     */
    public void errorCheck() {
        if (myMajor.equals("")) {
            myMajor = "N/A";
        }
        if (myMinor.equals("")) {
            myMinor = "N/A";
        }
        if (myCollege.equals("")) {
            myCollege = "N/A";
        }
        if (myYearEntered.equals("")) {
            myYearEntered = "2019";
        }
        if (myGraduatingYear.equals("")) {
            myGraduatingYear = "2023";
        }
    }
}