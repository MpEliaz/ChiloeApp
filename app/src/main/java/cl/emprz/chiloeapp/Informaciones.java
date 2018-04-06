package cl.emprz.chiloeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import cl.emprz.chiloeapp.adapters.InformacionesAdapter;
import cl.emprz.chiloeapp.clases.Categoria;

public class Informaciones extends AppCompatActivity implements InformacionesAdapter.OnItemClickListener {

    private RecyclerView informaciones_rv;
    private InformacionesAdapter adapter;
    private ArrayList<Categoria> categorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomaciones);

        categorias = new ArrayList<Categoria>();

        categorias.add(new Categoria(1,"Hospitales",R.drawable.hospital));
        categorias.add(new Categoria(2,"Carabineros",R.drawable.carabineros));
        categorias.add(new Categoria(3,"Bancos",R.drawable.bancos));
        categorias.add(new Categoria(4,"Farmacias",R.drawable.farmacias));
        categorias.add(new Categoria(5,"Radios",R.drawable.hospital));
        categorias.add(new Categoria(6,"Bencineras",R.drawable.hospital));

        adapter = new InformacionesAdapter(Informaciones.this, categorias);
        adapter.setOnItemClickListener(this);
        informaciones_rv = (RecyclerView) findViewById(R.id.rv_informaciones);
        informaciones_rv.setHasFixedSize(true);
        informaciones_rv.setLayoutManager(new GridLayoutManager(this,2));
        informaciones_rv.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, Categoria categoria, int position) {
        Toast.makeText(this, categoria.getNombre(), Toast.LENGTH_SHORT).show();
    }
}
