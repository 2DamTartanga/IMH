package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;

public class SendWorkOrderActivity extends AppCompatActivity {

    private EditText et_time_spent;
    private Spinner spn_failure_localization;
    private EditText et_replacements;
    private EditText et_tools;
    private EditText et_repair_process;
    private Switch sw_failure_repaired;
    private Switch sw_add_instructions;
    private Spinner spn_Availability;

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
        spn_Availability = (Spinner) findViewById(R.id.spn_availability);

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.failure_localization, android.R.layout.simple_spinner_item);

        String[] localizations = getResources().getStringArray(R.array.failure_localization);
        //SPINNER FAILURE LOCALIZATIONS
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, localizations) {
            @Override
            public boolean isEnabled(int position) {
                if(position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_failure_localization.setAdapter(adapter);

        //SPINNER AVAILABILITY AFTER REPAIR
        String[] availability = getResources().getStringArray(R.array.arr_equipment_available);

        ArrayAdapter adapterAval = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, availability) {
            @Override
            public boolean isEnabled(int position) {
                if(position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_Availability.setAdapter(adapterAval);
    }

    public void onClickOK(View v) {
        //TODO ENVIAR A LA BASE DE DATOS
    }

    public void onClickClear(View v) {
        if(!et_time_spent.getText().toString().isEmpty() || !et_replacements.getText().toString().isEmpty() || !et_tools.getText().toString().isEmpty() || !et_repair_process.getText().toString().isEmpty() ) {
            et_time_spent.setText("");
            spn_failure_localization.setSelection(0);
            et_replacements.setText("");
            et_tools.setText("");
            et_repair_process.setText("");
            sw_failure_repaired.setChecked(false);
            sw_add_instructions.setChecked(false);
            spn_Availability.setSelection(0);
        }
    }
}
