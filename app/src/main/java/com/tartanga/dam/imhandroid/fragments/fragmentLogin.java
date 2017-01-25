package com.tartanga.dam.imhandroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 2dam on 25/01/2017.
 */

public class fragmentLogin extends Fragment{
        public static fragmentLogin newInstance(){
            return new fragmentLogin();
        }

    public fragmentLogin(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}
