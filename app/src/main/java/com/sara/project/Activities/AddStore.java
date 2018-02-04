package com.sara.project.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sara.project.Database.DBController;
import com.sara.project.R;

public class AddStore extends AppCompatActivity {

    DBController myDb;
    String user_id;
    String storeName, storeLocation;
    private Toolbar toolbar;
    private TextView activityTitle;
    private FloatingActionButton fabButton;
    private EditText name, location;
    private Button add, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_store);

        InitViews();
    }

    private void InitViews() {
        myDb = new DBController(this);
        //add title of Activity
        toolbar = findViewById(R.id.toolbar);
        activityTitle = findViewById(R.id.toolbar_title);
        activityTitle.setText("Add Store");

        //hide floatingActionButton
        fabButton = findViewById(R.id.addButton);
        fabButton.hide();

        //
        name = findViewById(R.id.store_name);
        location = findViewById(R.id.store_location);
        add = findViewById(R.id.addStore);
        cancel = findViewById(R.id.cancelStore);

        //get user id from home activity
        Bundle bundle = getIntent().getExtras();
        user_id = bundle.getString("user_id");

        GetLocation();
        AddStore();
        CancelStore();
    }

    private void GetLocation() {
    }

    private void AddStore() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeName = name.getText().toString();
                storeLocation = location.getText().toString();

                if (TextUtils.isEmpty(storeName)) {
                    name.setError("Field cannot be left blank.");
                    return;
                }
                if (TextUtils.isEmpty(storeLocation)) {
                    location.setError("Field cannot be left blank.");
                    return;
                }
                //insert data into table of store
                // myDb.openDB();
                long insert = myDb.insert_intoStore(storeName, storeLocation, Integer.parseInt(user_id));
                //            myDb.closeDB();
                if (insert == -1) {
                    Toast.makeText(AddStore.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                } else {
                    CustomDialog();
                    name.setText("");
                    location.setText("");
                }
            }
        });

    }

    private void CustomDialog() {
        // final CharSequence[] items = {"Take Photo","Choose from Library","Remove"};

        AlertDialog.Builder builder = new AlertDialog.Builder(AddStore.this);
        builder.setTitle("Store Shop!")
                .setMessage("Store Added Succcessful")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        builder.show();
    }

    private void CancelStore() {

    }

}
