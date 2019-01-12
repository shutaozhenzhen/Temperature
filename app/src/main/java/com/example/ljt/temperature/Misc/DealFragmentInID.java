package com.example.ljt.temperature.Misc;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ljt.temperature.R;

public class DealFragmentInID {
    private  @IdRes int id;
    private FragmentManager fragmentManager;

    public DealFragmentInID(@IdRes int id, FragmentManager fragmentManager) {
        this.id = id;
        this.fragmentManager = fragmentManager;
    }

    public void  replaceWithFragment  (Fragment fragment)    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }
    public void  addWithFragment  (Fragment fragment)    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(id, fragment);
        fragmentTransaction.commit();
    }
    public void  replaceWithFragment  (@NonNull Fragment fragment, @Nullable String tag)    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment,tag);
        fragmentTransaction.commit();
    }
    public void  addWithFragment  (@NonNull Fragment fragment,  String tag)    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(id, fragment,tag);
        fragmentTransaction.commit();
    }
    public void  showFragment  (@NonNull Fragment fragment)    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }
    public void  hideFragment  (@NonNull Fragment fragment)    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragment);
        fragmentTransaction.commit();
    }
}
