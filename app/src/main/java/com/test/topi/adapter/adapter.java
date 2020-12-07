package com.test.topi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.test.topi.R;
import com.test.topi.models.Repositorio;
import com.test.topi.models.User;
import com.test.topi.network.ModelManagerListener;
import com.test.topi.network.getData;
import com.test.topi.ui.home.HomeFragment;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class adapter extends RecyclerView.Adapter implements Filterable {

    Context context;
    List<Repositorio> repositorios;
    List<Repositorio> repositoriosAll;
    List<User> nameUsers = new ArrayList<>();
    List<String> nameUsersAll = new ArrayList<>();

    public adapter (Context context,List<Repositorio> repositorios){
        this.repositorios=repositorios;
        this.repositoriosAll=repositorios;
        this.context=context;
        getAllNameUsers();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeTextView;
        public TextView descTextView;
        public TextView branchTextView;
        public TextView starsTextView;
        public TextView userNameTextView;
        public TextView nameSobrenomeTextView;
        public ImageView avatar_urlImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nomeTextView = (TextView) itemView.findViewById(R.id.nome);
            descTextView = (TextView) itemView.findViewById(R.id.descricao);
            branchTextView = (TextView) itemView.findViewById(R.id.branch);
            starsTextView = (TextView) itemView.findViewById(R.id.star_numb);
            userNameTextView = (TextView) itemView.findViewById(R.id.nameuser);
            nameSobrenomeTextView = (TextView) itemView.findViewById(R.id.nomeSobrenome);
            avatar_urlImageView = (ImageView) itemView.findViewById(R.id.imgUser);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_lista_repo, parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ViewHolder vholder = (ViewHolder) holder;

        vholder.nomeTextView.setText(repositorios.get(position).name);
        vholder.descTextView.setText(repositorios.get(position).description);
        vholder.userNameTextView.setText(repositorios.get(position).owner.login);

        Integer forks = repositorios.get(position).forks;
        Integer stars = repositorios.get(position).stargazers_count;

        String label_forks = getForksLabel(forks);
        vholder.branchTextView.setText(label_forks);

        String label_stars = getStarsCountLabel(stars);
        vholder.starsTextView.setText(label_stars);

        String url_user = repositorios.get(position).owner.url;

        Glide.with(context).load(repositorios.get(position).owner.avatar_url)
                .into(vholder.avatar_urlImageView);

        vholder.nameSobrenomeTextView.setText("");
        Boolean check_name = false;
        if (nameUsers.size()>0)
            for (int i=0; i<nameUsers.size();i++ ) {
                if (nameUsers.get(i).id.equals(repositorios.get(position).owner.id)) {
                    vholder.nameSobrenomeTextView.setText(nameUsers.get(i).name);
                    check_name = true;
                }
            }
        if (check_name==false)
            getData.getUseJson(context,url_user,new ModelManagerListener() {
                @Override
                public void onSuccess(JSONObject json) {

                        User user_ = new User();
                        user_ = user_.fromJson(json.toString());
                        nameUsers.add(user_);

                        vholder.nameSobrenomeTextView.setText(user_.name);

                }
                @Override
                public void onError(VolleyError error) {
                }
            });


    }


    @Override
    public int getItemCount() {
        return repositorios.size();
    }


    public Repositorio getItemIdRep(Integer i) {
        return repositorios.get(i);
    }

    @Override
    public Filter getFilter()
    {
        Filter filtro = new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                FilterResults results = new FilterResults();
                if(charSequence == null || charSequence.length() == 0)
                {

                    results.values = repositoriosAll;
                    results.count = repositoriosAll.size();

                }
                else
                {
                    ArrayList<Repositorio> filterResultsData = new ArrayList<Repositorio>();
                    for(Repositorio data : repositorios)
                    {
                        if(data.name.toLowerCase().contains(charSequence.toString().toLowerCase()))
                            filterResultsData.add(data);
                        else
                        if(data.owner.login.toLowerCase().contains(charSequence.toString().toLowerCase()))
                            filterResultsData.add(data);
                    }

                    results.values = filterResultsData;
                    results.count = filterResultsData.size();

                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                if (filterResults.count == 0)
                    notifyDataSetChanged();
                else {
                    repositorios = (List<Repositorio>) filterResults.values;
                    notifyDataSetChanged();
                }
            }
        };
        return filtro;
    }

    public void ordenarPorName(){
        Collections.sort(repositorios, (a, b) -> {
            notifyDataSetChanged();
            return a.getName().compareToIgnoreCase(b.getName());
        });
    }

    public void ordenarPorNumber(){
        Collections.sort(repositorios, (a, b) -> {
            notifyDataSetChanged();
            return b.getNumber().compareTo(a.getNumber());
        });
    }

    public static String getForksLabel(Integer totalForks){

        Double forksParse = 0.0;
        if (totalForks>=1000)
            forksParse = totalForks/1000d;
        else
            forksParse = totalForks * 1.0d;

        return String.format("%.0f", forksParse) +"k";

    }

    public static String getStarsCountLabel(Integer totalStars){

        Double starParse = 0.0;
        if (totalStars>1000)
            starParse = totalStars/1000d;
        else
            starParse = totalStars * 1.0d;

        return String.format("%.0f", starParse) +"k";

    }

    public void getAllNameUsers(){

        for (Repositorio rep : repositorios   ) {
            getData.getUseJson(context,rep.owner.url,new ModelManagerListener() {
                @Override
                public void onSuccess(JSONObject json) {

                    User user_ = new User();
                    user_ = user_.fromJson(json.toString());
                    nameUsers.add(user_);

                }
                @Override
                public void onError(VolleyError error) {
                }
            });
        }



    }




}