package cl.emprz.chiloeapp.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    // TODO: Rename and change types of parameters
    private int url;
    private String titulo;


    public ImageFrontSlider() {
        // Required empty public constructor
    }

    public static ImageFrontSlider newInstance(int url, String titulo) {
        ImageFrontSlider fragment = new ImageFrontSlider();
        Bundle args = new Bundle();
        args.putInt(URL, url);
        args.putString(TITULO, titulo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getInt(URL);
            titulo = getArguments().getString(TITULO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_front_slider, container, false);

        Typeface custom_bold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Bold.ttf");
        Typeface custom_normal = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular.ttf");

        TextView titulo = (TextView) v.findViewById(R.id.vp_titulo);
        titulo.setTypeface(custom_bold);

        ImageView image = (ImageView) v.findViewById(R.id.imageSlider);
        image.setImageDrawable(getContext().getResources().getDrawable(url));
        return v;
    }

}
