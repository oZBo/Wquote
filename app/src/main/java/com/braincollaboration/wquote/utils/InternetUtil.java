package com.braincollaboration.wquote.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.braincollaboration.wquote.R;

/**
 * Created by evhenii on 22.09.17.
 */

public class InternetUtil {

    public static boolean isInternetConnectionAvailable(Context context) {
        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectionManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            Toast.makeText(context, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}


