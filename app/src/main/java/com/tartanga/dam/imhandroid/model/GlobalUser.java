package com.tartanga.dam.imhandroid.model;

import android.app.Application;

/**
 * Created by 2dam on 06/02/2017.
 */


public class GlobalUser extends Application{
    private static User globalUser;


    public static User getGlobalUser() {
        return globalUser;
    }

    public static void setGlobalUser(User globalUser) {
        GlobalUser.globalUser = globalUser;
    }

}
