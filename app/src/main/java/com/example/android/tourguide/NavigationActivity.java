package com.example.android.tourguide;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
        SummaryFragment.OnFragmentInteractionListener,
        DetailFragment.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.getFragments() == null) {
            navigationView.getMenu().getItem(0).setChecked(true);
            fragmentManager.beginTransaction().replace(R.id.container, SummaryFragment.newInstance(SummaryFragment.DESMOINES),SummaryFragment.TAG).commit();
        }
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

        return super.onOptionsItemSelected(item);
    }

    public void openDetails(Model model){

        // start details fragment with incoming model
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container,DetailFragment.newInstance(model)).addToBackStack(null).commit();

    }


    @Override
    public void onPause(){
        super.onPause();
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (id == R.id.nav_desmoines) {
            fragmentManager.beginTransaction().replace(R.id.container, SummaryFragment.newInstance(SummaryFragment.DESMOINES),SummaryFragment.TAG).commit();
        } else if (id == R.id.nav_ames) {
            fragmentManager.beginTransaction().replace(R.id.container, SummaryFragment.newInstance(SummaryFragment.AMES),SummaryFragment.TAG).commit();
        } else if (id == R.id.nav_iowacity) {
            fragmentManager.beginTransaction().replace(R.id.container, SummaryFragment.newInstance(SummaryFragment.IOWACITY),SummaryFragment.TAG).commit();
        } else if (id == R.id.nav_dubuque) {
            fragmentManager.beginTransaction().replace(R.id.container, SummaryFragment.newInstance(SummaryFragment.DUBUQUE),SummaryFragment.TAG).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
