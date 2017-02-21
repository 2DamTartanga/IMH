package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.manager.VersionController;
import com.tartanga.dam.imhandroid.model.Breakdown;
import com.tartanga.dam.imhandroid.model.GlobalUser;
import com.tartanga.dam.imhandroid.model.Group;
import com.tartanga.dam.imhandroid.model.Message;
import com.tartanga.dam.imhandroid.model.Repair;
import com.tartanga.dam.imhandroid.model.WorkOrder;

import java.util.ArrayList;

public class DetailsWorkOrderActivity extends AppCompatActivity implements MessageListener, View.OnClickListener{

    private Button btnStart;
    private Button btnNext;
    private Button btnCancel;
    private String code;
    private Button btnIns;
    private String machine, sev;
    private TextView tMachine,tBreakdown, tDescripcion, tSev, tFailure;
    private VersionController vControl = new VersionController();
    private Breakdown b;
    private int codeInt;
    private WorkOrder wOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        code = getIntent().getStringExtra("Codigo");
        machine = getIntent().getStringExtra("Maquina");
        sev = getIntent().getStringExtra("Sev");
        codeInt = Integer.parseInt(code);
        Group group= GlobalUser.getGlobalUser().getGroup();
        b = new Breakdown(codeInt, null, null, null, null, null, null, null);
        Repair r = new Repair();
        r.setGroup(group);
        WorkOrder wo = new WorkOrder(b, 0, null, null, null, r);
        ThreadSender ts = new ThreadSender(this,new Message(Message.GET, Message.WORK_ORDER, wo));
        ts.execute();
        if(vControl.olderVersions())
            setContentView(R.layout.fragment_work_order_older_versions);
        else
            setContentView(R.layout.fragment_work_order);

        boolean ins = getIntent().getExtras().getBoolean("Instruct");

        btnIns = (Button) findViewById(R.id.btnChangePass);
        btnIns.setOnClickListener(this);
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

    /*public void onClickInstructions(View v){

    }*/

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
        /*Repair r1 = new Repair();
        wOrder.setRepair(r1);*/
        i.putExtra("Work", wOrder);
        Log.d("MENSAJE", "BOTON SIGUIENTE");
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void messageReceived(Object obj) {
        if(obj instanceof WorkOrder){
            wOrder = (WorkOrder) obj;
            rellenar();
        }

    }

    private void rellenar() {
        tMachine.setText(machine);
        tSev.setText("Severity: " + sev);
        tBreakdown.setText("Breakdown \n" + wOrder.getBreakdown().getSubject());
        tDescripcion.setText("Description \n" + wOrder.getBreakdown().getDescription());
        tFailure.setText("Failure type: " + wOrder.getBreakdown().getFailureType());
    }

    @Override
    public void onClick(View view) {
        String url = wOrder.getOthers();
        String s = "";
        String s2="";
        if(url!=null && !url.isEmpty()){
            s = url.substring(0,3);
            s2 = url.substring(0,4);
        }
        Log.d("INSTRUCCIONES", s2);
        if(s2.equals("http") || s.equals("www")){
            Log.d("INSTRUCCIONES", "ENTRA COMO WEB");
            Intent iUrl = new Intent(Intent.ACTION_VIEW);
            iUrl.setData(Uri.parse(url));
            startActivity(iUrl);
        }else if(url!=null && !url.isEmpty()){
            Intent i = new Intent(this, InstruccionesActivity.class);
            i.putExtra("Texto", url);
            startActivity(i);
        }else if(url==null || url.equals("")){
            /*StyleableToast t = new StyleableToast(this, getApplicationContext().getString(R.string.noInstruc), Toast.LENGTH_SHORT);
            t.setBackgroundColor(Color.parseColor("#ff5a5f"));
            t.setTextColor(Color.WHITE);
            t.setIcon(R.drawable.ic_alert_login);
            t.setMaxAlpha();
            t.show();*/
            Toast.makeText(this, getApplicationContext().getString(R.string.noInstruc), Toast.LENGTH_SHORT).show();
        }
    }
}
