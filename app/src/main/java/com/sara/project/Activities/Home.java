package com.sara.project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.sara.project.Fragments.About;
import com.sara.project.Fragments.Settings;
import com.sara.project.R;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String user_id;
    private Toolbar toolbar;
    private TextView activityTitle;
    private FloatingActionButton add;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        InitViews();
        getId();
    }

    private void getId() {
        Bundle bundle = getIntent().getExtras();
        user_id = bundle.getString("idOfUser");
        Toast.makeText(Home.this, "id is : " + user_id, Toast.LENGTH_LONG).show();
    }

    private void InitViews() {
        //init views
        toolbar = findViewById(R.id.toolbar);
        activityTitle = findViewById(R.id.toolbar_title);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        add = findViewById(R.id.addButton);

        //add title of Activity
        activityTitle.setText("Shopping list");

        //add custom toogle
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        //set icon to toggle drawer
        toolbar.setNavigationIcon(R.drawable.drawertoggle);

        AddAction();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            setFragment(new About());
        } else if (id == R.id.nav_settings) {
            setFragment(new Settings());
        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Home.this, Login.class));
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        setTitle(item.getTitle());

        return true;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction frag = getSupportFragmentManager().beginTransaction();
        frag.replace(R.id.main, fragment);
        frag.commit();
    }

    private void AddAction() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(Home.this, AddStore.class);

                Bundle bundle = new Bundle();
                bundle.putString("user_id", user_id);
                addIntent.putExtras(bundle);
                startActivity(addIntent);
                //  finish();
            }
        });
    }

}
