package com.tartanga.dam.imhandroid.manager;

import android.os.Build;

/**
 * Created by 2dam on 06/02/2017.
 */

public class VersionController {

    public boolean olderVersions() {
        return Build.VERSION.SDK_INT > 19;
    }
}
