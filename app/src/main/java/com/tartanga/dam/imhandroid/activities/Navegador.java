package com.tartanga.dam.imhandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;

public class Navegador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


    }

    public void onClickStatus(View v){
        Toast.makeText(this,"PULSADO NUMERO 1", Toast.LENGTH_LONG).show();
    }
}
