package com.tartanga.dam.imhandroid.manager;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.activities.MainActivity;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.model.Message;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by 2dam on 01/02/2017.
 */

public class ThreadSender extends AsyncTask<Object, Object, Object>{

    private final int PORT = 8080;
   // private final String HOST = "150.241.230.4";
    private final String HOST = "10.22.82.175";
    private MessageListener listener;
    private Socket cs;
    private Message msg;
    private MainActivity m;
    private boolean fallo = false;

    public ThreadSender(Object listener, /*Socket cs,*/ Message msg) {
        Socket cs = null;
        this.listener = ((MessageListener) listener);
        try {
            cs = new Socket(HOST, PORT);
        } catch (IOException e) {
            fallo = true;
            ((MessageListener) listener).messageReceived("Connection with server lost");
        }
        this.cs = cs;
        this.msg = msg;
    }

    /*public ThreadSender(Object listener, String IP, int PORT, Message msg){
        this.listener = ((MessageListener) listener);
        this.IP = IP;
        this.PORT = PORT;
        this.msg = msg;
    }*/


    private void connectionLost() {
        //Toast.makeText(m.getApplicationContext(), "Server not available", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Object doInBackground(Object... objects) {
        if(!fallo) {
            ObjectInputStream in;
            ObjectOutputStream out;
            Object input;
            try {
                out = new ObjectOutputStream(cs.getOutputStream());
                in = new ObjectInputStream(cs.getInputStream());
                Log.d("MENSAJE", "Enviando mensaje");
                out.writeObject(msg);
                Log.d("MENSAJE", "Mensaje enviado");
                input = in.readObject();
                publishProgress(input);
            } catch (SocketException | EOFException e) {
                connectionLost();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onCancelled(Object o) {
        super.onCancelled(o);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
        listener.messageReceived(values[0]);
    }
}
