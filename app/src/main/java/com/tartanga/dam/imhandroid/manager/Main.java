package com.tartanga.dam.imhandroid.manager;

import android.util.Log;

import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.model.Message;

import java.io.IOException;

public class Main implements MessageListener {

    private final int PORT = 8008;
    private final String IP = "10.22.82.173";
    //private final String IP = "10.22.87.157";
    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    //Manager manager;

    /*public static void main(){
        try {
            Main m = new Main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public Main(String accion, String object, Object content) throws IOException {
        Log.d("MENSAJE","ENTRA AL MAIN MANAGER");
        Message msg = new Message(accion, object, content);
        //ThreadSender ts = new ThreadSender(this msg);
        //ts.start();
    }

    @Override
    public void messageReceived(Object obj) {
        this.setObj(obj);
    }
}
