package com.example.degreeplanner.ui.home.checklist;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.degreeplanner.R;

public class ChecklistParentViewHolder extends ParentViewHolder {
    public TextView mListTitleTextView;
    public ImageButton mParentDropDownArrow;

    public ChecklistParentViewHolder(View itemView) {
        super(itemView);
        mListTitleTextView = itemView.findViewById(R.id.listTitle);
        mParentDropDownArrow = itemView.findViewById(R.id.parent_list_item_expand_arrow);
    }
}
