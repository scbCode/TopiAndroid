package com.test.topi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.topi.R;
import com.test.topi.models.Repositorio;


import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter implements Filterable {

    Context context;
    List<Repositorio> repositorios;
    List<Repositorio> repositoriosAll;

    public adapter (Context context,List<Repositorio> repositorios){
        this.repositorios=repositorios;
        this.context=context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nomeTextView = (TextView) itemView.findViewById(R.id.nome);
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
    }

    @Override
    public int getItemCount() {
        return repositorios.size();
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

}