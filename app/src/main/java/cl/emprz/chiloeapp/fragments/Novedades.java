package cl.emprz.chiloeapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import java.util.ArrayList;
import java.util.List;

import cl.emprz.chiloeapp.Ficha;
import cl.emprz.chiloeapp.Holders.PymeListaHolder;
import cl.emprz.chiloeapp.ListaPymes;
import cl.emprz.chiloeapp.R;
import cl.emprz.chiloeapp.adapters.imageSliderFrontAdapter;
import cl.emprz.chiloeapp.adapters.novedadesAdapter;
import cl.emprz.chiloeapp.clases.Destacado;
import cl.emprz.chiloeapp.clases.Pyme;

/**
 * A simple {@link Fragment} subclass.
 */
public class Novedades extends Fragment implements novedadesAdapter.OnItemClickListener{


    private ViewPager pager;
    private InkPageIndicator indicator;
    private imageSliderFrontAdapter adapter;
    private List<Destacado> destacados;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        obtenerDestacadosFirebase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_novedades, container, false);


/*        adapter = new imageSliderFrontAdapter(getFragmentManager(), images);
        pager = (ViewPager)v.findViewById(R.id.pager_front);
        pager.setAdapter(adapter);
        indicator = (InkPageIndicator)v.findViewById(R.id.indicator_front);
        indicator.setViewPager(pager);*/

        //OBJECTO EN LA LISTA
        List<Object> objectos = new ArrayList<>();
        objectos.add("viewpager");
        //objectos.add("el tiempo");
        objectos.add(new Pyme(1, "Parque Tantauco", "http://www.travelsecurity.cl/portals/0/Images/Tendencias/Tendencias-Chiloe/img2.jpg"));
        objectos.add(new Pyme(2, "Hosteria Quellón", "http://www.interpatagonia.com/paseos/museo-arte-moderno-chiloe/museo-arte-moderno-chiloe-b.jpg"));
        objectos.add(new Pyme(3, "Nombrecito", "http://cdn1.buuteeq.com/upload/14096/chiloe-2.JPG"));
        objectos.add(new Pyme(4, "Hotel4", "http://www.lagranepoca.com/sites/default/files/45090_102869089774702_1820359_n.jpg"));
        objectos.add(new Pyme(4, "Hotel5", "http://q-ec.bstatic.com/images/hotel/840x460/446/44669503.jpg"));
        objectos.add(new Pyme(4, "Hotel6", "http://www.interpatagonia.com/paseos/museo-arte-moderno-chiloe/museo-arte-moderno-chiloe-b.jpg"));
        objectos.add(new Pyme(4, "Hotel6", "http://www.interpatagonia.com/paseos/museo-arte-moderno-chiloe/museo-arte-moderno-chiloe-b.jpg"));
        objectos.add(new Pyme(4, "Hotel6", "http://www.interpatagonia.com/paseos/museo-arte-moderno-chiloe/museo-arte-moderno-chiloe-b.jpg"));
        objectos.add(new Pyme(4, "Hotel6", "http://www.interpatagonia.com/paseos/museo-arte-moderno-chiloe/museo-arte-moderno-chiloe-b.jpg"));
        objectos.add(new Pyme(4, "Hotel6", "http://www.interpatagonia.com/paseos/museo-arte-moderno-chiloe/museo-arte-moderno-chiloe-b.jpg"));

        destacados = new ArrayList<>();

        destacados.add(new Destacado(1, "el mejor curanto", "ingresa aqui", "https://cache-graphicslib.viator.com/graphicslib/thumbs360x240/12646/SITours/excursi-n-de-d-a-completo-isla-de-chilo-con-ancud-castro-y-dalcahue-in-puerto-varas-223232.jpg"));
        destacados.add(new Destacado(2, "el mejor curanto", "ingresa aqui", "http://cochainbound.com/wp-content/uploads/2016/05/PARQUE-NACIONAL-CHILOE-3.jpg"));
        destacados.add(new Destacado(3, "el mejor curanto", "ingresa aqui", "http://i4.visitchile.com/img/GalleryContent/8906/chacao.jpg"));

        //RECYCLERVIEW
        final novedadesAdapter adapter = new novedadesAdapter(getContext(), getFragmentManager(), objectos, destacados);
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

    private void obtenerDestacadosFirebase(){

        final Query db = FirebaseDatabase.getInstance().getReference().child("destacados");



        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                for (DataSnapshot ds: dataSnapshot.getChildren()) {

                }

/*                destacados = new ArrayList<>();

                destacados.add(new Destacado(1, "el mejor curanto", "ingresa aqui", "https://cache-graphicslib.viator.com/graphicslib/thumbs360x240/12646/SITours/excursi-n-de-d-a-completo-isla-de-chilo-con-ancud-castro-y-dalcahue-in-puerto-varas-223232.jpg"));
                destacados.add(new Destacado(2, "el mejor curanto", "ingresa aqui", "http://cochainbound.com/wp-content/uploads/2016/05/PARQUE-NACIONAL-CHILOE-3.jpg"));
                destacados.add(new Destacado(3, "el mejor curanto", "ingresa aqui", "http://i4.visitchile.com/img/GalleryContent/8906/chacao.jpg"));*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
