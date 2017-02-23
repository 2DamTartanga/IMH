package com.tartanga.dam.imhandroid.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.fragments.ConnectionLostFragment;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.manager.VersionController;
import com.tartanga.dam.imhandroid.model.Breakdown;
import com.tartanga.dam.imhandroid.model.GlobalUser;
import com.tartanga.dam.imhandroid.model.Machine;
import com.tartanga.dam.imhandroid.model.Message;

public class ActivityReport extends AppCompatActivity implements MessageListener {

    TextView tMachineCode;
    Spinner sFailureType;
    Spinner sEquipmentAvailable;
    EditText eSubject;
    EditText eDescription;
    Button btn;
    String machineCode = "";
    private VersionController vControl = new VersionController();
    private boolean connectionLost=false;

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
        machineCode = getIntent().getExtras().getString("id");
        tMachineCode.setText(getString(R.string.maintenance_request_machine,machineCode));

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
        String subject = eSubject.getText().toString();
        String description = eDescription.getText().toString();
        int equipmentAvailable = sEquipmentAvailable.getSelectedItemPosition();
        int failureTypePosition = sFailureType.getSelectedItemPosition();
        boolean ok =  (!subject.isEmpty() && equipmentAvailable != 0 && sFailureType.getSelectedItemPosition() != 0);
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
                ok = false;
                break;
        }

        String failureType = "";
        switch (failureTypePosition){
            case 1:
                failureType = "Electrical";
                break;
            case 2:
                failureType = "Mechanical";
                break;
            case 3:
                failureType = "Security";
                break;
            case 4:
                failureType = "Others";
                break;
            default:
                ok = false;
                break;
        }

        Breakdown br = new Breakdown(0,new java.util.Date(), GlobalUser.getGlobalUser(),failureType,subject,description,m,failure);
        if(ok) {
            ThreadSender ts = new ThreadSender(this, new Message(Message.ADD, Message.BREAKDOWN, br));
            ts.execute();
            if (!connectionLost) {
                Toast.makeText(this, "BREAKDOWN CREATED", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, MenuActivity.class);
                startActivity(i);
                this.finish();
            }
        }else{
            /*StyleableToast t = new StyleableToast(this, "FILL EMPTY FIELDS", Toast.LENGTH_SHORT);
            t.setBackgroundColor(Color.parseColor("#ff5a5f"));
            t.setTextColor(Color.WHITE);
            t.setIcon(R.drawable.ic_alert_login);
            t.setMaxAlpha();
            t.show();*/
            Toast.makeText(this,"FILL EMPTY FIELDS",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void messageReceived(Object obj) {
        if(obj.toString().equals("Connection with server lost")){
            //Toast.makeText(this, getApplicationContext().getString(R.string.connection_lost), Toast.LENGTH_LONG).show();
            //Log.d("MENSAJE","ERROR CONEXION");
            connectionLost=true;
            DialogFragment newFragment = new ConnectionLostFragment();
            newFragment.show(getFragmentManager(), "Error");
        }
    }
}
