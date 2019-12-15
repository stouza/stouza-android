package ru.stouza.android;

import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import ru.stouza.android.fragments.OneFragment;
import ru.stouza.android.fragments.FourFragment;
import ru.stouza.android.fragments.SixFragment;
import ru.stouza.android.fragments.ThreeFragment;
import ru.stouza.android.fragments.TwoFragment;
import ru.stouza.android.fragments.FiveFragment;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity {

    protected DrawerLayout  mDrawer;
    Toolbar                 toolbar;
    ActionBarDrawerToggle   drawerToggle;
    Fragment                oneFragment     = new OneFragment();
    Fragment                twoFragment     = new TwoFragment();
    Fragment                threeFragment   = new ThreeFragment();
    Fragment                fourFragment    = new FourFragment();
    Fragment                fiveFragment    = new FiveFragment();
    Fragment                sixFragment     = new SixFragment();
    FloatingActionMenu      floatingActionMenu;
    FloatingActionButton    floatingActionOne;
    FloatingActionButton    floatingActionTwo;
    FloatingActionButton    floatingActionThree;
    FloatingActionButton    floatingActionFour;
    FloatingActionButton    floatingActionFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment(oneFragment);

        // Main Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);

        // Floating Action Menu
        floatingActionMenu  = (FloatingActionMenu)   findViewById (R.id.floating_action_menu);
        floatingActionOne   = (FloatingActionButton) findViewById (R.id.floating_action_one);
        floatingActionTwo   = (FloatingActionButton) findViewById (R.id.floating_action_two);
        floatingActionThree = (FloatingActionButton) findViewById (R.id.floating_action_three);
        floatingActionFour  = (FloatingActionButton) findViewById (R.id.floating_action_four);
        floatingActionFive  = (FloatingActionButton) findViewById (R.id.floating_action_five);

        floatingActionOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(oneFragment);
                floatingActionMenu.close(true);
            }
        });
        floatingActionTwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(twoFragment);
                floatingActionMenu.close(true);
            }
        });
        floatingActionThree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(threeFragment);
                floatingActionMenu.close(true);
            }
        });
        floatingActionFour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(fourFragment);
                floatingActionMenu.close(true);
            }
        });
        floatingActionFive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(fiveFragment);
                floatingActionMenu.close(true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_toolbar_menu:
                replaceFragment(sixFragment);
                return true;

            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // 'onPostCreate' called when activity start-up is complete after 'onStart()'
    // NOTE! Make sure to override the method with only a single 'Bundle' argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {

        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;

        switch(menuItem.getItemId()) {
            case R.id.navigation_item_one:
                fragmentClass = OneFragment.class;
                break;
            case R.id.navigation_item_two:
                fragmentClass = TwoFragment.class;
                break;
            case R.id.navigation_item_three:
                fragmentClass = ThreeFragment.class;
                break;
            case R.id.navigation_item_four:
                fragmentClass = FourFragment.class;
                break;
            case R.id.navigation_item_five:
                fragmentClass = FiveFragment.class;
                break;
            case R.id.navigation_item_six:
                fragmentClass = SixFragment.class;
                break;
            default:
                fragmentClass = OneFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction  transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.flContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction  transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}