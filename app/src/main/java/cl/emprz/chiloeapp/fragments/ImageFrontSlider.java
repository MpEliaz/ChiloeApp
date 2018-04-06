package cl.emprz.chiloeapp.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cl.emprz.chiloeapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageFrontSlider#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageFrontSlider extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String URL = "url";
    private static final String TITULO = "titulo";
    private static final String DESCRIPCION = "descripcion";

    // TODO: Rename and change types of parameters
    private String url;
    private String titulo;
    private String descripcion;

    public ImageFrontSlider() {
        // Required empty public constructor
    }

    public static ImageFrontSlider newInstance(String url, String titulo, String descripcion) {
        ImageFrontSlider fragment = new ImageFrontSlider();
        Bundle args = new Bundle();
        args.putString(URL, url);
        args.putString(TITULO, titulo);
        args.putString(DESCRIPCION, descripcion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(URL);
            titulo = getArguments().getString(TITULO);
            descripcion = getArguments().getString(DESCRIPCION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_front_slider, container, false);

        Typeface custom_bold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Bold.ttf");
        Typeface custom_normal = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular.ttf");

        TextView titulo_tv = (TextView) v.findViewById(R.id.vp_titulo);
        titulo_tv.setTypeface(custom_bold);
        titulo_tv.setText(titulo);

        TextView desc_tv = (TextView) v.findViewById(R.id.vp_desc);
        desc_tv.setTypeface(custom_bold);
        desc_tv.setText(descripcion);

        ImageView image = (ImageView) v.findViewById(R.id.imageSlider);
        //image.setImageDrawable(getContext().getResources().getDrawable(url));
        Glide.with(getContext()).load(url).into(image);
        return v;
    }

}
