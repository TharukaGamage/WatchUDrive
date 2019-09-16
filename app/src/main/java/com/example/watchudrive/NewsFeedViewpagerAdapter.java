package com.example.watchudrive;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedViewpagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> listFragments = new ArrayList<>();
    private final List<String> listStrings = new ArrayList<>();


    public NewsFeedViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listStrings.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listStrings.get(position);
    }

    public void addFragment(Fragment fragment,String name){
        listFragments.add(fragment);
        listStrings.add(name);
    }
}
