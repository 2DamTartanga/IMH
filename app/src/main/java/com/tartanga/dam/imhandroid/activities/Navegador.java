package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.fragments.fragment_ZoneTotal;

public class Navegador extends AppCompatActivity implements View.OnClickListener{

    Button btnZonas, btnOT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_navigation);


        btnZonas = (Button) findViewById(R.id.btnZones);
        btnZonas.setOnClickListener(this);
        btnOT = (Button) findViewById(R.id.btnOT);
        btnOT.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,"PULSADO NUMERO 1", Toast.LENGTH_LONG).show();

        switch (view.getId()) {
            case R.id.btnZones:
                    Intent zonas = new Intent(this, WorkZonesActivity.class);
                    startActivity(zonas);
                break;
            case R.id.btnOT:
                    Intent ot = new Intent(this, WorkOrderActivity.class);
                    startActivity(ot);
                break;

        }
    }
}
