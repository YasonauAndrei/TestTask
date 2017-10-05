package com.yasand.testtask.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

/**
 * Created by user on 04.10.2017.
 */

public class InternetConnection {

    public static boolean checkConnection(@NonNull Context context){
        return ((ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo()!=null;
    }
}
