package com.fm2apps.warrantyapp.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.fm2apps.warrantyapp.Fragments.ReqFragment1;
import com.fm2apps.warrantyapp.Fragments.ReqFragment2;
import com.fm2apps.warrantyapp.Fragments.ReqFragment3;

/**
 * Created by Administrator on 04/07/2016.
 */
public class RequestViewPagerAdapter extends FragmentPagerAdapter {
    private Context _context;
    public static int totalPage = 3;
    public Fragment[] fragments = new Fragment[totalPage];

    public RequestViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        _context = context;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = new Fragment();
        switch (position) {
            case 0:
                f = new ReqFragment1();
                break;
            case 1:
                f = new ReqFragment2();
                break;
            case 2:
                f = new ReqFragment3();
                break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return totalPage;
    }

    //This populates your Fragment reference array:
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        fragments[position]  = createdFragment;
        return createdFragment;
    }
}
