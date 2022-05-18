package com.example.do_an_android.Util;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class  VolleySingleton {

    private static final String TAG = "VolleySingleton";
    public static int TIMEOUT_MS=10000 ;
    private static VolleySingleton sInstance;
    private RequestQueue mRequestQueue;

    private VolleySingleton(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());


        }
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (sInstance == null)
            sInstance = new VolleySingleton(context);
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
