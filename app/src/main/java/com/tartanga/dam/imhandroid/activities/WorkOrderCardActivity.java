package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.adaptadores.WorkOrderAdapter;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.model.GlobalUser;
import com.tartanga.dam.imhandroid.model.Message;
import com.tartanga.dam.imhandroid.model.WorkOrder;

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


        //Log.d("GROUP", GlobalUser.getGlobalUser().getGroup().getId()+"");
        ThreadSender ts = new ThreadSender(this,new Message(Message.GET, Message.WORK_ORDER, GlobalUser.getGlobalUser().getGroup()));
        ts.execute();
    }

    public void onClickOrder(View v) {
        code = (TextView) v.findViewById(R.id.tv_code);
        Log.d("Mensaje", code.getText().toString());
        machine = (TextView) v.findViewById(R.id.tv_machine);
        Log.d("Mensaje", machine.getText().toString());
        sev = (TextView) v.findViewById(R.id.tv_priority);
        Log.d("Mensaje", sev.getText().toString());
        Intent i = new Intent(this, DetailsWorkOrderActivity.class);
        String codigo = code.getText().toString().substring(3);
        i.putExtra("Codigo", codigo);
        i.putExtra("Maquina", machine.getText().toString());
        i.putExtra("Sev", sev.getText().toString());
        startActivity(i);
    }

    @Override
    public void messageReceived(Object obj) {
        ArrayList<WorkOrder> obj2= ((ArrayList<WorkOrder>) obj);
        orders=obj2;
        if(obj2==null){
            Toast.makeText(this, "NO WORK ORDERS ASIGNED", Toast.LENGTH_SHORT).show();
        }else{
            adapter = new WorkOrderAdapter(orders);
            recycler.setAdapter(adapter);
        }
    }

}
