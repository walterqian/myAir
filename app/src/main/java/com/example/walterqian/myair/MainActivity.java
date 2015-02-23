package com.example.walterqian.myair;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends FragmentActivity {

    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    LocationFragment mLocationFragment;
    ArrayList<String> tabs = new ArrayList<String>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationFragment locationFragment = new LocationFragment();
        // setting the tabs
        tabs.add("Home");
        tabs.add("Health");
        tabs.add("Toxics");
        tabs.add("Prizes");
        tabs.add("Learn More");

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.aqi_fragment_container,new AQIFragment())
                    .add(R.id.questions_container,new QuestionsFragment())
                    .commit();
        }


        mCustomPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), this);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
    }

    class CustomPagerAdapter extends FragmentPagerAdapter {

        Context mContext;

        public CustomPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {

            // Create fragment object
            Fragment fragment = new DemoFragment();




            // Attach some data to the fragment
            // that we'll use to populate our fragment layouts
            Bundle args = new Bundle(5);
            args.putStringArrayList("key",tabs);

            // Set the arguments on the fragment
            // that will be fetched in the
            // DemoFragment@onCreateView
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }


    }

    public static class DemoFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout resource that'll be returned
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // Get the arguments that was supplied when
            // the fragment was instantiated in the
            // CustomPagerAdapter
            Bundle args = getArguments();
           // ((TextView) rootView.findViewById(R.id.textView)).setText("" + args.getStringArrayList("key"));

            return rootView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // UPDATE: FIX GESTURE
       // getMenuInflater().inflate(R.menu.gesture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
