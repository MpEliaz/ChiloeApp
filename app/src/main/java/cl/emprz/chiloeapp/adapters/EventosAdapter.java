package cl.emprz.chiloeapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cl.emprz.chiloeapp.R;
import cl.emprz.chiloeapp.clases.Evento;
import cl.emprz.chiloeapp.clases.Pyme;

/**
 * Created by elias on 05-02-16.
 */
public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ItemViewHolder> {

    List<Evento> eventos;
    private OnItemClickListener onItemClickListener;
    private Context cx;

    public EventosAdapter(Context cx, List<Evento> eventos) {
        this.eventos = eventos;
        this.cx = cx;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView titulo;
        TextView descripcion;
        TextView fecha;
        TextView comuna;
        RatingBar ratingBar;
        Toolbar toolbar;

        public ItemViewHolder(View itemView) {
            super(itemView);

            imagen = (ImageView)itemView.findViewById(R.id.evento_imagen);
            titulo = (TextView)itemView.findViewById(R.id.evento_titulo);
            descripcion = (TextView)itemView.findViewById(R.id.evento_desc);
            fecha = (TextView)itemView.findViewById(R.id.evento_fecha);
            comuna = (TextView)itemView.findViewById(R.id.evento_comuna);

            //ratingBar = (RatingBar)itemView.findViewById(R.id.plist_rb);
            //toolbar = (Toolbar) itemView.findViewById(R.id.toolbar_item_event);
        }
    }
    @Override
    public EventosAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event2,parent,false);
        final ItemViewHolder vh = new ItemViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(view,eventos.get(vh.getAdapterPosition()),vh.getAdapterPosition());
                }
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(EventosAdapter.ItemViewHolder holder, int position) {

        Typeface custom_bold = Typeface.createFromAsset(cx.getAssets(), "fonts/Montserrat-Bold.ttf");
        Typeface custom_normal = Typeface.createFromAsset(cx.getAssets(), "fonts/Montserrat-Regular.ttf");

        Glide.with(cx).load(eventos.get(position).getImagen()).into(holder.imagen);
        holder.titulo.setText(eventos.get(position).getTitulo());
        holder.titulo.setTypeface(custom_bold);
        holder.descripcion.setText(eventos.get(position).getDescripcion());
        holder.fecha.setText(eventos.get(position).getFecha());
        holder.fecha.setTypeface(custom_normal);
        holder.comuna.setText(eventos.get(position).getComuna());
        holder.comuna.setTypeface(custom_normal);
        //holder.ratingBar.setRating(eventos.get(position).getCalificacion());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Evento evento, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
