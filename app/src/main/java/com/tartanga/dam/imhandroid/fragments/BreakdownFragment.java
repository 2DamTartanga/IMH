package com.tartanga.dam.imhandroid.fragments;

import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;


public class BreakdownFragment extends Fragment {


    private static final String ARG_SUBJECT = "ixm";
    private static final String ARG_ID = "id";
    private static final String ARG_SEVERITY = "severity";

    private String subject;
    private String id;
    private String severity;

    public BreakdownFragment() {
        // Required empty public constructor
    }


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
        txtMachine.setVisibility(View.INVISIBLE);
        String sev = "Severity: ";
        severity = severity.toUpperCase();
        switch (severity){
            case "V":
                sev += "Low";
                break;
            case "A":
                sev += "Medium";
                break;
            case "R":
                sev += "High";
                break;
        }

        txtSeverity.setText(sev);
        return v;
    }

}
