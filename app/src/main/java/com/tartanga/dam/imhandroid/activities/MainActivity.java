package com.tartanga.dam.imhandroid.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import com.tartanga.dam.imhandroid.R;

import com.tartanga.dam.imhandroid.manager.Main;
import com.tartanga.dam.imhandroid.manager.Manager;

import com.tartanga.dam.imhandroid.interfaces.onFragmentInteractionListener;
import com.tartanga.dam.imhandroid.manager.Message;
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        User u = new User(username.getText().toString(),pass.getText().toString());
        try {
            Main m = new Main(Message.LOGIN,null,u);
            User uServer = (User) m.getObj();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(v.getId()==R.id.btn_login){
            if (username.getText().toString().isEmpty() && pass.getText().toString().isEmpty()){
                Toast.makeText(this,"Must enter username and password", Toast.LENGTH_LONG).show();
            }else {
                Intent i = new Intent(this, MenuActivity.class);
                startActivity(i);
            }
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



}
