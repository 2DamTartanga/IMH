package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class WorkZonesActivity extends AppCompatActivity implements MessageListener, View.OnClickListener{

    ArrayList<Section> sections;
    int[] status;
    boolean isEmpty = true;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_zones);
        ThreadSender ts = new ThreadSender(this,new Message(Message.GET, Message.WORK_ZONE, null));
        ts.execute();
        //TODO implementar
    }

    public void onClickZone(View v) {
        TextView txt = (TextView) v.findViewById(R.id.tv_work_zone);
        Intent i = new Intent(this, MachinesActivity.class);
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
                    init();
                } else if (((ArrayList) obj).get(0) instanceof Machine) {

                }
            }
        }
    }

    private void init() {
        isEmpty = false;
        LinearLayout ll = (LinearLayout) findViewById(R.id.activity_fragmentos_cartas);
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        status = new int[]{0,0,0};
        float total = calculateTotal();
        fragment_ZoneTotal fZT = fragment_ZoneTotal.newInstance(status[0],status[2],status[1],total);
        ft.add(ll.getId(), fZT);
        //Fragment total finished
        for (Section section: sections){
            status = section.getStatus();
            fragmento_Zonas fz = fragmento_Zonas.newInstance(status[0],status[2],status[1], section.getName());
            fz.setOnClickListener(this);
            ft.add(ll.getId(),fz);
        }



        ft.commit();
    }

    private float calculateTotal() {
        for (Section sec: sections){
            for(int i=0; i< status.length; i++){
                status[i] += sec.getStatus()[i];
            }
        }
        return 97;
    }

    @Override
    public void onClick(View view) {
        Log.d("fe","freuighreihgr");
    }
}
