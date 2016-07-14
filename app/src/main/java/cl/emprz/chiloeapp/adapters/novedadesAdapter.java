package cl.emprz.chiloeapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cl.emprz.chiloeapp.R;
import cl.emprz.chiloeapp.clases.Pyme;

/**
 * Created by elias on 04-05-16.
 */

public class novedadesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 1;
    public static final int TYPE_ITEM = 2;
    public static final int TYPE_WEATHER = 3;

    private FragmentManager fm;
    private List<Object> objetos;
    private OnItemClickListener onItemClickListener;
    private int[] images;
    private Context cx;

    Timer timer;
    int page = 1;

    public novedadesAdapter(Context cx, FragmentManager fm, List<Object> objetos, int[] images) {
        this.fm = fm;
        this.objetos = objetos;
        this.images = images;
        this.cx = cx;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, Object o, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        switch (viewType){
            case TYPE_HEADER:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpager_novedades, parent, false);
                return new VHViewPager(v);
            case TYPE_WEATHER:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_el_tiempo, parent, false);
                return new VHWheather(v);
            case TYPE_ITEM:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destacado, parent, false);
                final VHPyme vh = new VHPyme(v);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemClickListener != null){
                            onItemClickListener.onItemClick(v, objetos.get(vh.getAdapterPosition()),vh.getAdapterPosition());
                        }
                    }
                });
                return vh;
        }
        throw new RuntimeException("el viewtype es: "+viewType+" y no calza");

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(position == 0){

            VHViewPager vhViewPager = (VHViewPager) holder;
            imageSliderFrontAdapter adapter = new imageSliderFrontAdapter(fm, images);
            vhViewPager.pager.setAdapter(adapter);
            vhViewPager.indicator.setViewPager(vhViewPager.pager);

            pageSwitcher(4,vhViewPager.pager);


        }else if(holder instanceof VHWheather) {
            VHWheather vh = (VHWheather)holder;


        }else if(holder instanceof VHPyme) {
            Typeface custom_normal = Typeface.createFromAsset(cx.getAssets(), "fonts/Montserrat-Regular.ttf");

            VHPyme vh = (VHPyme)holder;
            Pyme p = (Pyme)getItem(position);
            //vh.id = p.getId();
            vh.titulo.setText(p.getNombre());
            vh.titulo.setTypeface(custom_normal);
            Glide.with(cx).load(p.getUrl_imagen()).centerCrop().into(vh.imagen);
            Log.i("IMG", "deberia cargar la imagen "+p.getId());
            vh.toolbar.inflateMenu(R.menu.menu_item_destacado);
        }
    }

    @Override
    public int getItemCount() {
        return objetos.size();
    }

    @Override
    public int getItemViewType(int position) {

/*        switch (position){
            case 0:
                return TYPE_HEADER;
            case 1:
                return TYPE_WEATHER;
            case 2:
                return TYPE_ITEM;
        }
        return 2;*/

        if(objetos.get(position) instanceof Pyme){
            return 2;
        }else if(objetos.get(position) instanceof String){
            if(objetos.get(position) == "viewpager"){
                return 1;
            }else if(objetos.get(position) == "weather"){
                return 3;
            }
        }
        return 2;
    }

    private Object getItem(int position){
            return  objetos.get(position);
    }

    class VHPyme extends RecyclerView.ViewHolder{

        int id;
        TextView titulo;
        ImageView imagen;
        Toolbar toolbar;

        public VHPyme(View itemView) {
            super(itemView);

            titulo = (TextView)itemView.findViewById(R.id.txt_titulo_destacado);
            imagen = (ImageView)itemView.findViewById(R.id.img_imagen_destacado);
            toolbar = (Toolbar) itemView.findViewById(R.id.toolbar_destacado);
        }
    }

    class VHWheather extends RecyclerView.ViewHolder{

        ImageView imagen;
        TextView tiempo;

        public VHWheather(View itemView) {
            super(itemView);

            tiempo = (TextView)itemView.findViewById(R.id.txt_weather);
            imagen = (ImageView)itemView.findViewById(R.id.img_weather);
        }
    }

    class VHViewPager extends RecyclerView.ViewHolder{

        ViewPager pager;
        InkPageIndicator indicator;

        public VHViewPager(View itemView) {
            super(itemView);

            pager = (ViewPager)itemView.findViewById(R.id.pager_front);
            indicator = (InkPageIndicator) itemView.findViewById(R.id.indicator_front);


        }
    }

    public void pageSwitcher(int seconds, final ViewPager viewPager) {

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int numPages = viewPager.getAdapter().getCount();
                page = (page + 1) % numPages;
                viewPager.setCurrentItem(page);
            }
        };

        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                handler.post(runnable);
            }
        }, 0, seconds * 1000); // delay
        // in
        // milliseconds
    }

}
