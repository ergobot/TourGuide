package com.example.android.tourguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.tourguide.model.Model;

public class NavigationActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        DesMoinesFragment.OnFragmentInteractionListener,
        DetailFragment.OnFragmentInteractionListener{

    private Model selectedModel;
    public Model getSelectedModel(){return selectedModel;}
    public void setSelectedModel(Model model){this.selectedModel = model;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, DesMoinesFragment.newInstance()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openDetails(Model model){

        setSelectedModel(model);
        // start details fragment with incoming model
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container,DetailFragment.newInstance()).addToBackStack(null).commit();

    }


    @Override
    public void onPause(){
        super.onPause();
        if(getSelectedModel()!= null) {
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            if(getSelectedModel().getId() != null) {
                editor.putString("id", getSelectedModel().getId());
            }
            if(getSelectedModel().getName() != null) {
                editor.putString("name", getSelectedModel().getName());
            }
            if(getSelectedModel().getAddress() != null) {
                editor.putString("address", getSelectedModel().getAddress());
            }
            if (getSelectedModel().getPhone() != null) {
                editor.putString("phone", getSelectedModel().getPhone());
            }
            if (getSelectedModel().getWebsite() != null) {
                editor.putString("website", getSelectedModel().getWebsite());
            }
            if (getSelectedModel().getDetails() != null) {
                editor.putString("details", getSelectedModel().getDetails());
            }
            if (getSelectedModel().getImageResourceId() != 0) {
                editor.putInt("imageResourceid", getSelectedModel().getImageResourceId());
            }
            editor.commit();
        }


    }
    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String id = sharedPref.getString("id",null);
        if(id != null){
            Model restore = new Model();
            restore.setId(id);
            restore.setName(sharedPref.getString("name",""));
            restore.setAddress(sharedPref.getString("address",""));
            restore.setPhone(sharedPref.getString("phone",""));
            restore.setWebsite(sharedPref.getString("website",""));
            restore.setDetails(sharedPref.getString("details",""));
            restore.setImageResourceId(sharedPref.getInt("imageResourceId",0));
            setSelectedModel(restore);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
