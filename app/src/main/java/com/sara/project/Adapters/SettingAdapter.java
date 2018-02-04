package com.sara.project.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sara.project.ExpandRecycleClasses.LanguageChild;
import com.sara.project.ExpandRecycleClasses.LanguageParent;
import com.sara.project.R;
import com.sara.project.ViewHolders.LanguageViewHolder;
import com.sara.project.ViewHolders.SettingViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by sara on 1/30/2018.
 */

public class SettingAdapter extends ExpandableRecyclerViewAdapter<SettingViewHolder, LanguageViewHolder> {

    private Context context;

    public SettingAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.context = context;
    }

    @Override
    public SettingViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_parent, parent, false);

        return new SettingViewHolder(view);
    }

    @Override
    public LanguageViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_child_settings, parent, false);

        return new LanguageViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(LanguageViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final LanguageChild child = ((LanguageParent) group).getItems().get(childIndex);
        holder.onBind(child, group);
    }

    @Override
    public void onBindGroupViewHolder(SettingViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGroupName(group);
    }
}
