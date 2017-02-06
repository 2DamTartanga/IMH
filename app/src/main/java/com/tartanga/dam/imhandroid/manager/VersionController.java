package com.tartanga.dam.imhandroid.manager;

import com.tartanga.dam.imhandroid.R;

/**
 * Created by 2dam on 06/02/2017.
 */

public class VersionController {

    public boolean olderVersions() {
        if (android.os.Build.VERSION.SDK_INT > 19) {
            return false;
        }
        else {
            return true;
        }
    }
}
