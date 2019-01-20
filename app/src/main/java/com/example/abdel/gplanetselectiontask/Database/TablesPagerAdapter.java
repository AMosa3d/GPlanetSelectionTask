package com.example.abdel.gplanetselectiontask.Database;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class TablesPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;
    private final String[] pageTitles;

    public TablesPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] pageTitles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.pageTitles = pageTitles;
    }

    @Override
    public int getCount() {
        if (fragmentList == null)
            return 0;
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}
