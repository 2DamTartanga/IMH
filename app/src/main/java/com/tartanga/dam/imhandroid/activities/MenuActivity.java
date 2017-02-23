package com.tartanga.dam.imhandroid.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.fragments.ConnectionLostFragment;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.VersionController;
import com.tartanga.dam.imhandroid.model.GlobalUser;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener, MessageListener {

    Button btnZonas, btnOT, btnInstructions, btnSettings, btnLogout;
    private VersionController vControl = new VersionController();

    @Override
    public void onBackPressed() {
        //finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(vControl.olderVersions()) {
            setContentView(R.layout.content_navigation_older_versions);
        } else
            setContentView(R.layout.content_navigation);

        //setContentView(vControl.olderVersions() ? (R.layout.content_navigation_older_versions) : (R.layout.content_navigation));

        Intent i = getIntent();
        String nombreUsuario = i.getStringExtra("NombreUsuario");


        //Log.d("Usuario", u.getGlobalUser().getName());
        //Log.d("Usuario", u.getGlobalUser().getName());

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

        if(GlobalUser.getGlobalUser().getGroup()==null){
            btnOT.setEnabled(false);
            btnOT.setVisibility(View.GONE);
            btnInstructions.setEnabled(false);
            btnInstructions.setVisibility(View.GONE);
        }

        //int i = Build.VERSION.SDK_INT;
        //Toast.makeText(this, String.valueOf(i), Toast.LENGTH_LONG).show();
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

    @Override
    public void messageReceived(Object obj) {
        if(obj.toString().equals("Connection with server lost")){
            //Toast.makeText(this, getApplicationContext().getString(R.string.connection_lost), Toast.LENGTH_LONG).show();
            DialogFragment newFragment = new ConnectionLostFragment();
            newFragment.show(getFragmentManager(), "Error");
        }
    }
}
