package com.example.degreeplanner.ui.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.degreeplanner.ui.home.checklist.ChecklistTabFragment;

public class TabAdapter extends FragmentStateAdapter {

    private final int NUM_TABS = 2;

    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ChecklistTabFragment();
            case 1:
                return new QuarterPlanTabFragment();

        }
        return new ChecklistTabFragment();
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}

