package cl.emprz.chiloeapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cl.emprz.chiloeapp.R;
import cl.emprz.chiloeapp.clases.Categoria;

/**
 * Created by elias on 05-02-16.
 */
public class categoriasAdapter extends RecyclerView.Adapter<categoriasAdapter.CatViewHolder> {

    List<Categoria> categorias;
    private OnItemClickListener onItemClickListener;
    private Context cx;

    public categoriasAdapter(Context cx, List<Categoria> categorias) {
        this.categorias = categorias;
        this.cx = cx;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, Categoria categoria, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder{
        ImageView fondo;
        TextView titulo;

        public CatViewHolder(View itemView) {
            super(itemView);

            fondo = (ImageView)itemView.findViewById(R.id.item_cat_img);
            titulo = (TextView)itemView.findViewById(R.id.item_cat_nombre);
        }
    }
    @Override
    public categoriasAdapter.CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categorias,parent,false);
        final CatViewHolder vh = new CatViewHolder(v);
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
    public void onBindViewHolder(categoriasAdapter.CatViewHolder holder, int position) {

        holder.titulo.setText(categorias.get(position).getNombre());
        Typeface custom = Typeface.createFromAsset(cx.getAssets(), "fonts/Montserrat-Bold.ttf");
        holder.titulo.setTypeface(custom);
        holder.fondo.setImageResource(categorias.get(position).getId_imagen());
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }
}
