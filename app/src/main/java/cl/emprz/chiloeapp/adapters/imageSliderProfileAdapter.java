package cl.emprz.chiloeapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cl.emprz.chiloeapp.fragments.ImageFrontSlider;
import cl.emprz.chiloeapp.fragments.ImageProfileSlider;

/**
 * Created by elias on 07-04-16.
 */
public class imageSliderProfileAdapter extends FragmentStatePagerAdapter {

    int[] imagenes;

    public imageSliderProfileAdapter(FragmentManager fm, int[] imagenes) {
        super(fm);
        this.imagenes = imagenes;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageProfileSlider.newInstance(imagenes[position], "Titulo");
    }

    @Override
    public int getCount() {
        return imagenes.length;
    }
}
