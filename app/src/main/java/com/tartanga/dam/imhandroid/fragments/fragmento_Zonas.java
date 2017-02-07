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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link fragmento_Zonas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmento_Zonas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_WORKING = "w";
    private static final String ARG_HALF_WORKING = "f";
    private static final String ARG_NOT_WORKING = "pgtraram1";
    private static final String ARG_NAME = "efw";
    private static final String ARG_TOTAL = "pargtrrgam1";
    // TODO: Rename and change types of parameters
    private int working;//TODO refactor
    private int hw;
    private int nw;
    private String name;
    private float total;

    private MessageListener mListener;
    private View.OnClickListener clickListener;

    public fragmento_Zonas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * //@param param1 Parameter 1.
     * //@param param2 Parameter 2.
     * @return A new instance of fragment fragmento_Zonas.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmento_Zonas newInstance(int working, int hw, int nw, String name) {
        fragmento_Zonas fragment = new fragmento_Zonas();
        Bundle args = new Bundle();
        args.putInt(ARG_WORKING, working);
        args.putInt(ARG_NOT_WORKING, hw);
        args.putInt(ARG_HALF_WORKING, nw);
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
        view.setOnClickListener(clickListener);
        txt.setText(working+"");
        txt2.setText(hw+"");
        txt3.setText(nw+"");
        txt4.setText(name);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    public void setOnClickListener(Activity activity){
        clickListener = ((View.OnClickListener) activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
