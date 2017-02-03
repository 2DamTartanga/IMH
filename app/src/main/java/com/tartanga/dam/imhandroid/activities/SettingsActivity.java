package com.tartanga.dam.imhandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tartanga.dam.imhandroid.R;

public class SettingsActivity extends AppCompatActivity {

    private Button btnChangePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnChangePass = (Button)findViewById(R.id.btnChangePass);
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: MÉTODOS BD PARA CAMBIAR CONTRASEÑA
            }
        });
    }
}
