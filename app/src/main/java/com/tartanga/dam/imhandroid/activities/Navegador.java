package com.tartanga.dam.imhandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tartanga.dam.imhandroid.R;

public class Navegador extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.activity_navigation_drawer, menu);
        Toast.makeText(this,"PULSADO NUMERO 1", Toast.LENGTH_LONG).show();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_all_zones:
                Toast.makeText(this,"PULSADO NUMERO 1", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_work_orders:
                Toast.makeText(this,"PULSADO NUMERO 2", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    /*public void onClickStatus(View v){
        Toast.makeText(this,"PULSADO NUMERO 1", Toast.LENGTH_LONG).show();
    }*/
}
