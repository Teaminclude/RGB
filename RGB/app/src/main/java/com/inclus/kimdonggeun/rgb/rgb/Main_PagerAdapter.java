package com.inclus.kimdonggeun.rgb.rgb;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuri on 2018. 5. 22..
 */

public class Main_PagerAdapter extends FragmentPagerAdapter {
    List<Fragment> pageList = new ArrayList<>();
    public Main_PagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public void addFragment(Fragment fragment){
        pageList.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return pageList.get(position);
    }

    @Override
    public int getCount() {
        return pageList.size();
    }
}
