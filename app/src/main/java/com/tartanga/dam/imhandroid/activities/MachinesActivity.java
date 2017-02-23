package com.tartanga.dam.imhandroid.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.fragments.ConnectionLostFragment;
import com.tartanga.dam.imhandroid.fragments.MachineFragment;
import com.tartanga.dam.imhandroid.fragments.fragment_ZoneTotal;
import com.tartanga.dam.imhandroid.fragments.fragmento_Zonas;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.model.Machine;
import com.tartanga.dam.imhandroid.model.Message;
import com.tartanga.dam.imhandroid.model.Section;

import java.util.HashMap;

public class MachinesActivity extends AppCompatActivity implements MessageListener {

    Section section;
    private ImageButton btn_working;
    private ImageButton btn_half_working;
    private ImageButton btn_not_working;
    private HashMap<Character, Boolean> filter = new HashMap<>();

    private boolean workClicked=false;
    private boolean halfWorkClicked=false;
    private boolean notWorkClicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machines);

        filter.put('V',true);
        filter.put('A',true);
        filter.put('R',true);
        section = ((Section) getIntent().getExtras().getSerializable("zone"));
        String type  = getIntent().getExtras().getString("type");
        for (int j = 0; j<section.getMachines().size(); j++){
            if(!section.getMachines().get(j).getMachineFamilly().equals(type)){
                Log.d("section","ELIMINADO " + section.getMachines().get(j).getMachineFamilly());
                section.getMachines().remove(j);
                j--;
            }else{
                Log.d("section","NO ELIMINADO" + section.getMachines().get(j).getMachineFamilly());
            }
        }
        //BOTONES FRAGMENTO
        /*btn_working = (ImageButton) findViewById(R.id.btn_working);
        btn_half_working = (ImageButton) findViewById(R.id.btn_half_working);
        btn_not_working = (ImageButton) findViewById(R.id.btn_not_woking);*/

        //BOTONES ACTIVIDAD
        btn_working = (ImageButton) findViewById(R.id.btnWorking);
        btn_half_working = (ImageButton) findViewById(R.id.btnHalfWorking);
        btn_not_working = (ImageButton) findViewById(R.id.btnNotWorking);

        loadUi();

    }

    //MACHINE
    public void onClickMachine(View v) {
        TextView txt = (TextView) v.findViewById(R.id.tv_machine_name);
        Intent i = new Intent(this, MachineDetails.class);
        i.putExtra("codigo", txt.getText());
        startActivity(i);
    }

    //FILTROS
    //TODO: ONCLICK BOTONES DE LA ACTIVIDAD
    public void onClickWork (View v) {
        //Toast.makeText(this,"WORKING", Toast.LENGTH_SHORT).show();
        if (!workClicked) {
               // btn_working.setBackgroundResource(R.color.colorGray);
                //btn_working.getBackground().setAlpha(95);
            btn_working.setImageResource(R.drawable.ic_working_disabled);

            workClicked=true;

        } else {
                //btn_working.setBackgroundResource(R.color.colorWhite);
            btn_working.setImageResource(R.drawable.ic_working);
            workClicked=false;
        }
        refreshFilter();
    }

    private void refreshFilter() {
        filter.put('V',!workClicked);
        filter.put('A',!halfWorkClicked);
        filter.put('R',!notWorkClicked);

        loadUi();
    }

    public void onClickHalfWork (View v) {
        //Toast.makeText(this,"WORKING", Toast.LENGTH_SHORT).show();
        if (!halfWorkClicked) {

            btn_half_working.setImageResource(R.drawable.ic_half_working_disabled);
                //btn_half_working.setBackgroundResource(R.color.colorGray);
                //btn_half_working.getBackground().setAlpha(95);


            halfWorkClicked=true;
        } else {
            btn_half_working.setImageResource(R.drawable.ic_half_working);
                //btn_half_working.setBackgroundResource(R.color.colorWhite);
            halfWorkClicked=false;
        }
        refreshFilter();
    }

    public void onClickNotWork (View v) {
        //Toast.makeText(this,"WORKING", Toast.LENGTH_SHORT).show();
        if (!notWorkClicked) {
            btn_not_working.setImageResource(R.drawable.ic_not_working_disabled);
                //btn_not_working.setBackgroundResource(R.color.colorGray);
                //btn_not_working.getBackground().setAlpha(95);

            notWorkClicked=true;
        } else {
            btn_not_working.setImageResource(R.drawable.ic_not_working);
               // btn_not_working.setBackgroundResource(R.color.colorWhite);
            notWorkClicked=false;
        }
        refreshFilter();
    }

    //TODO: ONCLICK BOTONES DEL FRAGMENTO
    public void onClickWorking(View v){
        Toast.makeText(this,"WORKING", Toast.LENGTH_LONG).show();
        btn_working.setBackgroundResource(R.drawable.ic_working_disabled);
    }

    public void onClickHalf(View v){
        Toast.makeText(this,"HALF WORKING", Toast.LENGTH_LONG).show();
    }

    public void onClickNot(View v){
        Toast.makeText(this,"NOT WORKING", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void messageReceived(Object obj) {
        if(obj.toString().equals("Connection with server lost")){
            //Toast.makeText(this, getApplicationContext().getString(R.string.connection_lost), Toast.LENGTH_LONG).show();
            DialogFragment newFragment = new ConnectionLostFragment();
            newFragment.show(getFragmentManager(), "Error");
        }
    }

    private void loadUi() {
        LinearLayout ll = (LinearLayout)(findViewById(R.id.scroll_view)).findViewById(R.id.layout);
        ll.removeAllViews();
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        for (Machine m: section.getMachines()){

            if(filter.get(Character.toUpperCase(m.getStatus()))){
                Log.d("section","MOSTRADO" + m.getMachineFamilly() );
                MachineFragment mf = MachineFragment.newInstance(m.getId(), m.getStatus());
                ft.add(ll.getId(), mf);
            }
        }



        ft.commit();
    }
}
