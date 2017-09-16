package com.xstar97.easyutils.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.xstar97.easyutils.fragments.EasyParseModFragment;
import com.xstar97.easyutils.fragments.EasyPermissionModFragment;
import com.xstar97.easyutils.fragments.EasyPrefsModFragment;
import com.xstar97.easyutils.fragments.EasyShellModFragment;
import com.xstar97.easyutils.fragments.EasySocialModFragment;
import com.xstar97.easyutils.interfaces.EasyAdsModAsyncTaskListener;
import com.xstar97.easyutils.fragments.EasyActivityModFragment;
import com.xstar97.easyutils.fragments.EasyAdsModFragment;
import com.xstar97.easyutils.fragments.EasyAppModFragment;
import com.xstar97.easyutils.fragments.EasyAudioModFragment;
import com.xstar97.easyutils.fragments.EasyDeviceModFragment;
import com.xstar97.easyutils.fragments.EasyFileModFragment;
import com.xstar97.easyutils.fragments.EasyLocaleModFragment;
import com.xstar97.easyutils.fragments.EasyLogModFragment;
import com.xstar97.easyutils.fragments.EasyNetworkModFragment;
import com.xstar97.easyutils.fragments.MainActivityFragment;

import com.xstar97.easyutils.interfaces.EasySocialModListener;

import com.xstar97.easyutils.mods.EasySocialMod;
import com.xstar97.easyutils.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseDrawerActivity implements EasyAdsModAsyncTaskListener
{
    private String TAG = "MainActivity";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @BindView(R.id.fab) FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initOnCreate(toolbar, drawer, navigationView);
        
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainFragment();
            }
        });

        mainFragment();
    }
    private void mainFragment(){
        unCheckedMenu();
        newFragment(new MainActivityFragment());
    }
    private void newFragment(Fragment fragment){
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content, fragment)
                    .commit();
        }catch (Exception e){
            error(e.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            closerDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // Create new fragments here!
        switch (id) {
            case R.id.nav_easy_mod_activity:
                newFragment(new EasyActivityModFragment());
                break;
            case R.id.nav_easy_mod_ads:
                newFragment(new EasyAdsModFragment());
                break;
            case R.id.nav_nav_easy_mod_app:
                newFragment(new EasyAppModFragment());
                break;
            case R.id.nav_nav_easy_mod_audio:
                newFragment(new EasyAudioModFragment());
                break;
            case R.id.nav_nav_easy_mod_device:
                newFragment(new EasyDeviceModFragment());
                break;
            case R.id.nav_nav_easy_mod_file:
                newFragment(new EasyFileModFragment());
                break;
            case R.id.nav_nav_easy_mod_locale:
                newFragment(new EasyLocaleModFragment());
                break;
            case R.id.nav_nav_easy_mod_log:
                newFragment(new EasyLogModFragment());
                break;
            case R.id.nav_nav_easy_mod_network:
                newFragment(new EasyNetworkModFragment());
                break;
            case R.id.nav_nav_easy_mod_parse:
                newFragment(new EasyParseModFragment());
                break;
            case R.id.nav_nav_easy_mod_permission:
                newFragment(new EasyPermissionModFragment());
            case R.id.nav_nav_easy_mod_prefs:
                newFragment(new EasyPrefsModFragment());
                break;
            case R.id.nav_nav_easy_mod_shell:
                newFragment(new EasyShellModFragment());
                break;
            case R.id.nav_nav_easy_mod_social:
                newFragment(new EasySocialModFragment());
                break;
            default:
                mainFragment();
                break;
        }
        closerDrawer();
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void areAdsBlocked(boolean result) {
        toast("ads blocked = " + result);
    }
}