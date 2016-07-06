package cl.emprz.chiloeapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cl.emprz.chiloeapp.adapters.pymeListAdapter;
import cl.emprz.chiloeapp.clases.Evento;
import cl.emprz.chiloeapp.clases.Pyme;
import cl.emprz.chiloeapp.util.Constantes;
import cl.emprz.chiloeapp.util.ECSingleton;
import cl.emprz.chiloeapp.util.Utilidades;

public class ListaPymes extends AppCompatActivity implements pymeListAdapter.OnItemClickListener{

    int id_tipo = -1;
    private ArrayList<Pyme> pymes;
    private RecyclerView rv;
    private pymeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pymes);

        Bundle bundle = getIntent().getExtras();

        id_tipo = bundle.getInt("id_tipo");

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(bundle.getString("titulo"));
        }
        pymes = new ArrayList<Pyme>();
        Utilidades util = new Utilidades(ListaPymes.this);

        if(util.estaConectado()){

            obtenerPymesDesdeAPI(id_tipo);
        }else {
            pymes.add(new Pyme(1,"Hotel Tierra del fuego","Quellón", "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen.", 3, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));
            pymes.add(new Pyme(2,"Hosteria Quellón","Quellón", "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen.", 1, "http://aff.bstatic.com/images/hotel/840x460/361/36113534.jpg?s=116x88&ar=16x9"));
            pymes.add(new Pyme(3,"Hotel Corcovado","Chonchi", "una descripcion corta", 2, "https://media-cdn.tripadvisor.com/media/photo-s/02/29/9b/29/the-hotel-from-the-street.jpg"));
            pymes.add(new Pyme(3,"Hotel Patagonia","Quellón", "una descripcion corta", 2, "https://media-cdn.tripadvisor.com/media/photo-s/03/9f/2c/23/centro-de-ocio-hotel.jpg"));
            pymes.add(new Pyme(3,"Hotel Patagonia","Quellón", "una descripcion corta", 2, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));
            pymes.add(new Pyme(3,"Hotel Patagonia","Quellón", "una descripcion corta", 2, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));
            pymes.add(new Pyme(3,"Hotel Patagonia","Quellón", "una descripcion corta", 2, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));
            pymes.add(new Pyme(3,"Hotel Patagonia","Quellón", "una descripcion corta", 2, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));
            pymes.add(new Pyme(3,"Hotel Patagonia","Quellón", "una descripcion corta", 2, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));
            pymes.add(new Pyme(3,"Hotel Patagonia","Quellón", "una descripcion corta", 2, "http://www.patagonialoslagos.cl/assets/img/productos/1349_.jpg"));
        }



        rv = (RecyclerView) findViewById(R.id.rv_lista_pymes);
        adapter = new pymeListAdapter(ListaPymes.this, pymes);
        adapter.setOnItemClickListener(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    private void obtenerPymesDesdeAPI(int id_tipo) {

        StringRequest request = new StringRequest(Request.Method.GET, Constantes.url_get_pymes, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    pymes.clear();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);

                        Pyme p = new Pyme();
                        p.set_id(o.getString("_id"));
                        p.setNombre(o.getString("nombre"));
                        p.setDireccion(o.getString("direccion"));
                        p.setComuna(o.getString("comuna"));
                        p.setUrl_imagen(o.getString("imagen_lista"));
                        p.setCalificacion(o.getInt("calificacion"));

                        pymes.add(p);
                        Log.i("EC", o.toString());
                    }
                    adapter.notifyDataSetChanged();

/*                    for (Evento e : eventos) {
                        e.save();
                    }*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("EC", "error: "+ error);
            }
        }){
/*            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String, String>();
                params.put("relacion", "");
                return params;
            }*/

            @Override
            public String getBodyContentType() {
                return "application/json; charset=UTF-8";
            }
        };

        ECSingleton.getInstance(ListaPymes.this).addToRequestQueue(request);
    }

    @Override
    public void onItemClick(View view, Pyme pyme, int position) {
        Intent i = new Intent(this, Ficha.class);
        i.putExtra("nombre",pyme.getNombre());
        i.putExtra("id",pyme.getId());
        startActivity(i);
    }
}
