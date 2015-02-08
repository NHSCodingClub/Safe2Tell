package com.nhscoding.safe2tell;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


import java.lang.reflect.Array;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, NEWS.OnFragmentInteractionListener {

//    String[] drawerArray = getResources().getStringArray(R.array.drawerFields);

    int place;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment objFragment = null;

        switch (position) {
            case 0:
                objFragment = new ABOUT_US();
                mTitle = "About Us";
                place = 0;
                break;

            case 1:
                place = 1;
                mTitle = "Stories";
                objFragment = new STORIES();
                break;

            case 2:
                place = 2;
                mTitle = "Learn";
                objFragment = new LEARN();
                break;

            case 3:
                place = 3;
                mTitle = "Quiz";
                objFragment = new QUIZ();
                break;

            case 4:
                place = 4;
                mTitle = "Problem";
                objFragment = new PROBLEM();
                break;

            case 5:
                place = 5;
                mTitle = "Submit A Tip";
                Intent intent = new Intent(this, SUBMIT_TIP.class);
                startActivity(intent);
                return;

            default:
                place = -1;
                mTitle = "ERROR";
                objFragment = new PlaceholderFragment();
                break;

        }
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, objFragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        String title = getResources().getStringArray(R.array.drawerFields)[number - 1];
        mTitle = title;
    }

    public void restoreActionBar() {
        if (place != 4) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(mTitle);
        } else {
            SpinnerAdapter adapter =
                    ArrayAdapter.createFromResource(getApplicationContext(), R.array.problems,
                            android.R.layout.simple_spinner_dropdown_item);

            ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {

                String[] items = getResources().getStringArray(R.array.problems);

                @Override
                public boolean onNavigationItemSelected(int i, long l) {
                    Log.i("Navigation Item Selected", items[i]);
                    return true;
                }
            };

            ActionBar actionBar = getSupportActionBar();
            int a = actionBar.NAVIGATION_MODE_LIST;
            actionBar.setNavigationMode(a);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setListNavigationCallbacks(adapter, callback);
            mTitle = "Problem";
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            //((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
