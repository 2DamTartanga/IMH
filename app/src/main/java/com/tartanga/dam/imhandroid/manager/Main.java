package com.tartanga.dam.imhandroid.manager;

import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.model.Breakdown;
import com.tartanga.dam.imhandroid.model.Machine;
import com.tartanga.dam.imhandroid.model.Message;
import com.tartanga.dam.imhandroid.model.Repair;
import com.tartanga.dam.imhandroid.model.User;
import com.tartanga.dam.imhandroid.model.WorkOrder;

import java.io.IOException;
import java.net.Socket;
import java.security.acl.Group;
import java.util.Date;

public class Main implements MessageListener {

    private final int PORT = 8008;
    private final String IP = "localhost";
    private Socket cs;

    Manager manager;

    public static void main(String[] args){
        try {
            Main mc = new Main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Main() throws IOException {
        cs = new Socket(IP,PORT);
        int opc = 0;
        while(opc != -1){
            System.out.println("elige");
            opc = Utilidades.leerInt();
            doThings(opc);
            System.out.println(opc);
        }
    }

    private void doThings(int opc) {
        try {
            switch (opc) {
                case 1:
                    login();
                    break;
                case 2:
                    addBreakDown();
                    break;
                case 3:
                    addRepair();
                    break;
                case 4:
                    getBreakdown();
                    break;
                case 5:
                    getGroup();
                    break;
                case 6:
                    getLocalization();
                    break;
                case 7:
                    getMachine();
                    break;
                case 8:
                    getRepair();
                    break;
                case 9:
                    getUser();
                    break;
                case 10:
                    getWorkOrder();
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //System.exit(0);
        }
    }

    private void getWorkOrder() {
        // TODO Auto-generated method stub

    }

    private void getUser() {
        // TODO Auto-generated method stub

    }

    private void getRepair() {
        // TODO Auto-generated method stub

    }

    private void getMachine() {
        // TODO Auto-generated method stub

    }

    private void getLocalization() {
        // TODO Auto-generated method stub

    }

    private void getGroup() {
        /*Group gr = new Group(1,'T');
        try {
            gr = manager.getGroup(gr);
            System.out.println(gr);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    private void getBreakdown() {
        // TODO Auto-generated method stub

    }

    private void addRepair() {
        /*Breakdown br = new Breakdown(12);
        Repair repair = new Repair(
                new Group(1, ' '),
                new Date(),
                4.5f,
                new FailureLocalization(1, null),
                true,
                "Replacements",
                "tools",
                "RepairProcess",
                false,
                false);
        WorkOrder wo = new WorkOrder(br, null, null, 0);
        wo.setRepair(repair);
        try {
            System.out.println(manager.addRepair(wo));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

    }

    private void addBreakDown() {
        /*User user = new User("IMH");
        user.setName("Manolete");
        //user.setSurname("w");
        Breakdown br = new Breakdown(0, new Date(),user, new FailureType(1, null), "Broken screw", "The main screw is broken", new Machine(1), 1);
        try {
            System.out.println(manager.addBreakdown(br));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

    }

    private void login() throws Exception {
        //User user = new User("imh", "123", null, null, null, null, 'c', null);
        //Message msg = new Message(Message.LOGIN,null,user);
        //System.out.println(manager.login(user));
        //user = new User("ik2", "123", null, null, null, null, 'c', null);
        //System.out.println(manager.login(user));
       // user = new User("ik1", "123", null, null, null, null, 'c', null);
        //System.out.println(manager.login(user));
        //ThreadSender ts = new ThreadSender(this,cs,msg);
        //ts.start();
    }


    @Override
    public void messageReceived(Object obj) {
        System.out.println(obj);
    }
}
