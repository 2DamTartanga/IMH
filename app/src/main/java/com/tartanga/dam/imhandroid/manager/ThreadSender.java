package com.tartanga.dam.imhandroid.manager;

import android.util.Log;

import com.tartanga.dam.imhandroid.interfaces.MessageListener;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by 2dam on 01/02/2017.
 */

public class ThreadSender extends Thread{

    private MessageListener listener;
    private Socket cs;
    private final String IP;
    private final int PORT;
    private Message msg;
    /*public ThreadSender(Object listener, Socket cs, Message msg) {
        this.listener = ((MessageListener) listener);
        this.cs = cs;
        this.msg = msg;
    }*/

    public ThreadSender(Object listener, String IP, int PORT, Message msg){
        this.listener = ((MessageListener) listener);
        this.IP = IP;
        this.PORT = PORT;
        this.msg = msg;
    }

    @Override
    public void run() {
        Log.d("MENSAJE","INICIA EL HILO");
        try {
            cs = new Socket(IP,PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            listener.messageReceived(input);

        }catch (SocketException | EOFException e) {
            connectionLost();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void connectionLost() {
    }
}
