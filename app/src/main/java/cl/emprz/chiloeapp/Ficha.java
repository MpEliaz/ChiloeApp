package cl.emprz.chiloeapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cl.emprz.chiloeapp.adapters.imageSliderProfileAdapter;
import cl.emprz.chiloeapp.clases.Imagen;
import cl.emprz.chiloeapp.clases.Pyme;

public class Ficha extends AppCompatActivity implements OnMapReadyCallback {

    ViewPager pager;
    imageSliderProfileAdapter adapter;
    private Double lat;
    private Double lng;
    private ArrayList<Imagen> url_imagenes;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Timer timer;
    int page = 1;
    private int id;

    private TextView descripcion;
    private TextView direccion;
    private TextView email;
    private TextView telefono;
    private TextView web;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            id = bundle.getInt("id");
/*            nombre = bundle.getString("nombre");
            direccion = bundle.getString("direccion");
            comuna = bundle.getString("comuna");
            calificacion = bundle.getInt("calificacion");
            imagenes = bundle.getStringArray("imagenes");

            lat = bundle.getDouble("lat");
            lng = bundle.getDouble("lng");
            Log.i("EC", "ubicacion: " + lat + " " + lng);
            //Log.i("EC","imagenes: "+imagenes.toString());*/
            //imagenes = new String[]{"https://i.ytimg.com/vi/m1Aj4nXs9VI/maxresdefault.jpg", "http://nunorktimes.cl/wp-content/uploads/2016/07/chiloe_rutas_chile.jpg"};
            obtenerDatos();
        }


        //TextView  = (TextView)findViewById(R.id.ficha);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button llamar = (Button) findViewById(R.id.ficha_call_btn);
        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Ficha.this, "Deberia Iniciar la llamada", Toast.LENGTH_SHORT).show();

            }
        });

        Typeface custom_bold = Typeface.createFromAsset(Ficha.this.getAssets(), "fonts/Montserrat-Bold.ttf");
        Typeface custom_normal = Typeface.createFromAsset(Ficha.this.getAssets(), "fonts/Montserrat-Regular.ttf");

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setExpandedTitleTypeface(custom_normal);
            collapsingToolbarLayout.setExpandedTitleTypeface(custom_bold);
            collapsingToolbarLayout.setCollapsedTitleTypeface(custom_normal);

        }


//        int[] images = {R.drawable.iglesia, R.drawable.curanto, R.drawable.huillinco};







        fab = (FloatingActionButton) findViewById(R.id.fab);
        loadMap();
    }

    private void obtenerDatos() {
        final Query db = FirebaseDatabase.getInstance().getReference("pymes/"+id);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                setearDatos(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setearDatos(DataSnapshot dataSnapshot) {

        final Pyme p = dataSnapshot.getValue(Pyme.class);
        ArrayList<Imagen> images = new ArrayList();
        DataSnapshot imagenes = dataSnapshot.child("imagenes");
        url_imagenes = new ArrayList<>();

        for (DataSnapshot i : imagenes.getChildren()) {

            Imagen imagen = i.getValue(Imagen.class);
            url_imagenes.add(imagen);
            //DataSnapshot d = i.child("imagenes");

        }

        descripcion = (TextView) findViewById(R.id.ficha_descripcion);
        direccion = (TextView) findViewById(R.id.ficha_direccion);
        email = (TextView) findViewById(R.id.ficha_email);
        telefono = (TextView) findViewById(R.id.ficha_telefono);
        web = (TextView) findViewById(R.id.ficha_web);

        descripcion.setText(p.getDescipcion_larga());
        direccion.setText(p.getDireccion());
        email.setText(p.getEmail());
        telefono.setText(p.getTelefono());
        web.setText(p.getWeb());

        if(collapsingToolbarLayout != null){
            collapsingToolbarLayout.setTitle(p.getNombre());
        }


        pager = (ViewPager) findViewById(R.id.pager_ficha);
        adapter = new imageSliderProfileAdapter(getSupportFragmentManager(), url_imagenes);
        pager.setAdapter(adapter);
        InkPageIndicator indicator = (InkPageIndicator)findViewById(R.id.indicator_ficha);
        indicator.setViewPager(pager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri gmmIntentUri = Uri.parse("google.navigation:q="+p.getLatitud()+","+p.getLongitud());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });



    }

    private void loadMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_ficha);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng point = new LatLng(-43.1149002, -73.6241854);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(point,10));
        map.addMarker(new MarkerOptions()
                .position(point)
                .title("Aqui!"));

        map.getUiSettings().setAllGesturesEnabled(false);
        map.setPadding(0,0,0,400);

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent i = new Intent(Ficha.this, FichaMap.class);
                i.putExtra("nombre","Aqui!");
                i.putExtra("lat",-43.1149002);
                i.putExtra("lng",-73.6241854);
                startActivity(i);
            }
        });
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
