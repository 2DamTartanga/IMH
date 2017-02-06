package com.tartanga.dam.imhandroid.manager;

import com.tartanga.dam.imhandroid.interfaces.MessageListener;

import java.io.IOException;
import java.net.Socket;

public class Main implements MessageListener {

    private final int PORT = 8008;
    private final String IP = "10.22.82.173";
    private Socket cs;
    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    Manager manager;

    /*public static void main(){
        try {
            Main m = new Main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public Main(String accion, String object, Object content) throws IOException {
        cs = new Socket(IP,PORT);
        Message msg = new Message(accion, object, content);
        ThreadSender ts = new ThreadSender(this, cs, msg);
        ts.start();
    }



    @Override
    public void messageReceived(Object obj) {
        this.setObj(obj);
    }
}
