package com.example.degreeplanner.ui.resources;

import android.graphics.Color;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.degreeplanner.R;

public class ResourcesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ResourcesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is resources fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}