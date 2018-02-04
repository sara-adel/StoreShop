package com.sara.project.ViewHolders;

import android.view.View;

import com.sara.project.ExpandRecycleClasses.LanguageChild;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by sara on 1/30/2018.
 */

public class LanguageViewHolder extends ChildViewHolder {

    ///private RadioButton langName;

    public LanguageViewHolder(View itemView) {
        super(itemView);

        // langName = itemView.findViewById(R.id.radio_lang);
    }

    public void onBind(LanguageChild child, ExpandableGroup group) {
        // langName.setText(child.getLang());
        // if (group.getTitle().equals("Language")) {
        //  langName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nexus, 0, 0, 0);
        //}
    }
}