package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnZonas, btnOT, btnInstructions, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        versionAdapter();

        btnZonas = (Button) findViewById(R.id.btnZones);
        btnZonas.setOnClickListener(this);
        btnOT = (Button) findViewById(R.id.btnOT);
        btnOT.setOnClickListener(this);
        btnInstructions = (Button) findViewById(R.id.btnInstructions);
        btnInstructions.setOnClickListener(this);
        btnSettings = (Button) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(this);

        int i = Build.VERSION.SDK_INT;
        Toast.makeText(this, String.valueOf(i), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "PULSADO NUMERO 1", Toast.LENGTH_LONG).show();
        switch (view.getId()) {
            case R.id.btnZones:
                Intent zonas = new Intent(this, WorkZonesActivity.class);
                startActivity(zonas);
                break;
            case R.id.btnOT:
                Intent ot = new Intent(this, WorkOrderCardActivity.class);
                startActivity(ot);
                break;
            case R.id.btnInstructions:
                Intent inst = new Intent(this, InstructionsActivity.class);
                startActivity(inst);
                break;
            case R.id.btnSettings:
<<<<<<< HEAD
                    Intent settings = new Intent(this, SettingsActivity.class);
                    startActivity(settings);
=======
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
            case R.id.btnLogout:
                //TODO: MÉTODO BD PARA LOGOUT
>>>>>>> 22531a20ec043c6765b057b40b8a83d3a65f2636
                break;
        }
    }

    private void versionAdapter() {
        if (android.os.Build.VERSION.SDK_INT > 19) {
            setContentView(R.layout.content_navigation);

        }
        else {
            setContentView(R.layout.content_navigation_older_versions);

        }
    }
}

