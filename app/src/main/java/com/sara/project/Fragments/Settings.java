package com.sara.project.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sara.project.LanguageHelper;
import com.sara.project.R;

/**
 * Created by sara on 1/27/2018.
 */

public class Settings extends Fragment {

    View view;
    private FloatingActionButton fab;
    private TextView language;
    private RadioGroup group;
    private RadioButton arabic, english, french;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.settings, container, false);

//        InitViews();

        //hide floatingActionButton
//        fab = view.findViewById(R.id.addButton);
//        fab.hide();

        language = view.findViewById(R.id.parent_text);
        group = view.findViewById(R.id.group);
        arabic = view.findViewById(R.id.lang_arabic);
        english = view.findViewById(R.id.lang_english);
        french = view.findViewById(R.id.lang_french);

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down, 0);
                group.setVisibility(View.VISIBLE);
            }
        });

        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageHelper.changeLocal(getContext().getResources(), "ar");
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageHelper.changeLocal(getContext().getResources(), "en");
            }
        });
        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageHelper.changeLocal(getContext().getResources(), "fr");
            }
        });
        return view;
    }

   /* private void InitViews() {
        //hide floatingActionButton
        fab = view.findViewById(R.id.addButton);
        fab.hide();

        lang = view.findViewById(R.id.parent_text);
        group = view.findViewById(R.id.group);
        arabic = view.findViewById(R.id.lang_arabic);
        english = view.findViewById(R.id.lang_english);
        french = view.findViewById(R.id.lang_french);

        lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                group.setVisibility(View.VISIBLE);
            }
        });
    }*/
}

//    private RecyclerView recyclerView;
//    private ArrayList<LanguageParent> language;
//    private SettingAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.settings);
//
//        //hide floatingActionButton
////        fab = findViewById(R.id.addButton);
////        fab.hide();
//
//        InitViews();
//
//    }
//
//    private void InitViews() {
//        recyclerView = findViewById(R.id.recycle_settings);
//        language = new ArrayList<>();
//
//        setData();
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        adapter = new SettingAdapter(this, language);
//        recyclerView.setAdapter(adapter);
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        adapter.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        adapter.onRestoreInstanceState(savedInstanceState);
//    }
//
//    private void setData() {
//
//        ArrayList<LanguageChild> languages = new ArrayList<>();
//        languages.add(new LanguageChild("Arabic"));
//        languages.add(new LanguageChild("English"));
//
//        language.add(new LanguageParent("Language", languages));
//
//    }