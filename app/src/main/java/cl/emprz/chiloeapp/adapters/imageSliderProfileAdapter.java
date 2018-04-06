package cl.emprz.chiloeapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import cl.emprz.chiloeapp.clases.Imagen;
import cl.emprz.chiloeapp.fragments.ImageFrontSlider;
import cl.emprz.chiloeapp.fragments.ImageProfileSlider;

/**
 * Created by elias on 07-04-16.
 */
public class imageSliderProfileAdapter extends FragmentStatePagerAdapter {
    ArrayList<Imagen> imagenes;

    public imageSliderProfileAdapter(FragmentManager fm, ArrayList<Imagen> imagenes) {
        super(fm);
        this.imagenes = imagenes;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageProfileSlider.newInstance(imagenes.get(position).getUrl(), "Titulo");
    }

    @Override
    public int getCount() {
        return imagenes.size();
    }
}
