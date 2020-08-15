package com.example.degreeplanner.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.degreeplanner.R;
import com.example.degreeplanner.ui.requirements.AddRequirement;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        //Test quarter plan image


        // Move to add requirements screen once button is pressed
        FloatingActionButton editPlanBtn = root.findViewById(R.id.edit_quarter_plan_button);
        editPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openEditQuarterPlan(container, v);
            }
        });

        return root;
    }

    /*
     * Function to open the edit quarter plan page on click
     */
    public void openEditQuarterPlan(ViewGroup container, View view) {
        // inflate the layout of the popup window
        View popupView = getLayoutInflater().inflate(R.layout.popup_window, container, false);

        // create the popup window
        int width = RelativeLayout.LayoutParams.MATCH_PARENT;
        int height = RelativeLayout.LayoutParams.WRAP_CONTENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, 550, true);



        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 600);
    }
}


