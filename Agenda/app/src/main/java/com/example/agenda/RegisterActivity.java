package com.example.agenda;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

        editTextNombre = (EditText) findViewById(R.id.idEditTextNombreRegister);
        editTextDescripcion = (EditText) findViewById(R.id.idEditTextDescripcionRegister);
        editTextFecha = (EditText) findViewById(R.id.idEditTextFechaRegister);
        editTextCoste = (EditText) findViewById(R.id.idEditTextCosteRegister);
        spinnerPrioridad = (Spinner) findViewById(R.id.idSpinnerPrioridadRegister);
        checkBoxRealizada = (CheckBox) findViewById(R.id.idCheckBoxRealizadaRegister);

        botonRegistrar = (Button) findViewById(R.id.idButtonRegistrar);
        botonRegistrar.setOnClickListener(view -> {

            String nombreTarea = "";
            String descripcionTarea = "";
            String fechaTarea = "";
            int costeTarea = 0;
            boolean faltanDatos = true;

            if(editTextNombre.getText().toString().equals("")){
                Toast.makeText(this,R.string.texto_toastIntroduceDatos,Toast.LENGTH_SHORT).show();
                faltanDatos = false;
            }else{
               nombreTarea = editTextNombre.getText().toString();
                faltanDatos = true;
            }

            if(editTextDescripcion.getText().toString().equals("")){
                Toast.makeText(this,R.string.texto_toastIntroduceDatos,Toast.LENGTH_SHORT).show();
                faltanDatos = false;
            }else{
                descripcionTarea = editTextDescripcion.getText().toString();
                faltanDatos = true;
            }

            if(editTextFecha.getText().toString().equals("")){
                Toast.makeText(this,R.string.texto_toastIntroduceDatos,Toast.LENGTH_SHORT).show();
                faltanDatos = false;
            }else{
                fechaTarea =editTextFecha.getText().toString();
                faltanDatos = true;
            }

            if(editTextCoste.getText().toString().equals("")){
                Toast.makeText(this,R.string.texto_toastIntroduceDatos,Toast.LENGTH_SHORT).show();
                faltanDatos = false;
            }else{
                costeTarea = Integer.parseInt(editTextCoste.getText().toString());
                faltanDatos = true;
            }

            String prioridadTarea = spinnerPrioridad.getSelectedItem().toString();
            int realizadaTarea = 0;
            if (checkBoxRealizada.isChecked()){
               realizadaTarea = 1;
           }

            if (faltanDatos) {
                Tarea tarea = new Tarea();
                tarea.setNombre(nombreTarea);
                tarea.setDescripción(descripcionTarea);
                tarea.setFecha(fechaTarea);
                tarea.setCoste(costeTarea);
                tarea.setPrioridad(prioridadTarea);
                tarea.setRealizada(realizadaTarea);

                DataManager dbManager = new DataManager(RegisterActivity.this);
                SQLiteDatabase db = dbManager.getWritableDatabase();
                dbManager.insert(tarea);
                Toast.makeText(this,R.string.texto_toastCreadoCorrectamente,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RegisterActivity.this, BaseActivity.class);
                finish();
            }

        });

        botonCancel = (Button) findViewById(R.id.idButtonCancelarRegister);
        botonCancel.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, BaseActivity.class);
            finish();
        });
    }
}
