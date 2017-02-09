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

public class SettingsActivity extends AppCompatActivity {

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

        btnChangePass = (Button)findViewById(R.id.btnChangePass);
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            if(checkForm()) {

                // TODO call to server method to change password
                
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
}
