package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.adaptadores.WorkZonesAdapter;
import com.tartanga.dam.imhandroid.fragments.MachineFragment;
import com.tartanga.dam.imhandroid.fragments.fragmento_Zonas;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.model.Machine;
import com.tartanga.dam.imhandroid.model.Message;
import com.tartanga.dam.imhandroid.model.Section;

import java.util.List;

public class TypeMachineActivity extends AppCompatActivity implements MessageListener {


    Section section;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_machine);
        String sectionId = getIntent().getExtras().getString("zone");

        ThreadSender ts = new ThreadSender(this,new Message(Message.GET, Message.WORK_ZONE, new Section(sectionId)));
        ts.execute();


    }

    public void onClickZone(View v) {
        Intent i = new Intent(this, MachinesActivity.class);
        type = ((TextView) v.findViewById(R.id.tv_work_zone)).getText().toString();
        i.putExtra("zone", section);
        i.putExtra("type", type);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void init(){
        LinearLayout ll = (LinearLayout)(findViewById(R.id.scroll_view)).findViewById(R.id.layout);
        ll.removeAllViews();
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        int working;
        int halfWorking;
        int notWorking;
        Machine m;
        for(int i = 0; i< section.getMachines().size(); i++){
            m = section.getMachines().get(i);
            working = 0;
            halfWorking = 0;
            notWorking = 0;
            type = m.getMachineFamilly();
            m = section.getMachines().get(i);
            while(type.equals(m.getMachineFamilly()) && i < section.getMachines().size()) {
                switch(Character.toUpperCase(m.getStatus())){
                    case 'V':
                        working++;
                        break;
                    case 'A':
                        halfWorking++;
                        break;
                    case 'R':
                        notWorking++;
                        break;
                }
                i++;
                if(i < section.getMachines().size()) {
                    m = section.getMachines().get(i);
                }
            }
            i--;
            fragmento_Zonas mf = fragmento_Zonas.newInstance(working,halfWorking,notWorking, type);
            ft.add(ll.getId(), mf);
        }



        ft.commit();
    }

    @Override
    public void messageReceived(Object obj) {
        if(obj instanceof Section) {
            section = ((Section) obj);
            init();
        }
    }
}
