package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private void init() {
        Manager m = Manager.getInstance();
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        User u = new User(username.getText().toString(), pass.getText().toString());
        ThreadSender ts = new ThreadSender(this, new Message(Message.LOGIN, null, u));
        ts.execute();
       /* User uServer = null;

        String nombreUsu = "";
        String nombre = "";
        int grupo = 0;

        //Toast.makeText(this, username.getText(), Toast.LENGTH_SHORT).show();
       /try {
            Main m = new Main(Message.LOGIN, null, u);
            uServer = (User) m.getObj();
            Log.d("MENSAJE", uServer.getUsername());
            nombreUsu = uServer.getUsername();
            nombre = uServer.getName();
            grupo = uServer.getGroup().getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (v.getId() == R.id.btn_login) {
            if (!username.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
                ThreadSender ts = new ThreadSender(this,new Message(Message.LOGIN,null,new User(username.getText().toString(),pass.getText().toString())));
                ts.execute();
            }
            /*
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
            */
    }


    @Override
    public void messageReceived(Object obj) {
        if(obj instanceof User)
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