package com.xstar97.easyutils.activities;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.xstar97.easyutils.mods.EasyLogMod;
import com.xstar97.easyutils.sample.R;

@SuppressLint("Registered")
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class BaseDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private String TAG = "BaseDrawerActivity";

    private Toolbar toolBar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    public void initOnCreate(Toolbar toolbar, DrawerLayout drawer, NavigationView navigationView){
        try {
            toolBar = toolbar;
            drawerLayout = drawer;
            navView = navigationView;

            setSupportActionBar(toolbar);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            navigationView.setNavigationItemSelectedListener(this);
        }catch (Exception e){
            error(e.getMessage());
        }
    }
    public void initOnCreate(int toolbarID, int drawerID, int navigationViewID){
        try{

            Toolbar toolbar = (Toolbar) findViewById(toolbarID);
            DrawerLayout drawer = (DrawerLayout) findViewById(drawerID);
            NavigationView navigationView = (NavigationView) findViewById(navigationViewID);

            toolBar = toolbar;
            drawerLayout = drawer;
            navView = navigationView;

            setSupportActionBar(toolbar);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            navigationView.setNavigationItemSelectedListener(this);
        }catch (Exception e){
            error(e.getMessage());
        }
    }

    public void unCheckedMenu(){
        int size = getNavView().getMenu().size();
        for (int i = 0; i < size; i++) {
            getNavView().getMenu().getItem(i).setChecked(false);
        }
    }

    public void closerDrawer(){
        try{
            getDrawerLayout().closeDrawer(GravityCompat.START);
        }catch (Exception e){
            error(e.getMessage());
        }
    }

    public void openDrawer(){
        try {
            getDrawerLayout().openDrawer(GravityCompat.START);
        }catch (Exception e){
            error(e.getMessage());
        }
    }

    public boolean isDrawerOpen(){
        try{
            return getDrawerLayout().isDrawerOpen(GravityCompat.START);
        }catch (Exception e){
            error(e.getMessage());
            return false;
        }
    }

    public Toolbar getToolBar(){
        try{
            return toolBar;
        }catch (Exception e){
            error(e.getMessage());
            return null;
        }
    }
    public DrawerLayout getDrawerLayout(){
        try{
            return drawerLayout;
        }catch (Exception e){
            error(e.getMessage());
            return null;
        }
    }
    public NavigationView getNavView(){
        try{
            return navView;
        }catch (Exception e){
            error(e.getMessage());
            return null;
        }
    }

    public void toast(String t){
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show();
    }

    public void error(String e){
        new EasyLogMod.logBuilder()
            .setTag(TAG)
            .setLog(e)
            .error();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}