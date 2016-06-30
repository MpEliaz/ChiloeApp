package cl.emprz.chiloeapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class principalAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments;

    public principalAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "CATEGORIAS";
            case 1:
                return "NOVEDADES";
            case 2:
                return "EVENTOS";
        }
        return null;
    }
}
