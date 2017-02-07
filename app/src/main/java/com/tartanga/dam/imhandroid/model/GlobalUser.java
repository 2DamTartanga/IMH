package com.tartanga.dam.imhandroid.model;

import android.app.Application;

/**
 * Created by 2dam on 06/02/2017.
 */

public class GlobalUser{
    private static GlobalUser instance;
    private User globalUser;

    public GlobalUser(){}


    public User getGlobalUser() {
        return globalUser;
    }

    public void setGlobalUser(User globalUser) {
        this.globalUser = globalUser;
    }

    public static synchronized GlobalUser getInstance(){
        if(instance==null){
            instance = new GlobalUser();
        }
        return instance;
    }
}
