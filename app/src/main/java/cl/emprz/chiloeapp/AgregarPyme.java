package cl.emprz.chiloeapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class AgregarPyme extends AppCompatActivity {

    private TextView nombre, telefono, direccion, email, desc_corta, desc_larga;
    private Spinner comuna, tipo_pyme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pyme);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setear_inputs();


    }

    private void setear_inputs() {

        nombre = (TextView) findViewById(R.id.addpyme_nombre);
        direccion = (TextView) findViewById(R.id.addpyme_direccion);
        email = (TextView) findViewById(R.id.addpyme_email);
        desc_corta = (TextView) findViewById(R.id.addpyme_desc_corta);
        desc_larga = (TextView) findViewById(R.id.addpyme_desc_larga);
        telefono = (TextView) findViewById(R.id.addpyme_telefono);
        comuna = (Spinner) findViewById(R.id.addpyme_comuna);
        tipo_pyme = (Spinner) findViewById(R.id.addpyme_tipo_pyme);


    }


}
