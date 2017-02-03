package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.adaptadores.WorkOrderAdapter;

import java.util.List;

public class InstructionsActivity extends AppCompatActivity {

    List orders;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        recycler = (RecyclerView) findViewById(R.id.container);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new WorkOrderAdapter(orders);
        recycler.setAdapter(adapter);
    }


    public void onClickWorkOrder(View v) {
        Intent i = new Intent(this, DetailsWorkOrderActivity.class);
        i.putExtra("Instruct", true);
        startActivity(i);
    }
}

