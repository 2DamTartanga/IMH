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
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BreakdownFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BreakdownFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SUBJECT = "ixm";
    private static final String ARG_ID = "id";
    private static final String ARG_SEVERITY = "severity";

    // TODO: Rename and change types of parameters
    private String subject;
    private String id;
    private String severity;


    //private OnFragmentInteractionListener mListener;

    public BreakdownFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param subject Parameter 1.
     * @param id Parameter 2.
     * @return A new instance of fragment BreakdownFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BreakdownFragment newInstance(String subject, String id, String severity) {
        BreakdownFragment fragment = new BreakdownFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SUBJECT, subject);
        args.putString(ARG_ID, id);
        args.putString(ARG_SEVERITY, severity);
        fragment.setArguments(args);
        return fragment;
    }

    public static BreakdownFragment newInstance(){
        return new BreakdownFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            subject = getArguments().getString(ARG_SUBJECT);
            id = getArguments().getString(ARG_ID);
            severity = getArguments().getString(ARG_SEVERITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        CardView v = (CardView) inflater.inflate(R.layout.card_work_order, container, false);
        ConstraintLayout lay = (ConstraintLayout) v.findViewById(R.id.layout);
        TextView txtSubject = (TextView) lay.findViewById(R.id.tv_breakdown);
        TextView txtMachine = (TextView) lay.findViewById(R.id.tv_machine);
        TextView txtSeverity = (TextView) lay.findViewById(R.id.tv_priority);
        txtSubject.setText(subject);
        txtMachine.setText(id);
        txtSeverity.setText(severity);
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
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
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
}
