package com.tartanga.dam.imhandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;

public class ActivityReport extends AppCompatActivity {

    TextView tMachineCode;
    Spinner sFailureType;
    Spinner sEquipmentAvailable;
    EditText eSubject;
    EditText eDescription;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance_request);

        tMachineCode = (TextView) findViewById(R.id.tv_machine);
        sFailureType = (Spinner) findViewById(R.id.spn_failure_type);
        sEquipmentAvailable = (Spinner) findViewById(R.id.spn_equipment_available);
        eSubject = (EditText) findViewById(R.id.et_breakdown);
        eDescription = (EditText) findViewById(R.id.et_description);
        btn = (Button) findViewById(R.id.btn_send);
    }

    public void onClickSend(View v){
        String machineCode = tMachineCode.getText().toString();
        String failureType = sFailureType.toString();
        String equipmentAvailable = sEquipmentAvailable.toString();
        String subject = eSubject.getText().toString();
        String description = eDescription.getText().toString();
        Toast.makeText(this,"ENVIAR DATOS", Toast.LENGTH_LONG).show();
    }
}
