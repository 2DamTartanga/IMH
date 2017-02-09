package com.tartanga.dam.imhandroid.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;

import org.w3c.dom.Text;

public class InstruccionesActivity extends AppCompatActivity {

    TextView instruc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);

        instruc = (TextView) findViewById(R.id.tv_Instruccion);
        instruc.setText(getIntent().getStringExtra("Texto"));
    }
}
