package cl.emprz.chiloeapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import cl.emprz.chiloeapp.clases.Destacado;
import cl.emprz.chiloeapp.fragments.ImageFrontSlider;

/**
 * Created by elias on 07-04-16.
 */
public class imageSliderFrontAdapter extends FragmentStatePagerAdapter {

    private List<Destacado> destacados;

    public imageSliderFrontAdapter(FragmentManager fm, List<Destacado> destacados) {
        super(fm);
        this.destacados = destacados;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFrontSlider.newInstance(destacados.get(position).getImagen(), destacados.get(position).getTitulo(), destacados.get(position).getSub_titulo());
    }

    @Override
    public int getCount() {
        return destacados.size();
    }
}
