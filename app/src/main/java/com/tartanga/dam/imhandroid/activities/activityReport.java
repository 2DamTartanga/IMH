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
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.manager.VersionController;
import com.tartanga.dam.imhandroid.model.Breakdown;
import com.tartanga.dam.imhandroid.model.GlobalUser;
import com.tartanga.dam.imhandroid.model.Machine;
import com.tartanga.dam.imhandroid.model.Message;

import java.sql.Date;

public class ActivityReport extends AppCompatActivity implements MessageListener {

    TextView tMachineCode;
    Spinner sFailureType;
    Spinner sEquipmentAvailable;
    EditText eSubject;
    EditText eDescription;
    String machineCode;
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

        machineCode = getIntent().getExtras().getString("id");
        tMachineCode.setText(getString(R.string.maintenance_request_machine,machineCode));
    }

    public void onClickSend(View v){
        String subject = eSubject.getText().toString();
        String description = eDescription.getText().toString();
        int equipmentAvailable = sEquipmentAvailable.getSelectedItemPosition();
        String failureType = sFailureType.getSelectedItem().toString();

        Machine m = new Machine(machineCode);
        String failure = "s";
        switch (equipmentAvailable){
            case 1:
                failure = "V";
                break;
            case 2:
                failure = "R";
                break;
            case 3:
                failure = "A";
                break;
            default:
                failure = String.valueOf(equipmentAvailable);
                break;
        }
        Breakdown br = new Breakdown(0,new java.util.Date(), GlobalUser.getGlobalUser(),failureType,subject,description,m,failure);
        Toast.makeText(this,"ENVIAR DATOS", Toast.LENGTH_LONG).show();
        ThreadSender ts = new ThreadSender(this,new Message(Message.ADD, Message.BREAKDOWN, br));
        ts.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void messageReceived(Object obj) {

    }
}
