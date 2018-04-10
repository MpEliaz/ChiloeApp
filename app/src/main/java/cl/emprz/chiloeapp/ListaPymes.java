package cl.emprz.chiloeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import cl.emprz.chiloeapp.adapters.pymeListAdapter;
import cl.emprz.chiloeapp.clases.Imagen;
import cl.emprz.chiloeapp.clases.Pyme;
import cl.emprz.chiloeapp.util.Utilidades;

public class ListaPymes extends AppCompatActivity implements pymeListAdapter.OnItemClickListener{

    int id_tipo = -1;
    private ArrayList<Pyme> pymes;
    private RecyclerView rv;
    private pymeListAdapter adapter;
    private String FIREBASE_CHILD = "pymes";
    private DatabaseReference mDatabase;
    //private FirebaseRecyclerAdapter mAdapter;
    private ProgressBar progressBar;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pymes);

        db = FirebaseFirestore.getInstance();


        progressBar = (ProgressBar) findViewById(R.id.progressBar_lista_pyme);

        //OBTENER EL ID DEL TIPO DE PYMES QUE NECESITAMOS
        Bundle bundle = getIntent().getExtras();
        id_tipo = bundle.getInt("id_tipo");

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(bundle.getString("titulo"));
        }

        //pymes = new ArrayList<Pyme>();
        Utilidades util = new Utilidades(ListaPymes.this);

        //<editor-fold desc="Obtener datos local del api">
        if(util.estaConectado()){

            //obtenerPymesDesdeAPI(id_tipo);
            //obtenerPymesFirebase(id_tipo);
        }else {
            //<editor-fold desc="RELLENAR LIST">
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

            ArrayList<Imagen> imagenes = new ArrayList<>();

            Imagen img = new Imagen();
            img.set_id("123");
            img.setNombre("nombre");
            img.setDescripcion("desc");
            img.setUrl("http://www.enjoy.cl/chiloe/wp-content/uploads/sites/31/2014/12/contenido-chiloe-spa-4-900x600.jpg");

            Imagen img2 = new Imagen();
            img2.set_id("123");
            img2.setNombre("nombre");
            img2.setDescripcion("desc");
            img2.setUrl("http://www.enjoy.cl/chiloe/wp-content/uploads/sites/31/2014/12/contenido-chiloe-spa-4-900x600.jpg");

            Imagen img3 = new Imagen();
            img3.set_id("123");
            img3.setNombre("nombre");
            img3.setDescripcion("desc");
            img3.setUrl("http://www.travelsecurity.cl/portals/0/Images/Tendencias/Tendencias-Chiloe/img2.jpg");

            imagenes.add(img);
            imagenes.add(img2);

            for (Pyme p : pymes){
                //p.setImagenes(imagenes);
                p.setLatitud(-43.1149002);
                p.setLongitud(-73.6241854);
                p.setDireccion("Santos Vargas #777");
            }
            //</editor-fold>

        }
        //</editor-fold>



        rv = (RecyclerView) findViewById(R.id.rv_lista_pymes);
        //adapter = new pymeListAdapter(ListaPymes.this, pymes, db);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //rv.setAdapter(adapter);
        obtenerPymesFirestore(id_tipo);
    }

    /*private void obtenerPymesFirebase(int id_tipo){

        final Query db = FirebaseDatabase.getInstance().getReference().child("pymes").orderByChild("tipo_pyme").equalTo(id_tipo);



        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                //final long count = dataSnapshot.child("comentarios").getChildrenCount();

                mAdapter = new FirebaseRecyclerAdapter<Pyme, PymeListaHolder>(Pyme.class, R.layout.item_pyme, PymeListaHolder.class, db){

                    @Override
                    protected void populateViewHolder(final PymeListaHolder vh, final Pyme p, int position) {

                        vh.setNombre(p.getNombre());
                        vh.setDescripcion(p.getDescipcion_corta());
                        vh.setComuna(p.getComuna());
                        vh.setDireccion(p.getDireccion());
                        vh.setRatingBar(p.getCalificacion());
                        vh.setImagen(p.getUrl_imagen(), getApplicationContext());
                        vh.setCantidadComentarios(32);
                        
                        vh.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Toast.makeText(ListaPymes.this, p.getNombre(), Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(ListaPymes.this, Ficha.class);
                                i.putExtra("id",p.getId());
                                startActivity(i);
                            }
                        });

                    }


                };
                progressBar.setVisibility(View.GONE);
                rv.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/

    private void obtenerPymesFirestore(int id_tipo){
        final Query query = db.collection("pymes").whereEqualTo("tipo_pyme", id_tipo);

        FirestoreRecyclerOptions<Pyme> response = new FirestoreRecyclerOptions.Builder<Pyme>()
                .setQuery(query, Pyme.class).build();

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {

                if(task.isSuccessful()){

                    List<Pyme> pymes = new ArrayList<>();

                    for (DocumentSnapshot doc : task.getResult()) {
                        Pyme pyme = doc.toObject(Pyme.class);
                        pyme.set_id(doc.getId());
                        pymes.add(pyme);
                    }
                    adapter = new pymeListAdapter(getApplicationContext(), pymes, db);
                    adapter.setOnItemClickListener(ListaPymes.this);
                    rv.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }else{

                }
            }
        });


    }

    /*private void obtenerPymesDesdeAPI(int id_tipo) {

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
                        p.setComuna(o.getJSONObject("comuna").getString("nombre"));
                        p.setUrl_imagen(o.getString("imagen_lista"));
                        p.setCalificacion(o.getInt("calificacion"));
                        //p.setLatitud(o.getJSONObject("ubicacion").getDouble("lat"));
                        //p.setLongitud(o.getJSONObject("ubicacion").getDouble("lng"));

                        ArrayList<Imagen> imagenes = new ArrayList<>();
                        JSONArray images = o.getJSONArray("imagenes");

                        if(images.length() > 0){

                            for(int j=0;j < images.length(); j++){
                                Imagen img = new Imagen();
                                img.set_id(images.getJSONObject(j).getString("_id"));
                                img.setNombre(images.getJSONObject(j).getString("nombre"));
                                img.setDescripcion(images.getJSONObject(j).getString("descripcion"));
                                img.setUrl(images.getJSONObject(j).getString("url"));

                                imagenes.add(img);
                            }
                            //p.setImagenes(imagenes);
                        }

                        pymes.add(p);

                    }
                    adapter.notifyDataSetChanged();

*//*                    for (Evento e : eventos) {
                        e.save();
                    }*//*

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
*//*            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String, String>();
                params.put("relacion", "");
                return params;
            }*//*

            @Override
            public String getBodyContentType() {
                return "application/json; charset=UTF-8";
            }
        };

        ECSingleton.getInstance(ListaPymes.this).addToRequestQueue(request);
    }*/

    @Override
    public void onItemClick(View view, Pyme pyme, int position) {
        Intent i = new Intent(this, Ficha.class);
        i.putExtra("nombre",pyme.getNombre());
        i.putExtra("id",pyme.get_id());
        i.putExtra("direccion",pyme.getDireccion());
        i.putExtra("comuna",pyme.getComuna());
        i.putExtra("calificacion",pyme.getCalificacion());

        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

/*        if(mAdapter != null)
            mAdapter.cleanup();*/
    }
}
