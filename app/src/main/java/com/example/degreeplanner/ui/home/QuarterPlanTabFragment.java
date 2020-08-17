package com.example.degreeplanner.ui.home;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.degreeplanner.R;

public class QuarterPlanTabFragment extends Fragment {

    private QuarterPlanTabViewModel mViewModel;

    public static QuarterPlanTabFragment newInstance() {
        return new QuarterPlanTabFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quarter_plan_tab_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QuarterPlanTabViewModel.class);
        // TODO: Use the ViewModel
    }

}