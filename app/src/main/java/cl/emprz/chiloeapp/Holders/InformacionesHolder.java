package cl.emprz.chiloeapp.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cl.emprz.chiloeapp.R;

/**
 * Created by elias on 09-02-17.
 */

public class InformacionesHolder extends RecyclerView.ViewHolder {

    public ImageView imagen;
    public TextView titulo;

    public InformacionesHolder(View itemView) {
        super(itemView);

        imagen = (ImageView)itemView.findViewById(R.id.item_inf_imagen);
        titulo = (TextView)itemView.findViewById(R.id.item_inf_nombre);
    }
}
