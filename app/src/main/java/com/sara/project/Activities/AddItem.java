package com.sara.project.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.sara.project.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddItem extends AppCompatActivity {

    DatePickerDialog.OnDateSetListener datePicker;
    private Toolbar toolbar;
    private TextView activityTitle;
    private FloatingActionButton fabButton;
    private EditText date;
    private Spinner quantity, category;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        InitViews();
    }

    private void InitViews() {
        //add title of Activity
        toolbar = findViewById(R.id.toolbar);
        activityTitle = findViewById(R.id.toolbar_title);
        activityTitle.setText("Add Item");

        //hide floatingActionButton
        fabButton = findViewById(R.id.addButton);
        fabButton.hide();
        //
        quantity = findViewById(R.id.quantity);
        category = findViewById(R.id.category);
        DataOfSpinner();

        date = findViewById(R.id.date);
        DatePicker();
    }

    private void DataOfSpinner() {

        ArrayAdapter<Integer> quantity_adapter;
        ArrayAdapter<String> category_adapter;
        ArrayList<Integer> list_quantity;
        ArrayList<String> list_category;

        list_quantity = new ArrayList<Integer>();
        list_quantity.add(1);
        list_quantity.add(2);
        list_quantity.add(3);
        list_quantity.add(4);
        list_quantity.add(5);
        list_quantity.add(6);

        quantity_adapter = new ArrayAdapter<Integer>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list_quantity);
        quantity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantity.setAdapter(quantity_adapter);

        list_category = new ArrayList<>();
        list_category.add("Food");
        list_category.add("Clothes");
        list_category.add("Bags");
        list_category.add("Book Stores");
        list_category.add("Super Market");

        category_adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list_category);
        category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(category_adapter);
    }

    private void DatePicker() {
        calendar = Calendar.getInstance();
        datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddItem.this, datePicker, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(calendar.getTime()));
    }

}
