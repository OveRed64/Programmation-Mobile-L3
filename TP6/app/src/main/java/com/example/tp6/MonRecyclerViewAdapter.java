package com.example.tp6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp6.R;

import java.util.List;
import java.util.Locale;

public class MonRecyclerViewAdapter extends RecyclerView.Adapter<MonRecyclerViewAdapter.DataContainer> {
    private final List<Planet> Planets;

    public MonRecyclerViewAdapter(List<Planet> planets) {
        this.Planets = planets;
    }

    public void addPlanet(Planet planet){
        Planets.add(planet);
        notifyItemRangeInserted(Planets.size()-1, Planets.size());
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
        container.tv_planetName.setText(Planets.get(position).getName());
        String size = String.format(Locale.FRANCE,"Rayon : %d km",Planets.get(position).getSize());
        container.tv_planetDesc.setText(size);
        container.iv_planetIcon.setImageResource(
                MainActivity.getImageID(
                        Planets.get(position).getName().toLowerCase()
                )
        );
    }

    @Override
    public int getItemCount() {
        return Planets.size();
    }

    public static class DataContainer extends RecyclerView.ViewHolder {
        TextView tv_planetName;
        TextView tv_planetDesc;
        ImageView iv_planetIcon;

        public DataContainer(View itemView) {
            super(itemView);
            tv_planetName = (TextView) itemView.findViewById(R.id.planet_name);
            tv_planetDesc = (TextView) itemView.findViewById(R.id.planet_desc);
            iv_planetIcon = (ImageView) itemView.findViewById(R.id.planet_icon);
        }
    }

}