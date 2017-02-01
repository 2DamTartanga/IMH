package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.adaptadores.WorkOrderAdapter;
import com.tartanga.dam.imhandroid.fragments.FragmentAddReport;

import java.util.List;

public class MachineDetails extends AppCompatActivity {

    List orders;
    TextView txt;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_details);

        getSupportFragmentManager().beginTransaction().add(R.id.container2, FragmentAddReport.newInstance()).commit();

        recycler = (RecyclerView) findViewById(R.id.container);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new WorkOrderAdapter(orders);
        recycler.setAdapter(adapter);

    }

    public void onClickAddRequest(View v){
        Intent i = new Intent(this, ActivityReport.class);
        //AÑADIR PUTEXTRA CON EL DATO DEL INTENT ANTERIOR
        startActivity(i);
    }
}
