package com.tartanga.dam.imhandroid.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tartanga.dam.imhandroid.R;

public class FragmentAddReport extends Fragment {

    public FragmentAddReport() {
        // Required empty public constructor
    }

    public static FragmentAddReport newInstance(){
        return new FragmentAddReport();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.card_add_request, container, false);
    }

}
