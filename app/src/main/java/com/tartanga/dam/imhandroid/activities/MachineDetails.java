package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.adaptadores.WorkOrderAdapter;
import com.tartanga.dam.imhandroid.fragments.FragmentAddReport;
import com.tartanga.dam.imhandroid.fragments.MachineFragment;
import com.tartanga.dam.imhandroid.fragments.WorkOrderFragment;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.model.Breakdown;
import com.tartanga.dam.imhandroid.model.Machine;
import com.tartanga.dam.imhandroid.model.Message;
import com.tartanga.dam.imhandroid.model.WorkOrder;

import java.util.ArrayList;
import java.util.List;

public class MachineDetails extends AppCompatActivity implements MessageListener{

    Machine machine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_details);

        //getSupportFragmentManager().beginTransaction().add(R.id.container2, FragmentAddReport.newInstance()).commit();
        String machineId = getIntent().getExtras().getString("codigo");
        ThreadSender ts = new ThreadSender(this, new Message(Message.GET, Message.MACHINE, new Machine(machineId)));
        ts.execute();


    }

    public void onClickWorkOrder(View v){

    }
    public void onClickAddRequest(View v){
        Intent i = new Intent(this, ActivityReport.class);
        //AÑADIR PUTEXTRA CON EL DATO DEL INTENT ANTERIOR
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void messageReceived(Object obj) {
        if(obj instanceof Machine){
            machine = ((Machine) obj);
            loadUI();
        }
    }

    private void loadUI() {
        ArrayList<Breakdown> breakdowns = machine.getBreakdowns();
        LinearLayout ll = (LinearLayout)(findViewById(R.id.scroll_view)).findViewById(R.id.layout);
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        for (Breakdown br: breakdowns){
            WorkOrderFragment mf = WorkOrderFragment.newInstance(br.getSubject(),br.getMachine().getId(), br.getEquipmentAvailable());
            //mf.setOnClickListener(this);//TODO quitar todos estos?
            ft.add(ll.getId(),mf);
        }



        ft.commit();
    }
}
