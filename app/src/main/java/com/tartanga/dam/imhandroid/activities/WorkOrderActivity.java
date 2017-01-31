package com.tartanga.dam.imhandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.fragments.WorkOrderFragment;
import com.tartanga.dam.imhandroid.fragments.fragmento_Zonas;

public class WorkOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_order);

        getSupportFragmentManager().beginTransaction().add(R.id.layoutFragmento, WorkOrderFragment.newInstance()).commit();
    }
}
