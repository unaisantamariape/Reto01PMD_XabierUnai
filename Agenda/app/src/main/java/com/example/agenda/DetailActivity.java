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

public class DetailActivity extends AppCompatActivity {
    private Button botonCancel = null;
    private Button botonModificar = null;
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

            botonModificar = (Button) findViewById(R.id.idButtonModificar);
            botonModificar.setOnClickListener(view -> {
                String nombreTarea = editTextNombre.getText().toString();
                String descripcionTarea = editTextDescripcion.getText().toString();
                String fechaTarea = editTextFecha.getText().toString();
                int costeTarea = Integer.parseInt(String.valueOf(editTextCoste.getText()));
                String prioridadTarea = spinnerPrioridad.getSelectedItem().toString();
                int realizadaTarea;
                if (checkBoxRealizado.isChecked()){
                     realizadaTarea = 1;
                }else{
                     realizadaTarea = 0;
                }



                Tarea tarea = new Tarea();
                tarea.setId(Integer.parseInt(idTareaSelecionada));
                tarea.setNombre(nombreTarea);
                tarea.setDescripción(descripcionTarea);
                tarea.setFecha(fechaTarea);
                tarea.setCoste(costeTarea);
                tarea.setPrioridad(prioridadTarea);
                tarea.setRealizada(realizadaTarea);

                DataManager dbManager = new DataManager(DetailActivity.this);
                SQLiteDatabase db = dbManager.getWritableDatabase();
                dbManager.update(tarea);

                Toast.makeText(this,R.string.texto_toastModificadoCorrectamente,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DetailActivity.this, BaseActivity.class);
                startActivity(intent);
            });

            botonCancel = (Button) findViewById(R.id.idBtnCancelarRegister);

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
            editTextCoste.setText(tareaSeleccionada.getCoste()+"");

            spinnerPrioridad = (Spinner) findViewById(R.id.idSpinnerPrioridadRegister);
            //spinnerPrioridad.setSelection(1);

            checkBoxRealizado = (CheckBox) findViewById(R.id.idCheckBoxRealizadaRegister);
            //checkBoxRealizado.isChecked();



        }
}
