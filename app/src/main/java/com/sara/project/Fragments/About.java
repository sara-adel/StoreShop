package com.sara.project.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sara.project.R;

/**
 * Created by sara on 1/27/2018.
 */

public class About extends Fragment {

    private FloatingActionButton fab;
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.about, container, false);

        //hide floatingActionButton
        fab = view.findViewById(R.id.addButton);
        fab.hide();

        title = view.findViewById(R.id.toolbar_title);
        title.setText("About");

        return view;
    }
}
