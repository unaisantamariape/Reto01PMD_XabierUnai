package com.example.agenda;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;

import dataBase.DataManager;
import dataBase.Tarea;

public class RegisterActivity extends AppCompatActivity {

    private Button botonRegistrar = null;
    private Button botonCancel = null;
    private EditText editTextNombre = null;
    private EditText editTextDescripcion = null;
    private EditText editTextFecha = null;
    private EditText editTextCoste = null;
    private Spinner spinnerPrioridad = null;
    private CheckBox checkBoxRealizada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        botonRegistrar = (Button) findViewById(R.id.idButtonRegistrar);
        botonRegistrar.setOnClickListener(view -> {
            String nombreTarea = editTextNombre.getText().toString();
            String descripcionTarea = String.valueOf(editTextDescripcion.getText());
            Date fechaTarea = Date.valueOf(String.valueOf(editTextFecha.getText()));
            Integer costeTarea = Integer.valueOf(String.valueOf(editTextCoste.getText()));
            String prioridadTarea = String.valueOf(spinnerPrioridad.getSelectedItem());
            //Boolean realizadaTarea = Boolean.valueOf((String) checkBoxRealizada.getText());

            Tarea tarea = new Tarea();
            tarea.setNombre(nombreTarea);
            //tarea.setDescripción(descripcionTarea);
            //tarea.setFecha(fechaTarea);
            //tarea.setCoste(costeTarea);
            //tarea.setPrioridad(prioridadTarea);
            //tarea.setRealizada(false);

            DataManager dbManager = new DataManager(RegisterActivity.this);
            SQLiteDatabase db = dbManager.getWritableDatabase();
            dbManager.insert(tarea);
        });

        botonCancel = (Button) findViewById(R.id.idBtnCancelarRegister);
        botonCancel.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, BaseActivity.class);
            finish();
        });

        editTextNombre = (EditText) findViewById(R.id.idEditTextNombreRegister);
        editTextDescripcion = (EditText) findViewById(R.id.idEditTextDescripcionRegister);
        editTextFecha = (EditText) findViewById(R.id.idEditTextFechaRegister);
        editTextCoste = (EditText) findViewById(R.id.idEditTextCosteRegister);
        spinnerPrioridad = (Spinner) findViewById(R.id.idSpinnerPrioridadRegister);
        checkBoxRealizada = (CheckBox) findViewById(R.id.idCheckBoxRealizadaRegister);

    }
}
