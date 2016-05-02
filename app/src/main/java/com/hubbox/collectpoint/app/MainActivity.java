package com.hubbox.collectpoint.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hubbox.collectpoint.app.fragments.AddGiveOutParcelFragment;
import com.hubbox.collectpoint.app.fragments.CustomerFaqFragment;
import com.hubbox.collectpoint.app.fragments.EnterHubBoxCodeFragment;
import com.hubbox.collectpoint.app.fragments.GiveOutParcelFragment;
import com.hubbox.collectpoint.app.fragments.JustNameEnterFragment;
import com.hubbox.collectpoint.app.fragments.MyParcelsFragment;
import com.hubbox.collectpoint.app.util.AppConsts;
import com.hubbox.collectpoint.app.util.FragmentUtils;

import com.hubbox.collectpoint.app.fragments.AddParcelFragment;

public class MainActivity extends SecureActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AddGiveOutParcelFragment.OnAddGiveOutParcelInteractionListener,
        AddParcelFragment.OnAddParcelFragmentInteractionListener,
EnterHubBoxCodeFragment.OnEnterHubboxFragmentInteractionListener,
        JustNameEnterFragment.OnJustNameEnterFragmentInteractionListener,
        MyParcelsFragment.OnMyParcelsFragmentInteractionListener {

    private void startTourIfNeeded() {
        boolean notFirstRun = false;


        SharedPreferences settings = getSharedPreferences(AppConsts.Preferences.filenameConst, 0);
        // FIXME AppConsts.SHOW_TOUR_EVERY_TIME for debugging
        notFirstRun = settings.getBoolean(AppConsts.Preferences.notFirstRun, false);
        if (!notFirstRun) {
            settings = getSharedPreferences(AppConsts.Preferences.filenameConst, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(AppConsts.Preferences.notFirstRun, true);
            editor.commit();
            Intent tourIntent = new Intent(this, TextAndButtonActivity.class);
            tourIntent.putExtra(TextAndButtonActivity.STEP_NO, 1);
            startActivity(tourIntent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startTourIfNeeded();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentUtils.addFragment(this, new AddGiveOutParcelFragment());
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
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout_drawer_item) {
            ((HubBoxApplication)getApplication()).logout();
            finish();
            startActivity(getIntent());
        } else if(id == R.id.nav_customer_faqs_drawer_item){
            FragmentUtils.addFragment(this, new CustomerFaqFragment());
        }else if(id == R.id.nav_add_parcel_drawer_item){
            FragmentUtils.addFragment(this, new AddGiveOutParcelFragment());
        }else if(id == R.id.nav_manage_parcels_drawer_item){
            FragmentUtils.addFragment(this, new MyParcelsFragment());
        }
// else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onAddGiveOutFragmentInteraction(AddGiveOutParcelFragment.Result result) {
        switch (result){
            case ADD_PARCEL:
                FragmentUtils.addFragment(this, new AddParcelFragment());
                break;
            case GIVE_OUT:
                FragmentUtils.addFragment(this, new GiveOutParcelFragment());
                break;
        }
    }

    @Override
    public void onAddParcelFragmentInteraction(AddParcelFragment.Result result) {
        switch (result){
            case HUBBOX_CODE:
                FragmentUtils.addFragment(this, new EnterHubBoxCodeFragment());
                break;
            case JUST_NAME:
                FragmentUtils.addFragment(this, new JustNameEnterFragment());
                break;
        }
    }

    @Override
    public void onEnterHubboxFragmentInteraction(String hubboxNo) {

    }

    @Override
    public void onJustNameEnterFragmentInteraction(Uri uri) {

    }

    @Override
    public void onMyParcelsFragmentInteraction() {

    }
}
