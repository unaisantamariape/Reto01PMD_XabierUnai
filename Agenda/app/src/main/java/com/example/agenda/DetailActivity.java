package com.example.agenda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import dataBase.DataManager;
import dataBase.Tarea;

public class DetailActivity extends AppCompatActivity {
    private EditText editTextNombre = null;
    private EditText editTextDescripcion = null;
    private EditText editTextFecha = null;
    private EditText editTextCoste = null;
    private Spinner spinnerPrioridad = null;
    private CheckBox checkBoxRealizado = null;
    private String idTareaSelecionada;

    @SuppressLint("SetTextI18n")
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);


            DataManager dbManager = new DataManager(DetailActivity.this);

            Bundle extras = getIntent().getExtras();
            idTareaSelecionada = extras.getString("ID");

            Button botonModificar = findViewById(R.id.idButtonModificarDetail);
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
                tarea.setDescripcion(descripcionTarea);
                tarea.setFecha(fechaTarea);
                tarea.setCoste(costeTarea);
                tarea.setPrioridad(prioridadTarea);
                tarea.setRealizada(realizadaTarea);
                tarea.setIdUser(MainActivity.usuario.getId());

                dbManager.update(tarea);

                Toast.makeText(this,R.string.texto_toastModificadoCorrectamente,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DetailActivity.this, BaseActivity.class);
                startActivity(intent);
            });

            Button botonCancel = findViewById(R.id.idButtonVolverDetail);

            botonCancel.setOnClickListener(view -> finish());

            Button botonBorrar = findViewById(R.id.idButtonBorrarDetail);

            botonBorrar.setOnClickListener(view -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setTitle(R.string.texto_tituloAlertDialog);
                builder.setMessage(R.string.texto_mensajeAlertDialog);
                builder.setPositiveButton(R.string.texto_aceptarAlertDialog,
                        (dialog, which) -> {
                            // TODO Auto-generated method stub

                            dbManager.deleteById(idTareaSelecionada);



                            Intent intent = new Intent(DetailActivity.this,BaseActivity.class);
                            startActivity(intent);
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

            });


        Tarea tareaSeleccionada = dbManager.selectById(Integer.parseInt(idTareaSelecionada));

            editTextNombre = findViewById(R.id.idEditTextNombreRegister);
            editTextNombre.setText(tareaSeleccionada.getNombre());

            editTextDescripcion = findViewById(R.id.idEditTextDescripcionRegister);
            editTextDescripcion.setText(tareaSeleccionada.getDescripcion());

            editTextFecha = findViewById(R.id.idEditTextFechaRegister);
            editTextFecha.setText(tareaSeleccionada.getFecha());

            editTextCoste = findViewById(R.id.idEditTextCosteRegister);
            editTextCoste.setText(tareaSeleccionada.getCoste()+"");

            spinnerPrioridad = findViewById(R.id.idSpinnerPrioridadRegister);
            String seleccion = tareaSeleccionada.getPrioridad();
            spinnerPrioridad.setSelection(((ArrayAdapter)spinnerPrioridad.getAdapter()).getPosition(seleccion));

            checkBoxRealizado = findViewById(R.id.idCheckBoxRealizadaRegister);
            if (tareaSeleccionada.getRealizada() == 1){
                checkBoxRealizado.setChecked(true);
            }
        }
}
