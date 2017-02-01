package com.tartanga.dam.imhandroid.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tartanga.dam.imhandroid.R;

import java.util.List;

public class WorkOrderAdapter extends RecyclerView.Adapter<WorkOrderAdapter.WorkOrderViewHolder>{

    private List<WorkOrder> orders;

    public static class WorkOrderViewHolder extends RecyclerView.ViewHolder {

        //TODO: AQUI DECLARAS LAS VARIABLES

        public WorkOrderViewHolder(View v) {
            super(v);
            //TODO: INICIALIZAS LAS VARIABLES DE LA TARJETA
        }
    }

    public WorkOrderAdapter(List<WorkOrder> orders) {
        this.orders = orders;
    }

    @Override
    public WorkOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_work_order, parent, false);

        return new WorkOrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WorkOrderViewHolder holder, int position) {
        //TODO: AQUI ES DONDE CARGAS LOS DATOS A LOS ELEMTOS DE LAS TARJETAS
    }

    @Override
    public int getItemCount() {

        //TODO: UN COUNT DEL NUMERO DE WORK ORDERS QUE HAY EN LA BD

        return 55;
    }

}
