package com.tartanga.dam.imhandroid.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.model.Section;

import java.util.List;

/**
 * Created by 2dam on 31/01/2017.
 */

public class WorkZonesAdapter extends RecyclerView.Adapter<WorkZonesAdapter.WorkZonesViewHolder>{

    private List<Section> zones;

    public static class WorkZonesViewHolder extends RecyclerView.ViewHolder {
        //TODO: AQUI DECLARAS LAS VARIABLES
        private TextView tvWorking;
        private TextView tvHalfWorking;
        private TextView tvNotWorking;

        public WorkZonesViewHolder(View v) {
            super(v);
            //TODO: INICIALIZAS LAS VARIABLES DE LA TARJETA
            tvWorking = (TextView) v.findViewById(R.id.tv_working);
            tvHalfWorking = (TextView) v.findViewById(R.id.tv_half_working);
            tvNotWorking = (TextView) v.findViewById(R.id.tv_not_working);
        }
    }

    public WorkZonesAdapter(List<Section> zones) {
        this.zones = zones;
    }

    @Override
    public WorkZonesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_work_zone, parent, false);
        return new WorkZonesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WorkZonesViewHolder holder, int position) {
        //TODO: AQUI ES DONDE CARGAS LOS DATOS A LOS ELEMENTOS DE LAS TARJETAS

    }

    @Override
    public int getItemCount() {
        //TODO: UN COUNT DEL NUMERO DE WORK ORDERS QUE HAY EN LA BD

        return 55;
    }
}
