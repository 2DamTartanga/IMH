package com.tartanga.dam.imhandroid.manager;

import android.os.AsyncTask;
import android.util.Log;

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

    private final int PORT = 8008;
    private final String HOST = "10.22.82.173";
    private MessageListener listener;
    private Socket cs;
    private Message msg;

    public ThreadSender(Object listener, /*Socket cs,*/ Message msg) {
        Socket cs = null;
        try {
            cs = new Socket(HOST, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.listener = ((MessageListener) listener);
        this.cs = cs;
        this.msg = msg;
    }

    private void connectionLost() {
    }

    @Override
    protected Object doInBackground(Object... objects) {
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
        }catch (SocketException | EOFException e) {
            connectionLost();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
