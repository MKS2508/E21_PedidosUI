package com.example.e21_pedidosui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int nCafe1 = 0;
    int nCafe2 = 0;
    int nCafe3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void submit(MenuItem item) {

        TextView totalCafe1 = findViewById(R.id.totalCafe1);
        TextView totalCafe2 = findViewById(R.id.totalCafe2);
        TextView totalCafe3 = findViewById(R.id.totalCafe3);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("cafe1", totalCafe1.getText().toString());
        intent.putExtra("cafe2", totalCafe2.getText().toString());
        intent.putExtra("cafe3", totalCafe3.getText().toString());
        startActivity(intent);
    }

    public void rmCafe1(View view) {

        if (nCafe1 > 0) nCafe1--;
        TextView totalCafe1 = findViewById(R.id.totalCafe1);
        totalCafe1.setText(String.valueOf(nCafe1));
    }

    public void rmCafe2(View view) {

        if (nCafe2 > 0) nCafe2--;
        TextView totalCafe2 = findViewById(R.id.totalCafe2);
        totalCafe2.setText(String.valueOf(nCafe2));
    }

    public void rmCafe3(View view) {

        if (nCafe3 > 0) nCafe3--;
        TextView totalCafe3 = findViewById(R.id.totalCafe3);
        totalCafe3.setText(String.valueOf(nCafe3));
    }

    public void addCafe1(View view) {

        if (nCafe1 < 25) nCafe1++;

        TextView cantidadCafeSolo = findViewById(R.id.totalCafe1);
        cantidadCafeSolo.setText(String.valueOf(nCafe1));
    }

    public void addCafe2(View view) {

        if (nCafe2 < 25) nCafe2++;
        TextView totalCafe2 = findViewById(R.id.totalCafe2);
        totalCafe2.setText(String.valueOf(nCafe2));
    }

    public void addCafe3(View view) {

        if (nCafe3 < 25) nCafe3++;
        TextView totalCafe3 = findViewById(R.id.totalCafe3);
        totalCafe3.setText(String.valueOf(nCafe3));
    }

    public void reset(MenuItem item) {

        nCafe1 = 0;
        nCafe2 = 0;
        nCafe3 = 0;

        TextView totalCafe1 = findViewById(R.id.totalCafe1);
        totalCafe1.setText("0");

        TextView totalCafe2 = findViewById(R.id.totalCafe2);
        totalCafe1.setText("0");

        TextView totalCafe3 = findViewById(R.id.totalCafe3);
        totalCafe1.setText("0");
    }



}