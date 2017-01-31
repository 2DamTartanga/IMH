package com.tartanga.dam.imhandroid.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.model.WorkOrder;

import java.util.List;

/**
 * Created by 2dam on 31/01/2017.
 */

public class WorkOrderAdapter extends RecyclerView.Adapter<WorkOrderAdapter.WorkOrderViewHolder>{

    private List<WorkOrder> orders;

    public static class WorkOrderViewHolder extends RecyclerView.ViewHolder {

        //TODO: AQUI VA LO QUE VA DENTRO DE LA TARJETA

        public WorkOrderViewHolder(View v) {
            super(v);
            //////////////////////////////////////////////////////
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

    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
