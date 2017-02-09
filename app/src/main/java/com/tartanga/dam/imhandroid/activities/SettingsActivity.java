package com.tartanga.dam.imhandroid.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.tartanga.dam.imhandroid.R;
import com.tartanga.dam.imhandroid.interfaces.MessageListener;
import com.tartanga.dam.imhandroid.model.Message;
import com.tartanga.dam.imhandroid.manager.ThreadSender;
import com.tartanga.dam.imhandroid.model.GlobalUser;
import com.tartanga.dam.imhandroid.model.User;

public class SettingsActivity extends AppCompatActivity implements MessageListener{

    private Button btnChangePass;
    private TextView txtUsername, txtCurrentPass;
    private EditText edCurrentPass, edNewPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        txtCurrentPass = (TextView) findViewById(R.id.txtCurrentPass);
        txtUsername = (TextView) findViewById(R.id.txtUsername);

        edCurrentPass = (EditText) findViewById(R.id.edCurrentPass);
        edNewPass = (EditText) findViewById(R.id.edNewPass);

        // to populate textViews
        User u = new User(GlobalUser.getGlobalUser().getUsername(), GlobalUser.getGlobalUser().getPassword());
        ThreadSender ts = new ThreadSender(this, new Message(Message.GET, null, u));
        ts.execute();

        btnChangePass = (Button)findViewById(R.id.btnChangePass);
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            if(checkForm()) {

                User u = new User(GlobalUser.getGlobalUser().getUsername(), edNewPass.getText().toString());
                ThreadSender ts = new ThreadSender(this, new Message(Message.MOD, Message.USER, u));
                ts.execute();

            }else {
                StyleableToast s = new StyleableToast(getApplicationContext(), "You need to complete all fields.", Toast.LENGTH_SHORT);
                s.setBackgroundColor(Color.parseColor("#ff5a5f"));
                s.setTextColor(Color.WHITE);
                s.setIcon(R.drawable.ic_alert_login);
                s.setMaxAlpha();
                s.show();
            }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean checkForm(){
        return !edCurrentPass.getText().equals("") && !edNewPass.getText().equals("");
    }

    @Override
    public void messageReceived(Object obj){
        if(obj instanceof User) {

            User u = (User) obj;
            txtUsername.setText( txtUsername.getText() + " " + u.getUsername());
            txtCurrentPass.setText( txtCurrentPass.getText() + " " + u.getPassword());

        } else if(obj instanceof Boolean) {

            boolean result = (Boolean) obj;
            StyleableToast s = new StyleableToast(
                    getApplicationContext(),
                    result ? getString(R.string.password_change_ok) : getString(R.string.password_change_error),
                    Toast.LENGTH_SHORT
            );
            s.setBackgroundColor(result ? Color.parseColor("#5aff75") : Color.parseColor("#ff5a5f"));
            s.setTextColor(Color.WHITE);
            s.setIcon(result ? R.drawable.ic_ok_icon : R.drawable.ic_alert_login);
            s.setMaxAlpha();
            s.show();

        }
    }
}
