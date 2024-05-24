package com.example.bdp139.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bdp139.fragment.FragmentInfo;
import com.example.bdp139.fragment.FragmentList;
import com.example.bdp139.fragment.FragmentSearch;

public class FragmentAdapter extends FragmentPagerAdapter {
    private int pageNumber;
    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNumber = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentList();
            case 1:
                return new FragmentInfo();
            case 2:
                return new FragmentSearch();
        }
        return null;
    }

    @Override
    public int getCount() {
        return pageNumber;
    }
}
