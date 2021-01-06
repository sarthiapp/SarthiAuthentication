package com.example.sarthi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    private int TabsNumber;
    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int TabsNumber) {
        super(fm, behavior);
        this.TabsNumber= TabsNumber;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new login();
            case 1:
                return new signup();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TabsNumber;
    }
}
