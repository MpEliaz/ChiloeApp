package cl.emprz.chiloeapp.Holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cl.emprz.chiloeapp.R;

/**
 * Created by elias on 11-01-17.
 */

public class PymeListaHolder extends RecyclerView.ViewHolder{
    public View itemView;

    public PymeListaHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void setImagen(String url, Context cx) {
        ImageView imagen = (ImageView)itemView.findViewById(R.id.plist_image);
        Glide.with(cx).load(url).into(imagen);

    }

    public void setNombre(String nombre) {
        TextView nombre_ = (TextView)itemView.findViewById(R.id.plist_nombre);
        nombre_.setText(nombre);
    }

    public void setDireccion(String nombre) {
        TextView direccion = (TextView)itemView.findViewById(R.id.plist_direccion);
        direccion.setText(nombre);
    }

    public void setDescripcion(String descripcion) {
        TextView descripcion_ = (TextView)itemView.findViewById(R.id.plist_desc_corta);
        descripcion_.setText(descripcion);
    }

    public void setComuna(String comuna) {
        TextView comuna_ = (TextView)itemView.findViewById(R.id.plist_comuna);
        comuna_.setText(comuna);
    }

    public void setRatingBar(int val) {
        RatingBar ratingBar = (RatingBar)itemView.findViewById(R.id.plist_rb);
        ratingBar.setNumStars(val);
    }

    public void setCantidadComentarios(long count){
        TextView comentarios_ = (TextView)itemView.findViewById(R.id.plist_comentarios1);
        comentarios_.setText((String.valueOf(count)));
    }

}
