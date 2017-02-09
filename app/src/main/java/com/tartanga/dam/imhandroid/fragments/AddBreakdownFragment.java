package com.tartanga.dam.imhandroid.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;


public class AddBreakdownFragment extends Fragment {



    public AddBreakdownFragment() {
        // Required empty public constructor
    }


    public static AddBreakdownFragment newInstance() {
        AddBreakdownFragment fragment = new AddBreakdownFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.card_add_request, container, false);
    }

}
