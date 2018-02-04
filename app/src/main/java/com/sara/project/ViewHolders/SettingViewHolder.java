package com.sara.project.ViewHolders;

import android.util.Log;
import android.view.View;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by sara on 1/30/2018.
 */

public class SettingViewHolder extends GroupViewHolder {

    //  private TextView parentSettings;

    public SettingViewHolder(View itemView) {
        super(itemView);

        //    parentSettings = itemView.findViewById(R.id.parent_text);
    }

    @Override
    public void expand() {
        //  parentSettings.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down, 0);
        Log.i("Adapter", "expand");
    }

    @Override
    public void collapse() {
        Log.i("Adapter", "collapse");
        //parentSettings.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up, 0);
    }

    public void setGroupName(ExpandableGroup group) {

        //parentSettings.setText(group.getTitle());
    }

}
