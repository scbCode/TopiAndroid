package com.test.topi.network;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.AuthFailureError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class getData{

    public static void
    getDataJson(final Context context, final ModelManagerListener listener){

        RequestQueue requestQueue=   Volley.newRequestQueue(context);
        try{
            String url=config.getUrl();
            JsonObjectRequest jsonObjectRequest=  new  JsonObjectRequest(Request.Method.GET,url,null,
               new  Response.Listener<JSONObject>(){
                    @Override
                    public  void  onResponse(JSONObject  response){
                        listener.onSuccess(response);
                    }
                },new  Response.ErrorListener(){
                    @Override
                    public  void  onErrorResponse(VolleyError  error){

                        listener.onError(error);
                    }
                });
           requestQueue.add(jsonObjectRequest);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void
    getUseJson(final Context context,String url, final ModelManagerListener listener){

        RequestQueue requestQueue=   Volley.newRequestQueue(context);
        try{
            JsonObjectRequest jsonObjectRequest=  new  JsonObjectRequest(Request.Method.GET,url,null,
                    new  Response.Listener<JSONObject>(){
                        @Override
                        public  void  onResponse(JSONObject  response){

                            listener.onSuccess(response);
                        }
                    },new  Response.ErrorListener(){
                @Override
                public  void  onErrorResponse(VolleyError  error){

                    listener.onError(error);
                }
            });
            requestQueue.add(jsonObjectRequest);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
