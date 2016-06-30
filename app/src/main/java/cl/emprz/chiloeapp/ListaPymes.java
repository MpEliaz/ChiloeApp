package cl.emprz.chiloeapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import cl.emprz.chiloeapp.adapters.pymeListAdapter;
import cl.emprz.chiloeapp.clases.Pyme;

public class ListaPymes extends AppCompatActivity implements pymeListAdapter.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pymes);

        Bundle bundle = getIntent().getExtras();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(bundle.getString("titulo"));
        }

        ArrayList<Pyme> pymes = new ArrayList<Pyme>();

        pymes.add(new Pyme(1,"Hotel Patagonia", "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen.", 3, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));
        pymes.add(new Pyme(2,"Hotel Patagonia", "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen.", 1, "http://aff.bstatic.com/images/hotel/840x460/361/36113534.jpg?s=116x88&ar=16x9"));
        pymes.add(new Pyme(3,"Hotel Patagonia", "una descripcion corta", 2, "https://media-cdn.tripadvisor.com/media/photo-s/02/29/9b/29/the-hotel-from-the-street.jpg"));
        pymes.add(new Pyme(3,"Hotel Patagonia", "una descripcion corta", 2, "https://media-cdn.tripadvisor.com/media/photo-s/03/9f/2c/23/centro-de-ocio-hotel.jpg"));
        pymes.add(new Pyme(3,"Hotel Patagonia", "una descripcion corta", 2, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));
        pymes.add(new Pyme(3,"Hotel Patagonia", "una descripcion corta", 2, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));
        pymes.add(new Pyme(3,"Hotel Patagonia", "una descripcion corta", 2, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));
        pymes.add(new Pyme(3,"Hotel Patagonia", "una descripcion corta", 2, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));
        pymes.add(new Pyme(3,"Hotel Patagonia", "una descripcion corta", 2, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));
        pymes.add(new Pyme(3,"Hotel Patagonia", "una descripcion corta", 2, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));



        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_lista_pymes);
        pymeListAdapter adapter = new pymeListAdapter(ListaPymes.this, pymes);
        adapter.setOnItemClickListener(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, Pyme pyme, int position) {
        Intent i = new Intent(this, Ficha.class);
        i.putExtra("nombre",pyme.getNombre());
        i.putExtra("id",pyme.getId());
        startActivity(i);
    }
}
