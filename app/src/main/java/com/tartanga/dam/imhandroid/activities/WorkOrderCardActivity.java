package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.adaptadores.WorkOrderAdapter;
import com.tartanga.dam.imhandroid.fragments.WorkOrderFragment;
import com.tartanga.dam.imhandroid.fragments.fragmento_Zonas;
import com.tartanga.dam.imhandroid.model.Breakdown;
import com.tartanga.dam.imhandroid.model.WorkOrder;

import java.util.List;

public class WorkOrderCardActivity extends AppCompatActivity {

    List orders;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_order);

        /*

        public TextView tv_breakdown;
        public TextView tv_machine;
        public TextView tv_priority;
        public TextView tv_code;

        private static final long serialVersionUID = 1L;
        private int severity;
        private String others;
        private String typeOfMaintenance;
        private Date creationDate;
        private ArrayList<Repair> repairs;
        private Breakdown breakdown;

         */

        recycler = (RecyclerView) findViewById(R.id.container);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new WorkOrderAdapter(orders);
        recycler.setAdapter(adapter);
    }

    public void onClickWorkOrder(View v) {
        Intent i = new Intent(this, DetailsWorkOrderActivity.class);
        i.putExtra("Instruct", false);
        startActivity(i);
    }
}
