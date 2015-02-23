package com.example.walterqian.myair;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
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

    AQIFragment aqiFragment = new AQIFragment();
    QuestionsFragment questionsFragment = new QuestionsFragment();
    HealthFragment healthFragment = new HealthFragment();

    // AQIFragment aqiFragment = new AQIFragment();
    // QuestionsFragment questionsFragment = new QuestionsFragment();
    ArrayList<String> tabs = new ArrayList<String>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // setting the tabs
        tabs.add("Home");
        tabs.add("Health");
        tabs.add("Toxics");
        tabs.add("Prizes");
        tabs.add("Learn More");

        if (savedInstanceState == null) {


        }


        mCustomPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), this);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);


        final View mainView = findViewById(R.id.main_container);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                View aqiFragmentView = findViewById(R.id.aqi_fragment);
                View questionFragmentView = findViewById(R.id.question_fragment);
                View healthFragmentView = findViewById(R.id.health_fragment);

                // set all to invisible
                if (aqiFragmentView!=null)
                    aqiFragmentView.setVisibility(View.INVISIBLE);
                if (questionFragmentView!=null) {
                    questionFragmentView.setVisibility(View.INVISIBLE);
                    Log.e("You are here","Turning to invisible!");
                }
                if (healthFragmentView!=null)
                    healthFragmentView.setVisibility(View.INVISIBLE);

                if (position == 0) {
                    if (aqiFragmentView!=null && questionFragmentView != null) {
                        if (aqiFragmentView.getVisibility() == View.INVISIBLE)
                            aqiFragmentView.setVisibility(View.VISIBLE);
                        if (questionFragmentView.getVisibility() == View.INVISIBLE)
                            questionFragmentView.setVisibility(View.VISIBLE);
                    }
                    Resources res = getResources();
                    mainView.setBackground(res.getDrawable(R.drawable.sky_image));
                }
                else if (position == 1){
                    if (healthFragmentView!=null) {
                        if (healthFragmentView.getVisibility() == View.INVISIBLE)
                            healthFragmentView.setVisibility(View.VISIBLE);


                    }
                    mainView.setBackgroundColor(Color.WHITE);
                }
                else {


                }

                Log.e("You are here: ",String.valueOf(position));
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


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
            // Bundle args = new Bundle(5);
            //args.putStringArrayList("key",tabs);

            // Set the arguments on the fragment
            // that will be fetched in the
            // DemoFragment@onCreateView
            // fragment.setArguments(args);
            if (position == 1) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.aqi_fragment_container, aqiFragment)
                        .add(R.id.questions_container, questionsFragment)
                        .commit();
                return fragment;
            }
            else if (position == 2) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.health_fragment_container, healthFragment)
                        .commit();
                Log.e("HERE:","POSITION = 2");
                return fragment;
            }
            else
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
