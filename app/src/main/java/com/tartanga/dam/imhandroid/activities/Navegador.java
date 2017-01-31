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
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_navigation);


        btn = (Button) findViewById(R.id.btnZones);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,"PULSADO NUMERO 1", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, activity_fragmentos_cartas.class);
        startActivity(i);
    }
}
