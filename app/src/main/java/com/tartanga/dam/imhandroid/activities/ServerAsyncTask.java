package com.tartanga.dam.imhandroid.activities;

import android.os.AsyncTask;

import java.net.Socket;

/**
 * Created by 2dam on 07/02/2017.
 */

public class ServerAsyncTask extends AsyncTask<Object, Void, Void>{
    private final int PORT = 8008;
    private final String IP = "10.22.82.173";
    //private final String IP = "10.22.87.157";
    private Socket cs;
    private Object obj;
    @Override
    protected Void doInBackground(Object... objects) {

        return null;
    }
}
