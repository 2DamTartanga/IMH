package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;

import com.tartanga.dam.imhandroid.manager.Main;
import com.tartanga.dam.imhandroid.manager.Manager;

import com.tartanga.dam.imhandroid.interfaces.onFragmentInteractionListener;
import com.tartanga.dam.imhandroid.manager.Message;
import com.tartanga.dam.imhandroid.model.GlobalUser;
import com.tartanga.dam.imhandroid.model.User;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, onFragmentInteractionListener {

    Button btn;
    EditText username, pass;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_login);


        checkInternetConnection();

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

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
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
/*
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        User u = new User(username.getText().toString(), pass.getText().toString());
        User uServer = null;

        String nombreUsu ="";
        String nombre = "";
        int grupo = 0;
        //Toast.makeText(this, username.getText(), Toast.LENGTH_SHORT).show();

        try {
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
            if (username.getText().toString().isEmpty() && pass.getText().toString().isEmpty()) {
                /*
                User u = new User(username.getText().toString(), pass.getText().toString());
                User uServer = null;
                String nombreUsu ="";
                String nombre = "";
                int grupo = 0;
                try {
                    Main m = new Main(Message.LOGIN, null, u);
                    uServer = (User) m.getObj();
                    //Log.d("MENSAJE", uServer.getUsername());
                    nombreUsu = uServer.getUsername();
                    nombre = uServer.getName();
                    grupo = uServer.getGroup().getId();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (v.getId() == R.id.btn_login) {
                    if (username.getText().toString().isEmpty() && pass.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Must enter username and password", Toast.LENGTH_LONG).show();
                    } else {
                        if (uServer == null) {
                            Toast.makeText(this, "That user does not exist", Toast.LENGTH_LONG).show();
                        } else {
                            Intent i = new Intent(this, MenuActivity.class);
                            i.putExtra("NombreUsuario", nombreUsu);
                            i.putExtra("Nombre", nombre);
                            i.putExtra("Grupo", grupo);
                            Toast.makeText(this, nombreUsu + " " + nombre + " " + grupo, Toast.LENGTH_LONG).show();
                            startActivity(i);
                        }
                    }
                }
                */
                Toast.makeText(this, "Must enter username and password", Toast.LENGTH_LONG).show();
                //Main m = null;
                try {
                    Main m = new Main(Message.LOGIN, null, u);
                    uServer = (User) m.getObj();
                    Toast.makeText(this, Message.LOGIN, Toast.LENGTH_SHORT).show();
                    GlobalUser gU = new GlobalUser();
                    gU.setGlobalUser(uServer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                if (uServer == null) {
                    Toast.makeText(this, "That user does not exist", Toast.LENGTH_LONG).show();
                } else {
                    //String nombreUsu = username.getText().toString();
                    Intent i = new Intent(this, MenuActivity.class);
                    i.putExtra("NombreUsuario", nombreUsu);
                    i.putExtra("Nombre", nombre);
                    i.putExtra("Grupo", grupo);
                    Toast.makeText(this, nombreUsu + " " + nombre + " " + grupo, Toast.LENGTH_LONG).show();
                    startActivity(i);
                }
            }
        }
    }

    private boolean checkInternetConnection() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec
                =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() ==
                android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;
        }else if (
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() ==
                                android.net.NetworkInfo.State.DISCONNECTED  ) {
            Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
