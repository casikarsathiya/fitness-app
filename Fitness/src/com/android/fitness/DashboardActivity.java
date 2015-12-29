package com.android.fitness;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity{

     @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_dashboard);

	        // Get the ViewPager and set it's PagerAdapter so that it can display items
	        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
	        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(), 
	        		DashboardActivity.this));

	        // Give the TabLayout the ViewPager
	        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
	        tabLayout.setupWithViewPager(viewPager);
	    }
   
}