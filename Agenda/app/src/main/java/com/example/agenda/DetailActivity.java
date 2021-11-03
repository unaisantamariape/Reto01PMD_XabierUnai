package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import dataBase.DataManager;
import dataBase.Tarea;

public class DetailActivity extends AppCompatActivity {
    private Button botonCancel = null;
    private EditText editTextNombre = null;
    private EditText editTextDescripcion = null;
    private EditText editTextFecha = null;
    private EditText editTextCoste = null;
    private Spinner spinnerPrioridad = null;
    private CheckBox checkBoxRealizado = null;
    private String idTareaSelecionada;
    private Tarea tareaSeleccionada;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);

            Bundle extras = getIntent().getExtras();
            idTareaSelecionada = extras.getString("ID");

            botonCancel = (Button) findViewById(R.id.idBtnCancelarDetails);
            botonCancel.setOnClickListener(view -> {
                Intent intent = new Intent(DetailActivity.this, ListActivity.class);
                finish();
            });

            DataManager dbManager = new DataManager(DetailActivity.this);

            tareaSeleccionada = dbManager.selectById(Integer.parseInt(idTareaSelecionada));

            editTextNombre = (EditText)findViewById(R.id.idEditTextNombreRegister);
            editTextNombre.setText(tareaSeleccionada.getNombre());

            editTextDescripcion = (EditText)findViewById(R.id.idEditTextDescripcionRegister);
            editTextDescripcion.setText(tareaSeleccionada.getDescripción());

            editTextFecha = (EditText) findViewById(R.id.idEditTextFechaRegister);
            editTextFecha.setText(tareaSeleccionada.getFecha());

            editTextCoste = (EditText) findViewById(R.id.idEditTextCosteRegister);
            //editTextCoste.setText(tareaSeleccionada.getCoste());

            spinnerPrioridad = (Spinner) findViewById(R.id.idSpinnerPrioridadRegister);
            //spinnerPrioridad.setSelection(1);

            checkBoxRealizado = (CheckBox) findViewById(R.id.idCheckBoxRealizadaRegister);
            //checkBoxRealizado.isChecked();



        }
}
