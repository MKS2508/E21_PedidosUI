package com.example.e21_pedidosui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class ThirdActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Spinner spinner = findViewById(R.id.spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.telefonos, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }


    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_third, menu);
        return true;
    }

    public void send(MenuItem item) {
        Intent intent = getIntent();

        String msg = "";
        String totalCafe1 = intent.getStringExtra("cafe1");
        String totalCafe2 = intent.getStringExtra("cafe2");
        String totalCafe3 = intent.getStringExtra("cafe3");

        String metodo = intent.getStringExtra("metodo");

        String fecha = intent.getStringExtra("fecha");
        String hora = intent.getStringExtra("hora");

        EditText name = findViewById(R.id.editTextNombre);
        EditText dir = findViewById(R.id.editTextDireccion);
        EditText tlf = findViewById(R.id.editTextTelefono);
        Spinner tipoTelefono = findViewById(R.id.spinner);

        if (metodo.equals("1")) {
            msg = "Mi pedido es " + totalCafe1 + " Cafés Solos, " + totalCafe2 + " Cafés con leche y " + totalCafe3 + " Cafés Cortados.\n"
                    + "Se entregara el dia " + fecha + " a las " + hora + " en la direccion especificada" + ".\n"
                    + "Dirección de entrega: " + dir.getText().toString() + ".\n"
                    + "Número de teléfono: " + tlf.getText().toString() + " (" + tipoTelefono.getSelectedItem().toString() + ")";
        } else if (metodo.equals("2")) {
            msg = "Mi pedido es " + totalCafe1 + " Cafés Solos, " + totalCafe2 + " Cafés con leche y " + totalCafe3 + " Cafés Cortados.\n"
                    + "Se podra recoger en el local el dia " + fecha + " a las " + hora + ".\n"
                    + "Dirección de entrega: " + dir.getText().toString() + ".\n"
                    + "Número de teléfono: " + tlf.getText().toString() + " (" + tipoTelefono.getSelectedItem().toString() + ")";
        }

        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(ThirdActivity.this);

        myAlertBuilder.setMessage("¿Está seguro de que quiere hacer el pedido?");

        String finalMsg = msg;
        myAlertBuilder.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent sendIntent = new Intent();
                String email = "pedidos@mitienda.com";

                sendIntent.setAction(sendIntent.ACTION_SENDTO);
                sendIntent.setData(Uri.parse("mailto:"));
                sendIntent.putExtra(Intent.EXTRA_TEXT, finalMsg);
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de " + name.getText().toString());

                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(sendIntent);
                } else {
                    Toast.makeText(ThirdActivity.this, "No hay un cliente de correo instalado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        myAlertBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // User cancelled the dialog.
                Toast.makeText(getApplicationContext(), "Revise el pedido",
                        Toast.LENGTH_SHORT).show();
            }
        });

        myAlertBuilder.show();
    }
}