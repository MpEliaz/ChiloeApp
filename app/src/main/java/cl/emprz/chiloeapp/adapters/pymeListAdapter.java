package cl.emprz.chiloeapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import cl.emprz.chiloeapp.R;
import cl.emprz.chiloeapp.clases.Categoria;
import cl.emprz.chiloeapp.clases.Pyme;

/**
 * Created by elias on 05-02-16.
 */
public class pymeListAdapter extends RecyclerView.Adapter<pymeListAdapter.ItemViewHolder> {

    List<Pyme> pymes;
    private OnItemClickListener onItemClickListener;
    Context cx;
    private FirebaseFirestore firestoreDB;

    public pymeListAdapter(Context cx, List<Pyme> pymes, FirebaseFirestore firestoreDB) {
        this.pymes = pymes;
        this.cx = cx;
        this.firestoreDB = firestoreDB;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView nombre;
        TextView descripcion;
        TextView comuna;
        RatingBar ratingBar;

        public ItemViewHolder(View itemView) {
            super(itemView);

            imagen = (ImageView)itemView.findViewById(R.id.plist_image);
            nombre = (TextView)itemView.findViewById(R.id.plist_nombre);
            descripcion = (TextView)itemView.findViewById(R.id.plist_desc_corta);
            comuna = (TextView)itemView.findViewById(R.id.plist_comuna);

            ratingBar = (RatingBar)itemView.findViewById(R.id.plist_rb);
        }
    }
    @Override
    public pymeListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pyme,parent,false);
        final ItemViewHolder vh = new ItemViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(view,pymes.get(vh.getAdapterPosition()),vh.getAdapterPosition());
                }
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(pymeListAdapter.ItemViewHolder holder, int position) {

        Typeface custom_bold = Typeface.createFromAsset(cx.getAssets(), "fonts/Montserrat-Bold.ttf");
        Typeface custom_normal = Typeface.createFromAsset(cx.getAssets(), "fonts/Montserrat-Regular.ttf");

        //holder.imagen.setImageResource( pymes.get(position).getUrl_imagen());
        Glide.with(cx).load(pymes.get(position).getUrl_imagen()).into(holder.imagen);
        holder.nombre.setText(pymes.get(position).getNombre());
        holder.nombre.setTypeface(custom_bold);
        holder.comuna.setText(pymes.get(position).getComuna());
        holder.comuna.setTypeface(custom_normal);
        holder.descripcion.setText(pymes.get(position).getDescripcion_corta());
        holder.ratingBar.setRating(pymes.get(position).getCalificacion());
    }

    @Override
    public int getItemCount() {
        return pymes.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Pyme pyme, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
