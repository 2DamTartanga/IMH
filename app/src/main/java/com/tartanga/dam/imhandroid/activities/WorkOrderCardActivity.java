package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.adaptadores.WorkOrderAdapter;
import com.tartanga.dam.imhandroid.fragments.WorkOrderFragment;
import com.tartanga.dam.imhandroid.fragments.fragmento_Zonas;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.model.Breakdown;
import com.tartanga.dam.imhandroid.model.GlobalUser;
import com.tartanga.dam.imhandroid.model.Message;
import com.tartanga.dam.imhandroid.model.WorkOrder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WorkOrderCardActivity extends AppCompatActivity implements MessageListener{

    List orders;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private TextView code;
    private TextView machine;
    private TextView sev;

    private int countObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_order);

        /*

        public TextView tv_breakdown;
        public TextView tv_machine;
        public TextView tv_priority;
        public TextView tv_code;

        private static final long serialVersionUID = 1L;
        private int severity;
        private String others;
        private String typeOfMaintenance;
        private Date creationDate;
        private ArrayList<Repair> repairs;
        private Breakdown breakdown;

         */

        recycler = (RecyclerView) findViewById(R.id.container);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);


        Log.d("GROUP", GlobalUser.getGlobalUser().getGroup().getId()+"");
        ThreadSender ts = new ThreadSender(this,new Message(Message.GET, Message.WORK_ORDER, GlobalUser.getGlobalUser().getGroup()));
        ts.execute();


    }

    public void onClickWorkOrder(View v) {
        code = (TextView) v.findViewById(R.id.tv_code);
        machine = (TextView) v.findViewById(R.id.tv_machine);
        sev = (TextView) v.findViewById(R.id.tv_priority);
        Intent i = new Intent(this, DetailsWorkOrderActivity.class);
        i.putExtra("Codigo", code.getText().toString());
        i.putExtra("Maquina", machine.getText().toString());
        i.putExtra("Sev", sev.getText().toString());
        startActivity(i);
    }

    @Override
    public void messageReceived(Object obj) {
        Log.d("ArrayList de Ots", String.valueOf(obj));
        ArrayList<WorkOrder> obj2= ((ArrayList<WorkOrder>) obj);
        Log.d("ArrayList de Ots", obj2.size()+"");
        orders=obj2;

        adapter = new WorkOrderAdapter(orders);
        recycler.setAdapter(adapter);

    }


}
