package com.tartanga.dam.imhandroid.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.activities.MainActivity;

/**
 * Created by 2dam on 22/02/2017.
 */

public class dialogFragment extends DialogFragment{

    public dialogFragment newInstance(String title){
        dialogFragment fragment = new dialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        String title = getArguments().getString("title");
        return new AlertDialog.Builder(getActivity()).setIcon(R.mipmap.ic_launcher2).setTitle(title).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        ((MainActivity)getActivity()).doPositiveClick();
                    }
                })
                .create();
    }
}
