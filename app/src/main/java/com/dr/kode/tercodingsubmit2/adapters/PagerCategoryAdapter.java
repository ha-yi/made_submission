package com.dr.kode.tercodingsubmit2.adapters;

import com.dr.kode.tercodingsubmit2.fragments.AnimeListFragment;
import com.dr.kode.tercodingsubmit2.utils.IMDBCall;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerCategoryAdapter extends FragmentPagerAdapter {
    private List<String> data;
    private IMDBCall caller;

    public PagerCategoryAdapter(FragmentManager fm, List<String> items, IMDBCall calller) {
        super(fm);
        data = items;
        this.caller = calller;
    }

    @Override
    public Fragment getItem(int position) {
        return AnimeListFragment.createFragment(data.get(position), caller);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).toUpperCase();
    }
}
