package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tartanga.dam.imhandroid.R;

public class SendWorkOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_repair);
    }

    public void onClickOK(View v) {
        //TODO ENVIAR A LA BASE DE DATOS
    }

    public void onClickCancel(View v) {
    }
}
