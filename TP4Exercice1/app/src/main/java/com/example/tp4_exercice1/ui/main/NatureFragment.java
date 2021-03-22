package com.example.tp4_exercice1.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.tp4_exercice1.R;

public class NatureFragment extends Fragment {
    // Les champs utilisés par chaque Fragment
    //Ils sont distincts pour chaque NatureFragment instancié
    private String title;
    private int page;
    /**
     * Pour la sauvegarde et la récupération des données
     * dans un Bundle
     */
    private static final String ARG_SECTION_NUMBER = "numero_page";
    private static final String ARG_SECTION_TITLE = "titre_page";

    /**
     * Retourne une nouvelle instance de ce fragment
     * pour le numéro de section donné.
     */
    public static NatureFragment newInstance(int position, String title) {
        NatureFragment fragment = new NatureFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        args.putString(ARG_SECTION_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    // retrouver les valeurs des champs à partir du bundle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(ARG_SECTION_NUMBER, 0);
        title = getArguments().getString(ARG_SECTION_TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutID;
        View view;
        if( page == 4 ){
            layoutID = R.layout.menu;
            view = inflater.inflate(R.layout.menu, container, false);
            int[] imagesID = new int[]{R.id.spring,R.id.summer,R.id.autumn,R.id.winter};
            for(int i=0;i<imagesID.length;i++){
                ImageView img = (ImageView)view.findViewById(imagesID[i]);
                img.setClickable(true);
                int position = i;
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((ViewPager)getActivity().findViewById(R.id.view_pager)).setCurrentItem(position);
                    }
                });
            }
        } else {
            layoutID = R.layout.fragment_main;
            view = inflater.inflate(R.layout.fragment_main, container, false);
            ImageView image = (ImageView) view.findViewById(R.id.imageView);
            switch (page){
                case 0:
                    image.setImageResource(R.drawable.spring);
                    break;
                case 1:
                    image.setImageResource(R.drawable.summer);
                    break;
                case 2:
                    image.setImageResource(R.drawable.autumn);
                    break;
                case 3:
                    image.setImageResource(R.drawable.winter);
                    break;
            }
        }
        return view;
    }
}