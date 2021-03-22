package com.example.tp5;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MonRecyclerViewAdapter extends RecyclerView.Adapter<MonRecyclerViewAdapter.DataContainer> {
    private final ArrayList<Data> Data;
    private static DetecteurDeClicSurRecycler detecteurDeClicSurRecycler;

    public MonRecyclerViewAdapter(ArrayList<Data> data) {
        this.Data = data;
    }

    @NonNull
    @Override
    public DataContainer onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new DataContainer(view);
    }

    @Override
    public void onBindViewHolder(DataContainer container, int position) {
        container.tv_planetName.setText(Data.get(position).getPlanetName());
        container.tv_planetDesc.setText(Data.get(position).getPlanetDesc());
        container.iv_planetIcon.setImageResource(
                MainActivity.getImageID(
                        Data.get(position).getPlanetName().toLowerCase()
                )
        );
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public void setDetecteurDeClicSurRecycler(DetecteurDeClicSurRecycler detecteurDeClicSurRecycler) {
        this.detecteurDeClicSurRecycler = detecteurDeClicSurRecycler;
    }

    public static class DataContainer extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_planetName;
        TextView tv_planetDesc;
        ImageView iv_planetIcon;

        public DataContainer(View itemView) {
            super(itemView);
            tv_planetName = (TextView) itemView.findViewById(R.id.planet_name);
            tv_planetDesc = (TextView) itemView.findViewById(R.id.planet_desc);
            iv_planetIcon = (ImageView) itemView.findViewById(R.id.planet_icon);
            itemView.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View v) {
            ((CardView)v).setCardBackgroundColor(Color.rgb(255,0,0));
            detecteurDeClicSurRecycler.clicSurRecyclerItem(getAdapterPosition(), v);
        }
    }

}