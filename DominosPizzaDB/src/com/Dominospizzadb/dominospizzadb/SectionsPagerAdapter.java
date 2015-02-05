package com.Dominospizzadb.dominospizzadb;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments;

    public SectionsPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> listaFragments) {
        super(fragmentManager);
        // TODO Auto-generated constructor stub
        this.fragments = listaFragments;
    }

    @Override
    public Fragment getItem(int position) {
        // TODO Auto-generated method stub
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return fragments.size();
    }
}

