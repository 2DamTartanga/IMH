package com.tartanga.dam.imhandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.fragments.fragment_ZoneTotal;
import com.tartanga.dam.imhandroid.fragments.fragmento_Zonas;

public class activity_fragmentos_cartas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentos_cartas);

        getSupportFragmentManager().beginTransaction().add(R.id.layoutFragmento, fragment_ZoneTotal.newInstance()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.layoutFragmento, fragmento_Zonas.newInstance()).commit();
    }
}