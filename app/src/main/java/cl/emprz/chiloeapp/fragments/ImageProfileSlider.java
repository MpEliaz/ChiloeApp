package cl.emprz.chiloeapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cl.emprz.chiloeapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageProfileSlider#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageProfileSlider extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String URL = "url";
    private static final String TITULO = "titulo";

    // TODO: Rename and change types of parameters
    private String url;
    private String titulo;


    public ImageProfileSlider() {
        // Required empty public constructor
    }

    public static ImageProfileSlider newInstance(String url, String titulo) {
        ImageProfileSlider fragment = new ImageProfileSlider();
        Bundle args = new Bundle();
        args.putString(URL, url);
        args.putString(TITULO, titulo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(URL);
            titulo = getArguments().getString(TITULO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_profile_slider, container, false);

        ImageView image = (ImageView) v.findViewById(R.id.imageSlider);
        //image.setImageDrawable(getContext().getResources().getDrawable(url));
        Glide.with(getContext()).load(url).into(image);
        return v;
    }

}
