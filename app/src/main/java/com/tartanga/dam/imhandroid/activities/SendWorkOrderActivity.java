package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.manager.VersionController;
import com.tartanga.dam.imhandroid.model.Breakdown;
import com.tartanga.dam.imhandroid.model.GlobalUser;
import com.tartanga.dam.imhandroid.model.Localization;
import com.tartanga.dam.imhandroid.model.Message;
import com.tartanga.dam.imhandroid.model.Repair;
import com.tartanga.dam.imhandroid.model.WorkOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class SendWorkOrderActivity extends AppCompatActivity implements MessageListener, AdapterView.OnItemSelectedListener {

    private EditText et_time_spent;
    private Spinner spn_failure_localization;
    private EditText et_replacements;
    private Spinner spn_tools;
    private EditText et_repair_process;
    private Switch sw_failure_repaired;
    private Switch sw_add_instructions;
    private TextView work;
    private Spinner spn_Availability;
    private TextView textViewTools;
    private VersionController vControl = new VersionController();
    private boolean repairDate;
    private String formattedDate;
    private Date date;
    private HashMap<Integer,String> tools = new HashMap<>();
    private HashMap<Integer,String> tools2 = new HashMap<>();
    private ArrayList<Localization> listLoc = new ArrayList<Localization>();
    private int count=0;
    private WorkOrder workOrder;
    private Boolean recogido = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        workOrder = (WorkOrder) getIntent().getSerializableExtra(("Work"));

        if(vControl.olderVersions())
            setContentView(R.layout.frame_repair_older_versions);
        else
            setContentView(R.layout.frame_repair);

        work = (TextView) findViewById(R.id.tv_work_order);
        et_time_spent = (EditText) findViewById(R.id.et_time_spent);
        spn_failure_localization = (Spinner) findViewById(R.id.spn_failure_localization);
        et_replacements = (EditText) findViewById(R.id.et_replacements);
        spn_tools = (Spinner) findViewById(R.id.spn_tools);
        et_repair_process = (EditText) findViewById(R.id.et_repair_process);
        sw_failure_repaired = (Switch) findViewById(R.id.sw_failure_repaired);

        spn_Availability = (Spinner) findViewById(R.id.spn_availability);
        textViewTools = (TextView) findViewById(R.id.textView2);

        spn_tools.setOnItemSelectedListener(this);
        work.setText("OT-" + workOrder.getBreakdown().getId());

        String toolsUser="";
        int i = 0;

        ThreadSender ts = new ThreadSender(this, new Message(Message.GET, Message.TOOLS, null));
        ts.execute();
        if(workOrder.getRepairs()!=null){
            if(workOrder.getRepairs().getTools()!=null){
                workOrder.getRepairs().getTools();
                String[] toolsString2 = new String[workOrder.getRepairs().getTools().size()];
                for (Map.Entry<Integer, String> tool : workOrder.getRepairs().getTools().entrySet()) {
                    toolsString2[i] = tool.getValue();
                    if(i==0)
                        toolsUser = toolsString2[i];
                    else
                        toolsUser = toolsUser + ", " + toolsString2[i];
                    textViewTools.setText(toolsUser);
                    tools2.putAll(workOrder.getRepairs().getTools());
                    i++;
                }
            }
        }
        ThreadSender ts1 = new ThreadSender(this, new Message(Message.GET, Message.LOCALIZATION,null));
        ts1.execute();


        repairDate = false;

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.failure_localization, android.R.layout.simple_spinner_item);

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

        adapterAval.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_Availability.setAdapter(adapterAval);


    }

    public void onSetRepairDate(View v) {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formattedDate = df.format(c.getTime());

        date = c.getTime();

        repairDate = true;

        Toast.makeText(this, "Date set to: " + formattedDate, Toast.LENGTH_SHORT).show();
    }

    public void onClickClear(View v) {
        if(!et_time_spent.getText().toString().isEmpty() || !et_replacements.getText().toString().isEmpty() || !et_repair_process.getText().toString().isEmpty() ) {
            et_time_spent.setText("");
            spn_failure_localization.setSelection(0);
            et_replacements.setText("");
            spn_tools.setSelection(0);
            et_repair_process.setText("");
            sw_failure_repaired.setChecked(false);
            spn_Availability.setSelection(0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void messageReceived(Object obj) {
        if(obj instanceof ArrayList){
            listLoc = (ArrayList<Localization>) obj;
            for(int i = 0; i<listLoc.size();i++){
                Log.d("MENSAJE", listLoc.get(i).toString());
            }
            ArrayAdapter adapter5 = new ArrayAdapter<Localization>(this,android.R.layout.select_dialog_item, listLoc) {

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
            adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn_failure_localization.setAdapter(adapter5);
        }else{
            if(!recogido){
                if (obj != null) {
                    tools.putAll((HashMap<Integer, String>) obj);
                    Log.d("TOOLS", tools.size() + "");

                    String[] toolsString = new String[tools.size()+1];

                    int i = 0;
                    toolsString[0] = "Choose";
                    for (Map.Entry<Integer, String> tool : tools.entrySet()) {
                        toolsString[i+1] = tool.getValue();
                        i++;
                    }

                    ArrayAdapter<String> adapterTools = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, toolsString);
                    adapterTools.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spn_tools.setAdapter(adapterTools);
                    recogido = true;
                }
            }
        }
    }


    public void onClickUndo(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Boolean existe = false;
        if(!spn_tools.getSelectedItem().equals("Choose")){
            for (Map.Entry<Integer,String> t1: tools2.entrySet()) {
                if (t1.getValue().equals(spn_tools.getSelectedItem().toString())) {
                    existe = true;
                }
            }
            if(!existe){
                if(textViewTools.getText().toString().isEmpty()){
                    textViewTools.setText((String)spn_tools.getSelectedItem());
                } else {
                    textViewTools.setText(textViewTools.getText().toString() + ", " + spn_tools.getSelectedItem());
                }
                for (Map.Entry<Integer, String> t: tools.entrySet()){
                    if(t.getValue().equals(spn_tools.getSelectedItem().toString())){
                        Log.d("MENSAJE", "ENTRA AL SPINNER");
                        tools2.put(t.getKey(), spn_tools.getSelectedItem().toString());
                        break;
                    }
                }
            }else{
                Toast.makeText(this, "This tool already exist", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onClickOK(View v){
        Log.d("MENSAJE", "OK PULSADO");
        if(repairDate &&
                !et_time_spent.getText().toString().isEmpty() &&
                !(spn_Availability.getSelectedItemPosition() == 0) &&
                !et_repair_process.getText().toString().isEmpty() ) {

            Repair r = new Repair();
            Date fecha = new Date();
            r.setAsignationDate(workOrder.getRepairs().getAsignationDate());
            r.setDate(fecha);
            float time = Float.parseFloat(et_time_spent.getText().toString());
            r.setTime(time);
            int aval = spn_Availability.getSelectedItemPosition();
            String failure ="";
            switch (aval){
                case 1:
                    failure = "V";
                    break;
                case 2:
                    failure = "R";
                    break;
                case 3:
                    failure = "A";
                    break;
            }
            r.setAvailabilityAfterRepair(failure);
            r.setTools(tools2);
            String repair = et_repair_process.getText().toString();
            r.setRepairProcess(repair);
            Boolean isR = sw_failure_repaired.isChecked();
            r.setRepaired(isR);
            String repla = et_replacements.getText().toString();
            r.setReplacements(repla);
            r.setGroup(GlobalUser.getGlobalUser().getGroup());
            int loc = spn_failure_localization.getSelectedItemPosition();
            Localization lo = new Localization();
            for(int i = 0;i<listLoc.size();i++){
                if(i==loc){
                    lo = listLoc.get(i);
                    break;
                }
            }
            r.setFailureLocalization(lo);
            workOrder.setRepair(r);
            ThreadSender ts = new ThreadSender(this, new Message(Message.ADD,Message.REPAIR, workOrder));
            ts.execute();
            Toast.makeText(this, "Repair report sent", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this,MenuActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Please, fill in all fields", Toast.LENGTH_LONG).show();
        }

    }
}
