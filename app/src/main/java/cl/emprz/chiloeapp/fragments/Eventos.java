package cl.emprz.chiloeapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.emprz.chiloeapp.ActividadPrincipal;
import cl.emprz.chiloeapp.Ficha;
import cl.emprz.chiloeapp.R;
import cl.emprz.chiloeapp.adapters.EventosAdapter;
import cl.emprz.chiloeapp.adapters.categoriasAdapter;
import cl.emprz.chiloeapp.clases.Categoria;
import cl.emprz.chiloeapp.clases.Evento;
import cl.emprz.chiloeapp.clases.Pyme;
import cl.emprz.chiloeapp.db.SqLiteEventos;
import cl.emprz.chiloeapp.util.Constantes;
import cl.emprz.chiloeapp.util.ECSingleton;
import cl.emprz.chiloeapp.util.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 */
public class Eventos extends Fragment implements EventosAdapter.OnItemClickListener {

    private RecyclerView rv_event;
    private ArrayList<Evento> eventos;
    private EventosAdapter adapter;
    private SqLiteEventos db;
    SwipeRefreshLayout mSwipeRefreshLayout;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eventos = new ArrayList<>();
        Utilidades util = new Utilidades(getContext());

        if(util.estaConectado()){

            obtenerEventosDesdeAPI();

        }else {

            List<Evento> eventos = Evento.listAll(Evento.class);

            for (Evento e : eventos) {
                Log.i("SQLite EC",e.getTitulo());
            }
/*
        db = new SqLiteEventos(getActivity());
        //eventos = db.obtenerTodosLosEventos();
        eventos = new ArrayList<Evento>();
        eventos.add(new Evento(1,"Minga todos invitados","gran minga gran", "10/3/2016", "Quellon", "http://www.xn--cabaaslascatas-tnb.cl/images/galeria/img213.jpg"));
        eventos.add(new Evento(2,"Concierto de jazz: Chiloé musical","grandes bandas se presentaran en el gimnacion giscal", "10/3/2016", "Castro", "http://www.trifulka.com/trifulka/wp-content/uploads/2016/03/FondoHD.jpg"));
        eventos.add(new Evento(3,"Torneo de futbolito a beneficio","vengan todos al primer torneo a beneficio del cuerpo de bomberos de la comuna de chonchi", "10/3/2016", "Chonchi", "http://www.ohigginsfc.cl/wp-content/uploads/2014/05/5-6.jpg"));
        eventos.add(new Evento(4,"Torneo de futbolito a beneficio","vengan todos al primer torneo a beneficio del cuerpo de bomberos de la comuna de chonchi", "10/3/2016", "Chonchi", "http://www.ohigginsfc.cl/wp-content/uploads/2014/05/5-6.jpg"));
        eventos.add(new Evento(5,"Torneo de futbolito a beneficio","vengan todos al primer torneo a beneficio del cuerpo de bomberos de la comuna de chonchi", "10/3/2016", "Chonchi", "http://www.ohigginsfc.cl/wp-content/uploads/2014/05/5-6.jpg"));
        eventos.add(new Evento(6,"Torneo de futbolito a beneficio","vengan todos al primer torneo a beneficio del cuerpo de bomberos de la comuna de chonchi", "10/3/2016", "Chonchi", "http://www.ohigginsfc.cl/wp-content/uploads/2014/05/5-6.jpg"));
        eventos.add(new Evento(7,"Torneo de futbolito a beneficio","vengan todos al primer torneo a beneficio del cuerpo de bomberos de la comuna de chonchi", "10/3/2016", "Chonchi", "http://www.ohigginsfc.cl/wp-content/uploads/2014/05/5-6.jpg"));
*/
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_eventos, container, false);
        adapter = new EventosAdapter(getContext(), eventos);
        adapter.setOnItemClickListener(this);
        rv_event = (RecyclerView)rootView.findViewById(R.id.rv_eventos);
        rv_event.setHasFixedSize(true);
        rv_event.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_event.setAdapter(adapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_eventos);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                obtenerData();
            }
        });

        return rootView;
    }

    private void obtenerData() {
        obtenerEventosDesdeAPI();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(View view, Evento evento, int position) {

        Intent i = new Intent(getActivity(), Ficha.class);
        startActivity(i);
    }

    private void obtenerEventosDesdeAPI() {

        StringRequest request = new StringRequest(Request.Method.GET, Constantes.url_get_eventos, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    eventos.clear();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);

                        Evento e = new Evento();
                        e.set_Id(o.getString("_id"));
                        e.setTitulo(o.getString("titulo"));
                        e.setDescripcion(o.getString("descripcion"));
                        e.setComuna(o.getString("comuna"));
                        //e.setFecha(o.getString("fecha"));
                        e.setValor(o.getInt("valor"));
                        e.setImagen(o.getString("imagen"));
                        eventos.add(e);
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

        ECSingleton.getInstance(getContext()).addToRequestQueue(request);
    }
}
