package com.example.ljt.temperature;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;

import com.example.ljt.temperature.Fragment.SettingFragment;
import com.example.ljt.temperature.Misc.DealFragmentInID;

public class NavigationDeal {
    private BottomNavigationView navigationView;
    private FragmentManager manager;
    private Fragment[] fragments;
    private DealFragmentInID dealFragmentInID;
    private final static int HOME = R.id.navigation_home;
    private final static int SETTING = R.id.navigation_setting;

    public FragmentManager getManager() {
        return manager;
    }

    public Fragment[] getFragments() {
        return fragments;
    }

    public NavigationDeal(BottomNavigationView navigationView, FragmentManager manager, @IdRes int id, Fragment... fragments) {
        this.navigationView = navigationView;

        this.manager = manager;
        this.fragments = fragments;
        this.dealFragmentInID = new DealFragmentInID(id, manager);
        for (Fragment fragment : fragments) {
            this.dealFragmentInID.addWithFragment(fragment, fragment.getClass().getName());
        }
    }

    public void setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener) {
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void showFragment(Fragment fragment) {
        if (isInFragments(fragment)) {
            if (!fragment.isVisible()) {
                dealFragmentInID.showFragment(fragment);
            }
        }
    }

    public boolean isInFragments(Fragment fragment) {
        for (Fragment temp : fragments) {
            if (fragment.equals(temp)) {
                return true;
            }
        }
        return false;
    }

    public void hideFragment(Fragment fragment) {
        if (isInFragments(fragment)) {
            if (fragment.isVisible()) {
                dealFragmentInID.hideFragment(fragment);
            }
        }
    }

    public void hideAllFragment() {
        for (Fragment fragment : fragments) {
            dealFragmentInID.hideFragment(fragment);
        }
    }

    public Fragment getFragmentByClass(Class aClass) {
        for (Fragment fragment : fragments) {
            if(aClass.getName()==fragment.getTag()){
                return fragment;
            }
        }
        return null;
        //return manager.findFragmentByTag(aClass.getName());
    }


}
