package cl.emprz.chiloeapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cl.emprz.chiloeapp.Informaciones;
import cl.emprz.chiloeapp.ListaPymes;
import cl.emprz.chiloeapp.R;
import cl.emprz.chiloeapp.adapters.categoriasAdapter;
import cl.emprz.chiloeapp.clases.Categoria;

/**
 * A simple {@link Fragment} subclass.
 */
public class Categorias extends Fragment implements categoriasAdapter.OnItemClickListener {

    private RecyclerView rv_cat;
    private ArrayList<Categoria> categorias;
    private categoriasAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categorias = new ArrayList<Categoria>();

        categorias.add(new Categoria(1,"¿Donde Dormir?",R.drawable.hostal));
        categorias.add(new Categoria(2,"¿Donde Comer?",R.drawable.curanto));
        categorias.add(new Categoria(3,"¿Qué Hacer?",R.drawable.muelle));
        categorias.add(new Categoria(4,"¿Qué Visitar?",R.drawable.iglesia));
        categorias.add(new Categoria(5,"Informaciones",R.drawable.hospital));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_categorias, container, false);
        adapter = new categoriasAdapter(getContext(), categorias);
        adapter.setOnItemClickListener(this);
        rv_cat = (RecyclerView)rootView.findViewById(R.id.rv_categorias);
        rv_cat.setHasFixedSize(true);
        rv_cat.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_cat.setAdapter(adapter);


        return rootView;
    }

    @Override
    public void onItemClick(View view, Categoria categoria, int position) {
//        Toast.makeText(getContext(), categoria.getId(), Toast.LENGTH_SHORT).show();
        Intent i = null;
        if(categoria.getNombre() != "Informaciones"){
            i = new Intent(getActivity(), ListaPymes.class);
            i.putExtra("titulo",categoria.getNombre());
            i.putExtra("id_tipo",categoria.getId());
        }else{
            i = new Intent(getActivity(), Informaciones.class);
        }

        startActivity(i);
    }
}
