package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.manager.Manager;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.model.GlobalUser;
import com.tartanga.dam.imhandroid.model.Message;
import com.tartanga.dam.imhandroid.model.User;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, MessageListener {

    Button btn;
    EditText username, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy p = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(p);
        setContentView(R.layout.fragment_login);

        btn = (Button) findViewById(R.id.btn_login);
        btn.setOnClickListener(this);

        username = (EditText) findViewById(R.id.et_name);
        pass = (EditText) findViewById(R.id.et_password);

    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        User u = new User(username.getText().toString(), pass.getText().toString());
        if (v.getId() == R.id.btn_login) {
            if (!username.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
                ThreadSender ts = new ThreadSender(this,new Message(Message.LOGIN,null,u));
                ts.execute();
            }
            /*
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
            */
    }


    @Override
    public void messageReceived(Object obj) {
        GlobalUser.setGlobalUser(((User) obj));
        if(obj == null){
            StyleableToast t = new StyleableToast(this, getApplicationContext().getString(R.string.login_invalid_msg), Toast.LENGTH_SHORT);
            t.setBackgroundColor(Color.parseColor("#ff5a5f"));
            t.setTextColor(Color.WHITE);
            t.setIcon(R.drawable.ic_alert_login);
            t.setMaxAlpha();
            t.show();
        }else {
            startActivity(new Intent(this, MenuActivity.class));
            this.finish();//TODO cerrar esto?
        }

    }
}