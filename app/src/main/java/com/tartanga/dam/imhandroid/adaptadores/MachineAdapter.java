package com.tartanga.dam.imhandroid.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.model.Machine;
import com.tartanga.dam.imhandroid.model.WorkOrder;

import java.util.List;

/**
 * Created by 2dam on 31/01/2017.
 */

public class MachineAdapter extends RecyclerView.Adapter<MachineAdapter.MachineViewHolder>{

    private List<Machine> machines;

    public static class MachineViewHolder extends RecyclerView.ViewHolder {

        //TODO: AQUI DECLARAS LAS VARIABLES

        public MachineViewHolder(View v) {
            super(v);
            //TODO: INICIALIZAS LAS VARIABLES DE LA TARJETA
        }
    }

    public MachineAdapter(List<Machine> machines) {
        this.machines = machines;
    }

    @Override
    public MachineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_machine, parent, false);

        return new MachineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MachineViewHolder holder, int position) {
        //TODO: AQUI ES DONDE CARGAS LOS DATOS A LOS ELEMTOS DE LAS TARJETAS
    }

    @Override
    public int getItemCount() {

        //TODO: UN COUNT DEL NUMERO DE WORK ORDERS QUE HAY EN LA BD

        return 10;
    }


}