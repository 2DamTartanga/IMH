package com.tartanga.dam.imhandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.fragments.fragment_ZoneTotal;
import com.tartanga.dam.imhandroid.fragments.fragmento_Zonas;

public class activity_fragmentos_cartas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentos_cartas);

        getSupportFragmentManager().beginTransaction().add(R.id.layoutFragmento1, fragment_ZoneTotal.newInstance()).commit();
<<<<<<< HEAD
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        /*String pam1 = "11";
=======
/*
        String pam1 = "11";
>>>>>>> 858ce6cdd3b24631f5245fd6e90c69a837205343
        String pam2= "11";
        String pam3="11";
        for(int i=0;i<2;i++){
            getSupportFragmentManager().beginTransaction().add(R.id.list, fragmento_Zonas.newInstance(pam1,pam2,pam3)).commit();
            pam1 = "12";
            pam2="12";
            pam3="12";
        }*/
    }
}