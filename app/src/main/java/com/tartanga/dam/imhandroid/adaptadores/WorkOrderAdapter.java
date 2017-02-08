package com.tartanga.dam.imhandroid.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.model.WorkOrder;

import java.util.ArrayList;
import java.util.List;

public class WorkOrderAdapter extends RecyclerView.Adapter<WorkOrderAdapter.WorkOrderViewHolder> {

    private List<WorkOrder> orders;


    public static class WorkOrderViewHolder extends RecyclerView.ViewHolder {

        //TODO: AQUI DECLARAS LAS VARIABLES

        public TextView tv_breakdown;
        public TextView tv_machine;
        public TextView tv_priority;
        public TextView tv_code;

        public WorkOrderViewHolder(View v) {
            super(v);
            tv_breakdown = (TextView)v.findViewById(R.id.tv_breakdown);
            tv_machine = (TextView)v.findViewById(R.id.tv_machine);
            tv_priority = (TextView)v.findViewById(R.id.tv_priority);
            tv_code = (TextView)v.findViewById(R.id.tv_code);

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
        //TODO: AQUI ES DONDE CARGAS LOS DATOS A LOS ELEMENTOS DE LAS TARJETAS

        holder.tv_breakdown.setText(orders.get(position).getBreakdown().getSubject());
        //Log.d("MACHINEEEEEEEEEEEEEEEEE", orders.get(position).getBreakdown().getMachine().toString());
        holder.tv_machine.setText((CharSequence) orders.get(position).getBreakdown().getMachine().getId());
        //TODO: PRIORITY
        if(orders.get(position).getSeverity()==0){
            holder.tv_priority.setText("Low");
        }else if(orders.get(position).getSeverity()==1){
            holder.tv_priority.setText("Medium");
        }else if(orders.get(position).getSeverity()==2){
            holder.tv_priority.setText("High");
        }

        holder.tv_code.setText("OT-" + orders.get(position).getBreakdown().getId());
    }

    @Override
    public int getItemCount() {

        //TODO: UN COUNT DEL NUMERO DE WORK ORDERS QUE HAY EN LA BD

        return orders.size();
    }



}
