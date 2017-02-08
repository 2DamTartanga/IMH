package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.adaptadores.WorkOrderAdapter;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.manager.VersionController;
import com.tartanga.dam.imhandroid.model.Breakdown;
import com.tartanga.dam.imhandroid.model.GlobalUser;
import com.tartanga.dam.imhandroid.model.Message;
import com.tartanga.dam.imhandroid.model.Repair;
import com.tartanga.dam.imhandroid.model.WorkOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class SendWorkOrderActivity extends AppCompatActivity implements MessageListener{

    private EditText et_time_spent;
    private Spinner spn_failure_localization;
    private EditText et_replacements;
    private Spinner spn_tools;
    private EditText et_repair_process;
    private Switch sw_failure_repaired;
    private Switch sw_add_instructions;
    private Spinner spn_Availability;
    private VersionController vControl = new VersionController();
    private boolean repairDate;
    private String formattedDate;
    private Date date;
    private HashMap<Integer,String> tools = new HashMap<>();
    private WorkOrder workOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Breakdown breakdown = (Breakdown) getIntent().getSerializableExtra(("Breakdown");
        workOrder = new WorkOrder(breakdown, 0, null, null, null);

        if(vControl.olderVersions())
            setContentView(R.layout.frame_repair_older_versions);
        else
            setContentView(R.layout.frame_repair);

        et_time_spent = (EditText) findViewById(R.id.et_time_spent);
        spn_failure_localization = (Spinner) findViewById(R.id.spn_failure_localization);
        et_replacements = (EditText) findViewById(R.id.et_replacements);
        spn_tools = (Spinner) findViewById(R.id.spn_tools);
        et_repair_process = (EditText) findViewById(R.id.et_repair_process);
        sw_failure_repaired = (Switch) findViewById(R.id.sw_failure_repaired);
        sw_add_instructions = (Switch) findViewById(R.id.sw_add_instructions);
        spn_Availability = (Spinner) findViewById(R.id.spn_availability);

        repairDate = false;

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

        ThreadSender ts = new ThreadSender(this, new Message(Message.GET, Message.TOOLS, null));
        ts.execute();

        ThreadSender ts2 = new ThreadSender(this, new Message(Message.GET, Message.TOOLS, workOrder));
        ts2.execute();

    }

    public void onSetRepairDate(View v) {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formattedDate = df.format(c.getTime());

        date = c.getTime();

        repairDate = true;

        Toast.makeText(this, "Date set to: " + formattedDate, Toast.LENGTH_SHORT).show();
    }

    public void onClickOK(View v) {
        //TODO ENVIAR A LA BASE DE DATOS
        if(repairDate &&
                !et_time_spent.getText().toString().isEmpty() &&
                !(spn_failure_localization.getSelectedItemPosition() == 0) &&
                !(spn_Availability.getSelectedItemPosition() == 0) &&
                !et_replacements.getText().toString().isEmpty() &&
                !(spn_tools.getSelectedItemPosition()==0) &&
                !et_repair_process.getText().toString().isEmpty() ) {

            Repair r = new Repair();
            r.setDate(date);
            r.setFailureLocalization(spn_failure_localization.getSelectedItemPosition());
            r.setAvailabilityAfterRepair(spn_Availability.getSelectedItem().toString());
            r.setReplacements(et_replacements.getText().toString());
            //TODO: SPINNER TOOLS
            r.setRepairProcess(et_repair_process.getText().toString());
            if(sw_failure_repaired.isChecked())
                r.setRepaired(true);
            else
                r.setRepaired(false);

        } else {
            Toast.makeText(this, "Please, fill in all fields", Toast.LENGTH_LONG).show();
        }

    }

    public void onClickClear(View v) {
        if(!et_time_spent.getText().toString().isEmpty() || !et_replacements.getText().toString().isEmpty() || !et_repair_process.getText().toString().isEmpty() ) {
            et_time_spent.setText("");
            spn_failure_localization.setSelection(0);
            et_replacements.setText("");
            spn_tools.setSelection(0);
            et_repair_process.setText("");
            sw_failure_repaired.setChecked(false);
            sw_add_instructions.setChecked(false);
            spn_Availability.setSelection(0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void messageReceived(Object obj) {
        tools.putAll((HashMap<Integer, String>) obj);
        Log.d("TOOLS",tools.size()+"");
        String[] toolsString = new String[tools.size()];

        int i = 0;

        for (Map.Entry<Integer, String> tool: tools.entrySet()) {
            toolsString[i] = tool.getValue();
            Log.d("TOOLSSIZE",tool.getValue()+"");
            i++;
        }

        ArrayAdapter<String> adapterTools = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, toolsString);
        adapterTools.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_tools.setAdapter(adapterTools);
    }

    public void onClickUndo(View v) {

    }

    /*
    @Override
    public void messageReceived(Object obj) {
        Log.d("ArrayList de Ots", String.valueOf(obj));
        ArrayList<WorkOrder> obj2= ((ArrayList<WorkOrder>) obj);
        Log.d("ArrayList de Ots", obj2.size()+"");
        orders=obj2;

        adapter = new WorkOrderAdapter(orders);
        recycler.setAdapter(adapter);

    }*/
}
