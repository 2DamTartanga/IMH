package com.tartanga.dam.imhandroid.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;


public class fragment_ZoneTotal extends Fragment {
    private static final String ARG_WORKING = "w";
    private static final String ARG_HALF_WORKING = "f";
    private static final String ARG_NOT_WORKING = "pgtraram1";
    private static final String ARG_TOTAL = "pargtrrgam1";

    private int working;//TODO refactor
    private int hw;
    private int nw;
    private float total;


    public fragment_ZoneTotal() {
        // Required empty public constructor
    }


    public static fragment_ZoneTotal newInstance(int working, int hw, int nw, float total) {
        fragment_ZoneTotal fragment = new fragment_ZoneTotal();
        Bundle args = new Bundle();
        args.putInt(ARG_WORKING, working);
        args.putInt(ARG_HALF_WORKING, hw);
        args.putInt(ARG_NOT_WORKING, nw);
        args.putFloat(ARG_TOTAL, total);
        fragment.setArguments(args);
        return fragment;
    }

    public static fragment_ZoneTotal newInstance(){
        return new fragment_ZoneTotal();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            working = getArguments().getInt(ARG_WORKING);
            nw = getArguments().getInt(ARG_NOT_WORKING);
            hw = getArguments().getInt(ARG_HALF_WORKING);
            total = getArguments().getFloat(ARG_TOTAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.card_work_zone_total, container, false);
        ConstraintLayout lay = ((ConstraintLayout) view.findViewById(R.id.layout));
        TextView tvW = (TextView) lay.findViewById(R.id.tv_working);
        TextView tvNW = (TextView) lay.findViewById(R.id.tv_not_working);
        TextView tvHW = (TextView) lay.findViewById(R.id.tv_half_working);
        TextView tvTotal = (TextView) lay.findViewById(R.id.tv_percent);
        tvW.setText(String.valueOf(working));
        tvHW.setText(String.valueOf(hw));
        tvNW.setText(String.valueOf(nw));
        tvTotal.setText(String.format("%.1f%%", total));
        return view;
    }
}
