package com.sara.project.ExpandRecycleClasses;

import android.annotation.SuppressLint;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by sara on 1/30/2018.
 */

@SuppressLint("ParcelCreator")
public class LanguageParent extends ExpandableGroup<LanguageChild> {

    public LanguageParent(String title, List<LanguageChild> items) {
        super(title, items);
    }

}
