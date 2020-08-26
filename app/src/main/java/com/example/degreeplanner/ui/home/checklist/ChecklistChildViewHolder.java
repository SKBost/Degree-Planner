package com.example.degreeplanner.ui.home.checklist;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.degreeplanner.R;

public class ChecklistChildViewHolder extends ChildViewHolder {
    public TextView mCourseNameText;

    public ChecklistChildViewHolder(View itemView) {
        super(itemView);

        mCourseNameText = itemView.findViewById(R.id.expandedListItem);
    }
}
