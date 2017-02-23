package com.tartanga.dam.imhandroid.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.DialogFragment;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.activities.MainActivity;

/**
 * Created by 2dam on 22/02/2017.
 */

public class ConnectionLostFragment extends DialogFragment {

    public ConnectionLostFragment newInstance(){
        ConnectionLostFragment fragment = new ConnectionLostFragment();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setMessage(R.string.dialog_fire_missiles)
        builder.setMessage(R.string.textConnectLost)
                .setPositiveButton(R.string.btnExit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
