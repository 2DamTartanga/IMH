package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.adaptadores.MachineAdapter;
import com.tartanga.dam.imhandroid.adaptadores.WorkOrderAdapter;
import com.tartanga.dam.imhandroid.fragments.fragment_Filtro;

import java.util.List;

public class MachinesActivity extends AppCompatActivity {

    List machines;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machines);

        recycler = (RecyclerView) findViewById(R.id.machineList);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new MachineAdapter(machines);
        recycler.setAdapter(adapter);

        getSupportFragmentManager().beginTransaction().add(R.id.layoutFiltro, fragment_Filtro.newInstance()).commit();
    }

    //MACHINE
    public void onClickMachine(View v){
        TextView txt = (TextView) v.findViewById(R.id.tv_machine_name);
        Intent i = new Intent(this, machine_Details.class);
        i.putExtra("codigo", txt.getText());
        startActivity(i);
    }

    public void onClickDetails(View v) {
        Toast.makeText(this,"Details", Toast.LENGTH_LONG).show();
    }

    //FILTROS

    //TODO
    public void onClickWorking(View v){
        Toast.makeText(this,"WORKING", Toast.LENGTH_LONG).show();

    }

    public void onClickHalf(View v){
        Toast.makeText(this,"HALF WORKING", Toast.LENGTH_LONG).show();
    }

    public void onClickNot(View v){
        Toast.makeText(this,"NOT WORKING", Toast.LENGTH_LONG).show();
    }



}
