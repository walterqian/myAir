package com.example.walterqian.myair;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This demonstrates how you can implement switching between the tabs of a
 * TabHost through fragments, using FragmentTabHost.
 */
public class TabFragment extends Fragment {
    private FragmentTabHost mTabHost;


    final String TAG1 = "Home";
    final String TAG2 = "Health";
    final String TAG3 = "Toxics";
    final String TAG4 = "Prizes";
    final String TAG5 = "Learn More";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager());

        mTabHost.addTab(mTabHost.newTabSpec(TAG1).setIndicator(TAG1),
                AQIFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAG2).setIndicator(TAG2),
                HealthFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAG3).setIndicator(TAG3),
                AQIFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAG4).setIndicator(TAG4),
                HealthFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAG5).setIndicator(TAG5),
                AQIFragment.class, null);

        Log.e("Was HERE:", "TabFragment Created");
        setTabSize();
        return mTabHost;

    }

    private void setTabSize(){
        for (int k=0; k<5; k++) {
            TextView x = (TextView) mTabHost.getTabWidget().getChildAt(k).findViewById(android.R.id.title);
            x.setTextSize(8);
        }
    }
}