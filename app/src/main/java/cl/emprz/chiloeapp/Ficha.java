package cl.emprz.chiloeapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import java.util.Timer;
import java.util.TimerTask;

import cl.emprz.chiloeapp.adapters.imageSliderFrontAdapter;
import cl.emprz.chiloeapp.adapters.imageSliderProfileAdapter;

public class Ficha extends AppCompatActivity implements OnMapReadyCallback {

    private String nombre;
    private String direccion;
    private String comuna;
    private int calificacion;
    ViewPager pager;
    private Double lat;
    private Double lng;
    private String[] imagenes;
    Timer timer;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            nombre = bundle.getString("nombre");
            direccion = bundle.getString("direccion");
            comuna = bundle.getString("comuna");
            calificacion = bundle.getInt("calificacion");
            imagenes = bundle.getStringArray("imagenes");
            lat = bundle.getDouble("lat");
            lng = bundle.getDouble("lng");
            Log.i("EC","ubicacion: "+lat+" "+lng);
            //Log.i("EC","imagenes: "+imagenes.toString());
        }

        TextView dir = (TextView)findViewById(R.id.ficha_direccion);
        dir.setText(direccion);
        //TextView  = (TextView)findViewById(R.id.ficha);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface custom_bold = Typeface.createFromAsset(Ficha.this.getAssets(), "fonts/Montserrat-Bold.ttf");
        Typeface custom_normal = Typeface.createFromAsset(Ficha.this.getAssets(), "fonts/Montserrat-Regular.ttf");

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setTitle(nombre);
            collapsingToolbarLayout.setExpandedTitleTypeface(custom_normal);
            collapsingToolbarLayout.setExpandedTitleTypeface(custom_bold);
            collapsingToolbarLayout.setCollapsedTitleTypeface(custom_normal);

        }


        int[] images = {R.drawable.iglesia, R.drawable.curanto, R.drawable.huillinco};

        imageSliderProfileAdapter adapter = new imageSliderProfileAdapter(getSupportFragmentManager(), images);
        pager = (ViewPager) findViewById(R.id.pager_ficha);
        pager.setAdapter(adapter);
        InkPageIndicator indicator = (InkPageIndicator)findViewById(R.id.indicator_ficha);
        indicator.setViewPager(pager);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
/*                Intent i = new Intent(Ficha.this, FichaMap.class);
                i.putExtra("lat", lat);
                i.putExtra("lng", lng);
                i.putExtra("nombre", nombre);

                //i.putExtra("lat", -42.475826);
                //i.putExtra("lng", -73.770961);

                startActivity(i);*/

                Uri gmmIntentUri = Uri.parse("google.navigation:q="+lat+","+lng);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
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
        LatLng point = new LatLng(lat, lng);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(point,10));
        map.addMarker(new MarkerOptions()
                .position(point)
                .title("Aqui!"));

        map.getUiSettings().setAllGesturesEnabled(false);
        map.setPadding(0,0,0,200);
    }

    public void pageSwitcher(int seconds, final ViewPager viewPager) {

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int numPages = viewPager.getAdapter().getCount();
                page = (page + 1) % numPages;
                Log.i("EC", "pagina de ficha: "+page);
                viewPager.setCurrentItem(page);
            }
        };

        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                handler.post(runnable);
            }
        }, 0, seconds * 1000); // delay
        // in
        // milliseconds
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if(pager != null){

            pageSwitcher(4, pager);
        }
    }
}
