package com.example.e21_pedidosui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String fecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        String hora = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

        TextView fechaTV = (TextView) findViewById(R.id.fecha);
        fechaTV.setText(fecha);

        TextView horaTV = (TextView) findViewById(R.id.hora);
        horaTV.setText(hora);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }


    public void submit(MenuItem item) {
        Intent intent1 = getIntent();
        String cafe1 = intent1.getStringExtra("cafe1");
        String cafe2 = intent1.getStringExtra("cafe2");
        String cafe3 = intent1.getStringExtra("cafe3");
        String metodo = "";

        TextView fechaTV = findViewById(R.id.fecha);
        TextView horaTV = findViewById(R.id.hora);

        RadioButton rbtn1 = findViewById(R.id.rbtn1);
        RadioButton rbtn2 = findViewById(R.id.rbtn2);

        if (!rbtn1.isChecked() && !rbtn2.isChecked()) { // si no se ha seleccionado un metodo de entrega
            Toast toast1 = Toast.makeText(this, "Selecciona un metodo de entrega.", Toast.LENGTH_LONG);
            toast1.show();
        }

        if (rbtn1.isChecked() && !rbtn2.isChecked()) { // si se ha seleccionado 1 domicilio
            metodo = "1";
        }

        if (!rbtn1.isChecked() && rbtn2.isChecked()) { // si se ha seleccionado 2 local
            metodo = "2";
        }

        Intent intent2 = new Intent(this, ThirdActivity.class);
        intent2.putExtra("cafe1", cafe1);
        intent2.putExtra("cafe2", cafe2);
        intent2.putExtra("cafe3", cafe3);
        intent2.putExtra("metodo", metodo);
        intent2.putExtra("fecha", fechaTV.getText().toString());
        intent2.putExtra("hora", horaTV.getText().toString());
        startActivity(intent2);
    }

    public void showTimePicker(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.timepicker));
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.datepicker));
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        String timeMessage = "";
        if (hourOfDay < 10 && minute < 10) {
            timeMessage = ("0" + hour_string + ":" + "0" + minute_string);
        }

        else if(hourOfDay < 10 && minute > 9) {
            timeMessage = ("0" + hour_string + ":" + minute_string);
        }

        else if(hourOfDay > 9 && minute < 10) {
            timeMessage = (hour_string + ":" + "0" + minute_string);
        }

        else {
            timeMessage = (hour_string + ":" + minute_string);
        }

        TextView horaTV = findViewById(R.id.hora);
        horaTV.setText(timeMessage);
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = "";

        if (month < 10 && day < 10) {
            dateMessage = "0" + day_string + "/" + "0" + month_string + "/" + year_string;
        }

        else if (month < 10) {
            dateMessage = day_string + "/" + "0" + month_string + "/" + year_string;
        }

        else if (day < 10) {
            dateMessage = "0" + day_string + "/" + month_string + "/" + year_string;
        }

        else {
            dateMessage = day_string + "/" + month_string + "/" + year_string;
        }

        TextView fechaTV = findViewById(R.id.fecha);
        fechaTV.setText(dateMessage);
    }


    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            SecondActivity activity = (SecondActivity) getActivity();
            activity.processTimePickerResult(hourOfDay, minute);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it.
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            SecondActivity activity = (SecondActivity) getActivity();
            activity.processDatePickerResult(year, month, day);
        }
    }
}