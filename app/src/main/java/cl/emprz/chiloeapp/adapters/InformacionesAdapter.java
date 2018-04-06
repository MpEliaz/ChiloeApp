package cl.emprz.chiloeapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cl.emprz.chiloeapp.Holders.InformacionesHolder;
import cl.emprz.chiloeapp.R;
import cl.emprz.chiloeapp.clases.Categoria;

/**
 * Created by elias on 09-02-17.
 */

public class InformacionesAdapter extends RecyclerView.Adapter<InformacionesHolder> {

    List<Categoria> categorias;
    private OnItemClickListener onItemClickListener;
    private Context cx;

    public InformacionesAdapter(Context cx, List<Categoria> categorias) {
        this.categorias = categorias;
        this.cx = cx;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, Categoria categoria, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    @Override
    public InformacionesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_informaciones,parent,false);
        final InformacionesHolder vh = new InformacionesHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(v, categorias.get(vh.getAdapterPosition()), vh.getAdapterPosition());
                }
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(InformacionesHolder holder, int position) {
        holder.titulo.setText(categorias.get(position).getNombre());
        Typeface custom = Typeface.createFromAsset(cx.getAssets(), "fonts/Montserrat-Bold.ttf");
        holder.titulo.setTypeface(custom);
        holder.imagen.setImageResource(categorias.get(position).getId_imagen());
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }
}
