package com.example.degreeplanner.ui.requirements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.degreeplanner.R;

public class RequirementsFragment extends Fragment {

    private RequirementsViewModel requirementsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        requirementsViewModel =
                ViewModelProviders.of(this).get(RequirementsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_requirements, container, false);
        final TextView textView = root.findViewById(R.id.text_requirements);
        requirementsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}