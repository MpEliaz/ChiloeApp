package cl.emprz.chiloeapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pixelcan.inkpageindicator.InkPageIndicator;

import java.util.ArrayList;

import cl.emprz.chiloeapp.Ficha;
import cl.emprz.chiloeapp.R;
import cl.emprz.chiloeapp.adapters.imageSliderFrontAdapter;
import cl.emprz.chiloeapp.adapters.novedadesAdapter;
import cl.emprz.chiloeapp.clases.Pyme;

/**
 * A simple {@link Fragment} subclass.
 */
public class Novedades extends Fragment implements novedadesAdapter.OnItemClickListener{


    ViewPager pager;
    InkPageIndicator indicator;
    imageSliderFrontAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_novedades, container, false);

        int[] images = {R.drawable.iglesia, R.drawable.curanto, R.drawable.huillinco};

/*        adapter = new imageSliderFrontAdapter(getFragmentManager(), images);
        pager = (ViewPager)v.findViewById(R.id.pager_front);
        pager.setAdapter(adapter);
        indicator = (InkPageIndicator)v.findViewById(R.id.indicator_front);
        indicator.setViewPager(pager);*/

        //OBJECTO EN LA LISTA
        ArrayList<Object> objectos = new ArrayList<>();
        objectos.add("viewpager");
        objectos.add("el tiempo");
        objectos.add(new Pyme(1, "Parque Tantauco", "http://www.travelsecurity.cl/portals/0/Images/Tendencias/Tendencias-Chiloe/img2.jpg"));
        objectos.add(new Pyme(2, "Hosteria Quellón", "http://www.interpatagonia.com/paseos/museo-arte-moderno-chiloe/museo-arte-moderno-chiloe-b.jpg"));
        objectos.add(new Pyme(3, "Hotel3", "http://cdn1.buuteeq.com/upload/14096/chiloe-2.JPG"));
        objectos.add(new Pyme(4, "Hotel4", "http://www.lagranepoca.com/sites/default/files/45090_102869089774702_1820359_n.jpg"));
        objectos.add(new Pyme(4, "Hotel5", "http://q-ec.bstatic.com/images/hotel/840x460/446/44669503.jpg"));
        objectos.add(new Pyme(4, "Hotel6", "http://www.interpatagonia.com/paseos/museo-arte-moderno-chiloe/museo-arte-moderno-chiloe-b.jpg"));
        objectos.add(new Pyme(4, "Hotel6", "http://www.interpatagonia.com/paseos/museo-arte-moderno-chiloe/museo-arte-moderno-chiloe-b.jpg"));
        objectos.add(new Pyme(4, "Hotel6", "http://www.interpatagonia.com/paseos/museo-arte-moderno-chiloe/museo-arte-moderno-chiloe-b.jpg"));
        objectos.add(new Pyme(4, "Hotel6", "http://www.interpatagonia.com/paseos/museo-arte-moderno-chiloe/museo-arte-moderno-chiloe-b.jpg"));
        objectos.add(new Pyme(4, "Hotel6", "http://www.interpatagonia.com/paseos/museo-arte-moderno-chiloe/museo-arte-moderno-chiloe-b.jpg"));

        //RECYCLERVIEW
        final novedadesAdapter adapter = new novedadesAdapter(getContext(), getFragmentManager(), objectos, images);
        adapter.setOnItemClickListener(this);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_destacados);
        rv.setHasFixedSize(true);
        GridLayoutManager grid = new GridLayoutManager(getContext(),2);
        grid.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){

            @Override
            public int getSpanSize(int position) {
                switch (adapter.getItemViewType(position)){
                    case novedadesAdapter.TYPE_HEADER:
                        return 2;
                    case novedadesAdapter.TYPE_ITEM:
                        return 1;
                    case novedadesAdapter.TYPE_WEATHER:
                        return 2;
                    default:
                        return -1;
                }
            }
        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(grid);
        return v;
    }

    @Override
    public void onItemClick(View view, Object o, int position) {
        //Toast.makeText(getActivity(),((Pyme)o).getNombre(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), Ficha.class);
        i.putExtra("nombre",((Pyme)o).getNombre());
        i.putExtra("id",((Pyme)o).getId());
        startActivity(i);
    }
}
