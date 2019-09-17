package com.example.watchudrive;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.Volley;

public class RequestQueue {

    private static  RequestQueue mInstance;
    private com.android.volley.RequestQueue requestQueue;
    private static Context mCtx;

    private RequestQueue(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public com.android.volley.RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
            return requestQueue;
    }

    public static  synchronized RequestQueue getInstance(Context context){
        if(mInstance == null){
            mInstance = new RequestQueue(context);
        }
        return mInstance;
    }

    public <T> void  addToRequestque(Request<T> request){

        requestQueue.add(request);
    }
}
