package com.test.topi.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.AndroidViewModel;
import org.json.JSONObject;
import java.util.List;
import android.content.Context;
import android.app.Application;

import com.google.android.material.snackbar.Snackbar;
import com.test.topi.network.ModelManagerListener;
import com.test.topi.network.getData;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.AuthFailureError;
import android.widget.Toast;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<JSONObject> mList;

    public HomeViewModel(Application application) {
        super(application);
        mList = new MutableLiveData<>();
        getListaNetwork(application.getApplicationContext());
    }

    public LiveData<JSONObject> getLista(){return mList;}

    public void getListaNetwork(Context context){
       getData.getDataJson(context, new ModelManagerListener() {

            @Override
            public void onSuccess(JSONObject json) {
                mList.setValue(json);
            }
           @Override
           public void onError(VolleyError error) {
               mList.setValue(null);
               Toast.makeText(context,"Erro ao carregar dados",Toast.LENGTH_SHORT).show();

           }
        });
    }
}