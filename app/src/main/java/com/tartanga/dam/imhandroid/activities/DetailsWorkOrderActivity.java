package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.manager.VersionController;
import com.tartanga.dam.imhandroid.model.Breakdown;
import com.tartanga.dam.imhandroid.model.GlobalUser;
import com.tartanga.dam.imhandroid.model.Message;

public class DetailsWorkOrderActivity extends AppCompatActivity implements MessageListener{

    private Button btnStart;
    private Button btnNext;
    private Button btnCancel;
    private String code;
    private String machine, sev;
    private TextView tMachine,tBreakdown, tDescripcion, tSev, tFailure;
    private VersionController vControl = new VersionController();
    private Breakdown b;
    private int codeInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        code = getIntent().getStringExtra("Codigo");
        machine = getIntent().getStringExtra("Maquina");
        sev = getIntent().getStringExtra("Sev");
        codeInt = Integer.parseInt(code);
        b = new Breakdown(codeInt,null,null,null,null,null,null,null);
        ThreadSender ts = new ThreadSender(this,new Message(Message.GET, Message.BREAKDOWN, b));
        ts.execute();
        if(vControl.olderVersions())
            setContentView(R.layout.fragment_work_order_older_versions);
        else
            setContentView(R.layout.fragment_work_order);

        boolean ins = getIntent().getExtras().getBoolean("Instruct");

        btnStart = (Button) findViewById(R.id.btn_start_working);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnCancel = (Button) findViewById(R.id.btn_cancel_work);
        tMachine = (TextView) findViewById(R.id.tv_machine);
        tBreakdown = (TextView) findViewById(R.id.tv_breakdown);
        tDescripcion = (TextView) findViewById(R.id.tv_description);
        tSev = (TextView) findViewById(R.id.tv_severity);
        tFailure = (TextView) findViewById(R.id.tv_failure_type);


        if(ins) {
            btnStart.setVisibility(View.INVISIBLE);
            btnNext.setVisibility(View.INVISIBLE);
            btnCancel.setVisibility(View.INVISIBLE);
        }
    }

    public void onClickStart(View v) {
        btnStart.setVisibility(View.INVISIBLE);
        btnNext.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
    }

    public void onClickCancel(View v) {
        btnStart.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.INVISIBLE);
        btnCancel.setVisibility(View.INVISIBLE);
    }

    public void onClickNext(View v) {
        Intent i = new Intent(this, SendWorkOrderActivity.class);
        i.putExtra("Codigo", code);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void messageReceived(Object obj) {
        Breakdown bd = (Breakdown) obj;
        rellenar(bd);
    }

    private void rellenar(Breakdown bd) {
        tMachine.setText(machine);
        tSev.setText("Severity: " + sev);
        tBreakdown.setText("Breakdown \n" + bd.getSubject());
        tDescripcion.setText("Description \n" + bd.getDescription());
        tFailure.setText("Failure type: " + bd.getFailureType());
    }
}
