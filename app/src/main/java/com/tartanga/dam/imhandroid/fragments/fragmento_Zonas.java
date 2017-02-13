package com.tartanga.dam.imhandroid.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;


public class fragmento_Zonas extends Fragment {

    private static final String ARG_WORKING = "w";
    private static final String ARG_HALF_WORKING = "f";
    private static final String ARG_NOT_WORKING = "pgtraram1";
    private static final String ARG_NAME = "efw";
    private static final String ARG_TOTAL = "pargtrrgam1";
    private static final String ARG_HIDDEN = "parrgam1";

    private int working;//TODO refactor
    private int hw;
    private int nw;
    private String name;
    private float total;


    public fragmento_Zonas() {
        // Required empty public constructor
    }

    public static fragmento_Zonas newInstance(int working, int hw, int nw, String name) {
        fragmento_Zonas fragment = new fragmento_Zonas();
        Bundle args = new Bundle();
        args.putInt(ARG_WORKING, working);
        args.putInt(ARG_HALF_WORKING, hw);
        args.putInt(ARG_NOT_WORKING, nw);
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    public static fragmento_Zonas newInstance(){
        return new fragmento_Zonas();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            working = getArguments().getInt(ARG_WORKING);
            nw = getArguments().getInt(ARG_NOT_WORKING);
            hw = getArguments().getInt(ARG_HALF_WORKING);
            name = getArguments().getString(ARG_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.card_work_zone,container,false);
        ConstraintLayout lay = ((ConstraintLayout) view.findViewById(R.id.layout));
        TextView txt = (TextView) lay.findViewById(R.id.tv_working);
        TextView txt2 = (TextView) lay.findViewById(R.id.tv_half_working);
        TextView txt3 = (TextView) lay.findViewById(R.id.tv_not_working);
        TextView txt4 = (TextView) lay.findViewById(R.id.tv_work_zone);
        txt.setText(String.valueOf(working));
        txt2.setText(String.valueOf(hw));
        txt3.setText(String.valueOf(nw));
        txt4.setText(name);
        return view;
    }
}
