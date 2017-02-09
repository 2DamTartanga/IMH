package com.tartanga.dam.imhandroid.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.manager.VersionController;

public class ActivityReport extends AppCompatActivity {

    TextView tMachineCode;
    Spinner sFailureType;
    Spinner sEquipmentAvailable;
    EditText eSubject;
    EditText eDescription;
    Button btn;
    private VersionController vControl = new VersionController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(vControl.olderVersions())
            setContentView(R.layout.maintenance_request_older_versions);
        else
            setContentView(R.layout.maintenance_request);

        tMachineCode = (TextView) findViewById(R.id.tv_machine);
        sFailureType = (Spinner) findViewById(R.id.spn_failure_type);
        sEquipmentAvailable = (Spinner) findViewById(R.id.spn_equipment_available);
        eSubject = (EditText) findViewById(R.id.et_breakdown);
        eDescription = (EditText) findViewById(R.id.et_description);
        btn = (Button) findViewById(R.id.btn_send);

        //SPINNER FAILURE TYPE
        String[] type = getResources().getStringArray(R.array.arr_failure_type);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, type) {
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
        sFailureType.setAdapter(adapter);

        //SPINNER EQUIPMENT AVAILABLE
        String[] avail = getResources().getStringArray(R.array.arr_equipment_available);
        ArrayAdapter adapter1 = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, avail) {
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
        sEquipmentAvailable.setAdapter(adapter1);
    }

    public void onClickSend(View v){
        String machineCode = tMachineCode.getText().toString();
        String failureType = sFailureType.toString();
        String equipmentAvailable = sEquipmentAvailable.toString();
        String subject = eSubject.getText().toString();
        String description = eDescription.getText().toString();
        Toast.makeText(this,"ENVIAR DATOS", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
