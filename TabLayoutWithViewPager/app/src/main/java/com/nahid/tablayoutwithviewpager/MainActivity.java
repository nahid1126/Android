package com.nahid.tablayoutwithviewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.nahid.tablayoutwithviewpager.fragment.BlankFragment;
import com.nahid.tablayoutwithviewpager.fragment.FragmentOne;
import com.nahid.tablayoutwithviewpager.fragment.FragmentTwo;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private BlankFragment blankFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        prepareFragment();
        loadAdapter();
    }

    private void loadAdapter() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(fragmentOne, "Fragment One");
        viewPagerAdapter.addFragment(fragmentTwo, "Fragment Two");
        viewPagerAdapter.addFragment(blankFragment,"Fragment Three");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void prepareFragment() {
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        blankFragment = new BlankFragment();
    }

}