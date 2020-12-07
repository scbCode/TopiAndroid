package com.test.topi.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.test.topi.R;
import com.test.topi.adapter.adapter;
import com.test.topi.models.Repositorio;
import com.test.topi.network.ModelManagerListener;
import com.test.topi.network.getData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Context context;
    List<Repositorio> repositorios = new ArrayList<Repositorio>();;
    public static adapter adapter;
    RecyclerView recview;
    TextView teste;
    ProgressBar load;
    List<String> nameUsers = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();

        initUI(root);

        return root;
    }

    public void initUI(View view){

        load = view.findViewById(R.id.progress);

        homeViewModel.getLista().observe(getViewLifecycleOwner(), s -> {

            try {
                JSONArray reps_json_array = s.getJSONArray("items");
                JSONObject item;
                for (int i = 0; i < reps_json_array.length(); i++)
                {
                    item = reps_json_array.getJSONObject(i);
                    Repositorio repositorio = new Repositorio();
                    repositorio = repositorio.fromJson(item.toString());
                    repositorios.add(repositorio);
                }
                load.setVisibility(View.GONE);
                adapter = new adapter(context,repositorios);
                recview = view.findViewById(R.id.rc_repo);
                recview.addItemDecoration(new DividerItemDecoration(getContext(),
                        DividerItemDecoration.VERTICAL));
                recview.setLayoutManager(new LinearLayoutManager(context));
                recview.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

    }




}