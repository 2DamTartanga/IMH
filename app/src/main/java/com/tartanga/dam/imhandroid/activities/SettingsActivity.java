package com.tartanga.dam.imhandroid.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.fragments.ConnectionLostFragment;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.model.Message;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.model.GlobalUser;
import com.tartanga.dam.imhandroid.model.User;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements MessageListener, View.OnClickListener, Spinner.OnItemSelectedListener{

    private TextView txtUsername, txtCurrentPass;
    private EditText edCurrentPass, edNewPass;
    private Spinner spinner;
    /*private Locale locale;
    private Configuration config = new Configuration();*/
    private boolean firstLoad = true;
    private boolean secondLoad = true;
    private boolean connectionLost = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        txtUsername = (TextView) findViewById(R.id.txtUsername);

        edCurrentPass = (EditText) findViewById(R.id.edCurrentPass);
        edNewPass = (EditText) findViewById(R.id.edNewPass);

        // to populate textViews
        User u = new User(GlobalUser.getGlobalUser().getUsername(), GlobalUser.getGlobalUser().getPassword());
        ThreadSender ts = new ThreadSender(this, new Message(Message.GET, Message.USER, u));
        ts.execute();

        Button btnChangePass = (Button) findViewById(R.id.btnChangePass);
        btnChangePass.setOnClickListener(this);

        spinner = ((Spinner) findViewById(R.id.spinner));
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(0);
        //SPINNER AVAILABILITY AFTER REPAIR
        String[] languages = getResources().getStringArray(R.array.languages);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.spinner_size, languages) {
            @Override
            public boolean isEnabled(int position) {
                if(position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //firstLoad = true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean checkForm(){
        return !edCurrentPass.getText().toString().equals("") && !edNewPass.getText().toString().equals("");
    }

    @Override
    public void messageReceived(Object obj){
        if(obj instanceof User) {
            User u = (User) obj;
            txtUsername.setText(txtUsername.getText() + " " + u.getUsername());
        } else if(obj instanceof Boolean) {
            boolean result = (Boolean) obj;
            /*StyleableToast s = new StyleableToast(
                    getApplicationContext(),
                    result ? getString(R.string.password_change_ok) : getString(R.string.password_change_error),
                    Toast.LENGTH_SHORT
            );
            s.setBackgroundColor(result ? Color.parseColor("#5aff75") : Color.parseColor("#ff5a5f"));
            s.setTextColor(Color.WHITE);
            s.setIcon(result ? R.drawable.ic_ok_icon : R.drawable.ic_alert_login);
            s.setMaxAlpha();
            s.show();*/
            Toast.makeText(getApplicationContext(),result ? getString(R.string.password_change_ok) : getString(R.string.password_change_error),Toast.LENGTH_SHORT).show();
        }
        if(obj.toString().equals("Connection with server lost")){
            //Toast.makeText(this, getApplicationContext().getString(R.string.connection_lost), Toast.LENGTH_LONG).show();
            connectionLost = true;
            DialogFragment newFragment = new ConnectionLostFragment();
            newFragment.show(getFragmentManager(), "Error");
        }
    }

    @Override
    public void onClick(View view) {
        if(checkForm()) {
            User u = new User(GlobalUser.getGlobalUser().getUsername(), edNewPass.getText().toString());
            ThreadSender ts = new ThreadSender(this, new Message(Message.MOD, Message.USER, u));
            ts.execute();
        } else {
            /*StyleableToast s = new StyleableToast(this, "You need to complete all fields", Toast.LENGTH_SHORT);
            s.setBackgroundColor(Color.parseColor("#ff5a5f"));
            s.setTextColor(Color.WHITE);
            s.setIcon(R.drawable.ic_alert_login);
            s.setMaxAlpha();
            s.show();*/
            Toast.makeText(this,getString(R.string.required_fields),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //if(!firstLoad && !secondLoad){
            Log.d("position", "i=" + i + " pos=" + spinner.getSelectedItemPosition());
            switch (spinner.getSelectedItemPosition()) {
                case 1:
                    /*locale = new Locale("en");
                    //config.locale = locale;
                    config.setLocale(locale);*/
                    setLocale("en");
                    Toast.makeText(getApplicationContext(),"Language changed",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    /*locale = new Locale("eu");
                    //config.locale = locale;
                    config.setLocale(locale);*/
                    setLocale("eu");
                    Toast.makeText(getApplicationContext(),"Hizkuntza aldatuta",Toast.LENGTH_SHORT).show();
                    break;
            }
            /*Locale.setDefault(locale);
            Configuration config = new Configuration();
            //config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //startActivity(intent);
            recreate();*/
        /*} else {
            if(firstLoad) firstLoad = false;
            else secondLoad = false;
        }*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //Empty
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        //Resources res = getResources();
        //DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = getBaseContext().getResources().getConfiguration();
        //conf.locale = myLocale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(myLocale);
        } else {
            conf.locale = myLocale;
        }
        getBaseContext().getResources().updateConfiguration(conf, getBaseContext().getResources().getDisplayMetrics());
        /*Intent refresh = new Intent(this, SettingsActivity.class);
        startActivity(refresh);
        recreate();*/
        Intent menu = new Intent(this, MenuActivity.class);
        startActivity(menu);
    }
}
