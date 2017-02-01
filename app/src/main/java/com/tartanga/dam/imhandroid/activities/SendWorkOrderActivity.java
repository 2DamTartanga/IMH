package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.tartanga.dam.imhandroid.R;

public class SendWorkOrderActivity extends AppCompatActivity {

    private EditText et_time_spent;
    private Spinner spn_failure_localization;
    private EditText et_replacements;
    private EditText et_tools;
    private EditText et_repair_process;
    private Switch sw_failure_repaired;
    private Switch sw_add_instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_repair);

        et_time_spent = (EditText) findViewById(R.id.et_time_spent);
        spn_failure_localization = (Spinner) findViewById(R.id.spn_failure_localization);
        et_replacements = (EditText) findViewById(R.id.et_replacements);
        et_tools = (EditText) findViewById(R.id.et_tools);
        et_repair_process = (EditText) findViewById(R.id.et_repair_process);
        sw_failure_repaired = (Switch) findViewById(R.id.sw_failure_repaired);
        sw_add_instructions = (Switch) findViewById(R.id.sw_add_instructions);
    }

    public void onClickOK(View v) {
        //TODO ENVIAR A LA BASE DE DATOS
    }

    public void onClickClear(View v) {
        et_time_spent.setText("");
        spn_failure_localization.setSelection(0);
        et_replacements.setText("");
        et_tools.setText("");
        et_repair_process.setText("");
        sw_failure_repaired.setChecked(false);
        sw_add_instructions.setChecked(false);
    }
}
