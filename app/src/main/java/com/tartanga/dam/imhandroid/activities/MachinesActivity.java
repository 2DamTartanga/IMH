package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.adaptadores.MachineAdapter;

import java.util.List;

public class MachinesActivity extends AppCompatActivity {

    List machines;
    LinearLayout linear;

    private ImageButton btn_working;
    private ImageButton btn_half_working;
    private ImageButton btn_not_working;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private boolean workClicked=false;
    private boolean halfWorkClicked=false;
    private boolean notWorkClicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machines);

        recycler = (RecyclerView) findViewById(R.id.machineList);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new MachineAdapter(machines);
        recycler.setAdapter(adapter);

        linear = (LinearLayout) findViewById(R.id.layout);

        //BOTONES FRAGMENTO
        /*btn_working = (ImageButton) findViewById(R.id.btn_working);
        btn_half_working = (ImageButton) findViewById(R.id.btn_half_working);
        btn_not_working = (ImageButton) findViewById(R.id.btn_not_woking);*/

        //BOTONES ACTIVIDAD
        btn_working = (ImageButton) findViewById(R.id.btnWorking);
        btn_half_working = (ImageButton) findViewById(R.id.btnHalfWorking);
        btn_not_working = (ImageButton) findViewById(R.id.btnNotWorking);
    }

    //MACHINE
    public void onClickMachine(View v) {
        Toast.makeText(this,"Details", Toast.LENGTH_LONG).show();
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
            btn_working.setImageResource(R.drawable.ic_working_disabled);
            /*btn_working.setBackgroundResource(R.color.colorGray);
            btn_working.getBackground().setAlpha(95);*/
            workClicked=true;
        } else {
            btn_working.setImageResource(R.drawable.ic_working);
            //btn_working.setBackgroundResource(R.color.colorWhite);
            workClicked=false;
        }
    }

    public void onClickHalfWork (View v) {
        //Toast.makeText(this,"WORKING", Toast.LENGTH_SHORT).show();
        if (!halfWorkClicked) {
            btn_half_working.setImageResource(R.drawable.ic_half_working_disabled);
            /*btn_half_working.setBackgroundResource(R.color.colorGray);
            btn_half_working.getBackground().setAlpha(95);*/
            halfWorkClicked=true;
        } else {
            btn_half_working.setImageResource(R.drawable.ic_half_working);
            //btn_half_working.setBackgroundResource(R.color.colorWhite);
            halfWorkClicked=false;
        }
    }

    public void onClickNotWork (View v) {
        //Toast.makeText(this,"WORKING", Toast.LENGTH_SHORT).show();
        if (!notWorkClicked) {
            btn_not_working.setImageResource(R.drawable.ic_not_working_disabled);
            /*btn_not_working.setBackgroundResource(R.color.colorGray);
            btn_not_working.getBackground().setAlpha(95);*/
            notWorkClicked=true;
        } else {
            btn_not_working.setImageResource(R.drawable.ic_not_working);
            //btn_not_working.setBackgroundResource(R.color.colorWhite);
            notWorkClicked=false;
        }
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
}
