package com.tartanga.dam.imhandroid.model;

import android.app.Application;

/**
 * Created by 2dam on 06/02/2017.
 */

public class GlobalUser extends Application{
    private static User globalUser;

    public User getGlobalUser() {
        return globalUser;
    }

    public void setGlobalUser(User globalUser) {
        this.globalUser = globalUser;
    }
}
