package com.example.sarthi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class login_signup extends AppCompatActivity {
    TabLayout tabs;
    TabItem loginTabItem,signupTabItem;
    ViewPager pager;
    PagerAdapter adapter;
    public String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        tabs = findViewById(R.id.tabs);
        loginTabItem = findViewById(R.id.LoginTab);
        signupTabItem = findViewById(R.id.SignUpTab);
        pager = findViewById(R.id.pager);
        Intent intent=getIntent();
        type=intent.getStringExtra("type");

        adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,tabs.getTabCount(), type);
        pager.setAdapter(adapter);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

    }

}
