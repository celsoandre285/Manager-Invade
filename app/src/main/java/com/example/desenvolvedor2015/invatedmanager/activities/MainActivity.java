package com.example.desenvolvedor2015.invatedmanager.activities;

import android.content.Intent;
import android.os.Bundle;


import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.desenvolvedor2015.invatedmanager.R;
import com.example.desenvolvedor2015.invatedmanager.fragments.AbsentFragment;
import com.example.desenvolvedor2015.invatedmanager.fragments.AllInvatedFragment;
import com.example.desenvolvedor2015.invatedmanager.fragments.PresentFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.fabAddGuests = (FloatingActionButton) this.findViewById(R.id.fab_add_guests);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.setListeners();

        this.startDefaultFragment();
    }

    private void setListeners() {
        this.mViewHolder.fabAddGuests.setOnClickListener(this);
    }

    private void startDefaultFragment() {

        Fragment fragment = null;
        Class fragmentClass = AllInvatedFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            Log.e("ErroFragment", "IllegalAccessException: "+ e.getMessage() );

            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.e("ErroFragment", "IllegalAccessException: "+ e.getMessage() );

            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_content, fragment).commit();
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
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        Class fragmentClass = null;

        int id = item.getItemId();

        if (id == R.id.nav_all_guests) {

            fragmentClass = AllInvatedFragment.class;

        } else if (id == R.id.nav_present) {

            fragmentClass = PresentFragment.class;

        } else if (id == R.id.nav_absent) {

            fragmentClass = AbsentFragment.class;

        }

        try {

            fragment = (Fragment) fragmentClass.newInstance();


        } catch (InstantiationException e) {
            Log.e("ErroFragment", "InstantiationException: "+ e.getMessage() );
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.e("ErroFragment", "IllegalAccessException: "+ e.getMessage() );
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_content, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.fab_add_guests){
            startActivity(new Intent(MainActivity.this, GuestFormActivity.class));
        }

    }

    private static class ViewHolder{
        public FloatingActionButton fabAddGuests;
    }
}
