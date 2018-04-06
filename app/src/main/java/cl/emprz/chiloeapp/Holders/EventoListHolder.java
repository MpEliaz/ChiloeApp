package cl.emprz.chiloeapp.Holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cl.emprz.chiloeapp.R;

/**
 * Created by elias on 28-01-17.
 */

public class EventoListHolder extends RecyclerView.ViewHolder {

    public View itemView;
    public EventoListHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void setImagen(String url, Context cx) {
        ImageView imagen = (ImageView)itemView.findViewById(R.id.evento_imagen);
        Glide.with(cx).load(url).into(imagen);

    }

    public void setTitulo(String nombre) {
        TextView nombre_ = (TextView)itemView.findViewById(R.id.evento_titulo);
        nombre_.setText(nombre);
    }

    public void setDescripcion(String nombre) {
        TextView nombre_ = (TextView)itemView.findViewById(R.id.evento_desc);
        nombre_.setText(nombre);
    }

    public void setComuna(String nombre) {
        TextView nombre_ = (TextView)itemView.findViewById(R.id.evento_comuna);
        nombre_.setText(nombre);
    }

    public void setValor(int nombre) {
        TextView nombre_ = (TextView)itemView.findViewById(R.id.evento_precio);
        if (nombre == 0){
            nombre_.setText("Gratis");
        }else{
            nombre_.setText(Integer.toString(nombre));
        }

    }

    public void setFecha(String nombre) {
        TextView nombre_ = (TextView)itemView.findViewById(R.id.evento_fecha);
        nombre_.setText(nombre);
    }
}
