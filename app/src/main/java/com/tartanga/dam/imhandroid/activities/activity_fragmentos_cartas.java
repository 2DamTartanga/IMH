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

        getSupportFragmentManager().beginTransaction().add(R.id.layoutFragmento1, fragment_ZoneTotal.newInstance()).commit();

        String pam1 = "11";
        String pam2= "11";
        String pam3="11";
        for(int i=0;i<2;i++){
            getSupportFragmentManager().beginTransaction().add(R.id.list, fragmento_Zonas.newInstance(pam1,pam2,pam3)).commit();
            pam1 = "12";
            pam2="12";
            pam3="12";
        }
    }
}