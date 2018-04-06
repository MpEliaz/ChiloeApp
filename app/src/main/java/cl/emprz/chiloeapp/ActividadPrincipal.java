package cl.emprz.chiloeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import cl.emprz.chiloeapp.adapters.principalAdapter;
import cl.emprz.chiloeapp.fragments.Categorias;
import cl.emprz.chiloeapp.fragments.Eventos;
import cl.emprz.chiloeapp.fragments.Novedades;

public class ActividadPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private principalAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TextView nombre_profile;
    private TextView email_profile;
    private ImageView imagen_profile;
    private FirebaseUser user;
    private NavigationView navigationView;

    private final static int LOGIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = FirebaseAuth.getInstance().getCurrentUser();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Categorias());
        fragments.add(new Novedades());
        fragments.add(new Eventos());

        mSectionsPagerAdapter = new principalAdapter(getSupportFragmentManager(), fragments);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.textColorWhite));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

/*        if(getIntent().getExtras() != null){

            String id_pyme = getIntent().getStringExtra("id_pyme");
            int id = Integer.parseInt(id_pyme);

            if(id != -1){
                Intent intent = new Intent(this, Ficha.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }

        }*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                //myRef.setValue("Hello, World!");
                Intent i = new Intent(ActividadPrincipal.this, Informaciones.class);
                startActivity(i);

            }
        });

        DatabaseReference pymes = myRef.child("test");

/*        pymes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                Toast.makeText(ActividadPrincipal.this, data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ActividadPrincipal.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

        //ADD NAVIGATION
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(ActividadPrincipal.this);

        if(user != null){
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
        }else {
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
        }

        setearPerfil();
        //ADD NAVIGATION

    }

    private void setearPerfil() {

        View headerLayout = navigationView.getHeaderView(0);
        nombre_profile = (TextView) headerLayout.findViewById(R.id.nombre_profile);
        email_profile = (TextView) headerLayout.findViewById(R.id.email_profile);
        imagen_profile = (ImageView) headerLayout.findViewById(R.id.foto_profile);

        if(user != null){
            nombre_profile.setText(user.getDisplayName());
            email_profile.setText(user.getEmail());
            Log.e("APP", user.getPhotoUrl().toString());
            Glide.with(getApplicationContext()).load(user.getPhotoUrl().toString()).into(imagen_profile);
        }
        else{
            nombre_profile.setText("Bienvenido");
            email_profile.setText("");
            Glide.with(getApplicationContext()).load("http://tr3.cbsistatic.com/fly/bundles/techrepubliccore/images/icons/standard/icon-user-default.png").into(imagen_profile);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mis_eventos) {
            // Handle the camera action
        } else if (id == R.id.nav_mis_pymes) {

            Intent i = new Intent(ActividadPrincipal.this, MisPymes.class);
            startActivity(i);

        } else if (id == R.id.nav_mis_favoritos) {

        } else if (id == R.id.nav_herramientas) {

        }else if (id == R.id.nav_login){
            Intent i = new Intent(ActividadPrincipal.this, LoginActivity.class);
            startActivityForResult(i, LOGIN);

        } else if (id == R.id.nav_logout){

            if(user != null) {
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                user = null;
                Toast.makeText(this, "Sesion cerrada con exito.", Toast.LENGTH_SHORT).show();
                navigationView.getMenu().findItem(R.id.nav_login).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
                setearPerfil();

            }else{
                Toast.makeText(this, "No hay sesion iniciada", Toast.LENGTH_SHORT).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOGIN){
            if(resultCode == RESULT_OK){
                    navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
                    navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
            }else {
                navigationView.getMenu().findItem(R.id.nav_login).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
            }
        }
    }
}
