package com.tartanga.dam.imhandroid.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.adaptadores.WorkOrderAdapter;
import com.tartanga.dam.imhandroid.fragments.ConnectionLostFragment;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;

import java.util.List;

public class InstructionsActivity extends AppCompatActivity implements MessageListener {

    List orders;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Toast.makeText(this, getString(R.string.instructions), Toast.LENGTH_LONG).show();
        this.finish();

        recycler = (RecyclerView) findViewById(R.id.container);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new WorkOrderAdapter(orders);
        recycler.setAdapter(adapter);
    }

    public void onClickWorkOrder(View v) {
        Intent i = new Intent(this, DetailsWorkOrderActivity.class);
        i.putExtra("Instruct", true);
        startActivity(i);
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
}

