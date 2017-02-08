package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.adaptadores.WorkZonesAdapter;
import com.tartanga.dam.imhandroid.fragments.fragment_ZoneTotal;

import java.util.List;

public class WorkZonesActivity extends AppCompatActivity{

    List zones;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_zones);

        getSupportFragmentManager().beginTransaction().add(R.id.layoutFragmento1, fragment_ZoneTotal.newInstance()).commit();

        recycler = (RecyclerView) findViewById(R.id.cardList);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new WorkZonesAdapter(zones);
        recycler.setAdapter(adapter);

    }

    public void onClickZone(View v) {
        TextView txt = (TextView) v.findViewById(R.id.tv_work_zone);
        Intent i = new Intent(this, TypeMachineActivity.class);
        i.putExtra("zone", txt.getText());
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
