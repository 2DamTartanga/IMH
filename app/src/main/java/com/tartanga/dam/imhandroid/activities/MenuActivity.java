package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.manager.VersionController;
import com.tartanga.dam.imhandroid.model.GlobalUser;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnZonas, btnOT, btnInstructions, btnSettings, btnLogout;
    private VersionController vControl = new VersionController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(vControl.olderVersions() ? (R.layout.content_navigation_older_versions) : (R.layout.content_navigation));

        Intent i = getIntent();

        btnZonas = (Button) findViewById(R.id.btnZones);
        btnZonas.setOnClickListener(this);
        btnOT = (Button) findViewById(R.id.btnOT);
        btnOT.setOnClickListener(this);
        btnInstructions = (Button) findViewById(R.id.btnInstructions);
        btnInstructions.setOnClickListener(this);
        btnSettings = (Button) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(this);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);

        if(GlobalUser.getGlobalUser().getGroup() == null){
            btnOT.setVisibility(View.GONE);
            btnInstructions.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
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
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
            case R.id.btnLogout:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
