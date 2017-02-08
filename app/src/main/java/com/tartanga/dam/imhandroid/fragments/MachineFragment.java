package com.tartanga.dam.imhandroid.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.activities.MachinesActivity;

public class MachineFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "param1";
    private static final String ARG_STATUS = "param2";

    // TODO: Rename and change types of parameters
    private String id;
    private char status;

    //private OnFragmentInteractionListener mListener;
    private MachinesActivity onClickListener;

    public MachineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id Parameter 1.
     * @param status Parameter 2.
     * @return A new instance of fragment MachineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MachineFragment newInstance(String id, char status) {
        MachineFragment fragment = new MachineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        args.putChar(ARG_STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ARG_ID);
            status = getArguments().getChar(ARG_STATUS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        CardView v = ((CardView) inflater.inflate(R.layout.card_machine, container, false));
        ConstraintLayout cl = (ConstraintLayout) v.findViewById(R.id.layout);
        TextView txtId = ((TextView) cl.findViewById(R.id.tv_machine_name));
        ImageView imgStatus = ((ImageView) cl.findViewById(R.id.iv_machine_status));
        status = Character.toLowerCase(status);
        switch (status){
            case 'a':
                imgStatus.setImageResource(R.drawable.ic_half_working);
                break;
            case 'v':
                imgStatus.setImageResource(R.drawable.ic_working);
                break;
            case 'r':
                imgStatus.setImageResource(R.drawable.ic_not_working);
                break;
        }
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        /*if (mListener != null) {
           mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);/*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    public void setOnClickListener(MachinesActivity onClickListener) {
        this.onClickListener = onClickListener;
    }

}
