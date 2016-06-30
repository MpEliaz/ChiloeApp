package cl.emprz.chiloeapp;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import cl.emprz.chiloeapp.adapters.imageSliderFrontAdapter;
import cl.emprz.chiloeapp.adapters.imageSliderProfileAdapter;

public class Ficha extends AppCompatActivity implements OnMapReadyCallback {

    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            nombre = bundle.getString("nombre");
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setTitle(nombre);
        }


        int[] images = {R.drawable.iglesia, R.drawable.curanto, R.drawable.huillinco};

        imageSliderProfileAdapter adapter = new imageSliderProfileAdapter(getSupportFragmentManager(), images);
        ViewPager pager = (ViewPager) findViewById(R.id.pager_ficha);
        pager.setAdapter(adapter);
        InkPageIndicator indicator = (InkPageIndicator)findViewById(R.id.indicator_ficha);
        indicator.setViewPager(pager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        loadMap();
    }

    private void loadMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_ficha);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng point = new LatLng(-42.475826, -73.770961);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(point,15));
        map.addMarker(new MarkerOptions()
                .position(point)
                .title("Aqui!"));
    }
}
