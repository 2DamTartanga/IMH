package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.fragments.fragment_ZoneTotal;
import com.tartanga.dam.imhandroid.fragments.fragmento_Zonas;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.model.Machine;
import com.tartanga.dam.imhandroid.model.Message;
import com.tartanga.dam.imhandroid.model.Section;

import java.util.ArrayList;

public class WorkZonesActivity extends AppCompatActivity implements MessageListener{

    ArrayList<Section> sections = null;
    int[] status;
    boolean isEmpty = true;
    float total = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_zones);
        ThreadSender ts = new ThreadSender(this,new Message(Message.GET, Message.WORK_ZONE, null));
        ts.execute();
        ts = new ThreadSender(this,new Message(Message.GET, Message.PERCENT, null));
        ts.execute();
        //TODO implementar
    }

    public void onClickZone(View v) {
        TextView txt = (TextView) v.findViewById(R.id.tv_work_zone);
        Intent i = new Intent(this, TypeMachineActivity.class);
        String secId = null;
        for (Section sec : sections){
            if(sec.getName().equals(txt.getText())){
                secId = sec.getId();
                break;
            }
        }
        i.putExtra("zone", secId);

        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void messageReceived(Object obj) {
        if(obj instanceof ArrayList<?>) {
            if(((ArrayList) obj).get(0) != null) {
                if (((ArrayList) obj).get(0) instanceof Section) {
                    sections = ((ArrayList) obj);
                    status = new int[]{0,0,0};
                    for (Section sec: sections){
                        for(int i=0; i<status.length; i++){
                            status[i] += sec.getStatus()[i];
                        }
                    }
                    if(total != -1){
                        isEmpty = false;
                    }
                } else if (((ArrayList) obj).get(0) instanceof Machine) {

                }
            }
        }else if(obj instanceof Float){
            calculateTotal((float) obj);
            if(sections != null){
                isEmpty = false;
            }
        }
        if(!isEmpty) init();
    }

    private void init() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.activity_fragmentos_cartas);
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        float total = this.total;
        fragment_ZoneTotal fZT = fragment_ZoneTotal.newInstance(status[0],status[1],status[2],total);
        ft.add(ll.getId(), fZT);
        //Fragment total finished
        for (Section section: sections){
            status = section.getStatus();
            fragmento_Zonas fz = fragmento_Zonas.newInstance(status[0],status[1],status[2], section.getName());
            ft.add(ll.getId(),fz);
        }

        ft.commit();
    }

    private void calculateTotal(float obj) {

        total = obj;
    }
}
