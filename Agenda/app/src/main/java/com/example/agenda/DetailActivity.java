package com.example.agenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    private Button botonCancel = null;
    private Button botonModificar = null;
    private Button botonBorrar = null;
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


            DataManager dbManager = new DataManager(DetailActivity.this);

            Bundle extras = getIntent().getExtras();
            idTareaSelecionada = extras.getString("ID");

            botonModificar = (Button) findViewById(R.id.idButtonModificarDetail);
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
                tarea.setIdUser(MainActivity.usuario.getId());

                SQLiteDatabase db = dbManager.getWritableDatabase();
                dbManager.update(tarea);

                Toast.makeText(this,R.string.texto_toastModificadoCorrectamente,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DetailActivity.this, BaseActivity.class);
                startActivity(intent);
            });

            botonCancel =  findViewById(R.id.idButtonVolverDetail);

            botonCancel.setOnClickListener(view -> {
                Intent intent = new Intent(DetailActivity.this, ListActivity.class);
                finish();
            });

            botonBorrar =  findViewById(R.id.idButtonBorrarDetail);

            botonBorrar.setOnClickListener(view -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setTitle(R.string.texto_tituloAlertDialog);
                builder.setMessage(R.string.texto_mensajeAlertDialog);
                builder.setPositiveButton(R.string.texto_aceptarAlertDialog,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                                dbManager.deleteById(idTareaSelecionada);



                                Intent intent = new Intent(DetailActivity.this,BaseActivity.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

            });



            tareaSeleccionada = dbManager.selectById(Integer.parseInt(idTareaSelecionada));

            editTextNombre = findViewById(R.id.idEditTextNombreRegister);
            editTextNombre.setText(tareaSeleccionada.getNombre());

            editTextDescripcion = findViewById(R.id.idEditTextDescripcionRegister);
            editTextDescripcion.setText(tareaSeleccionada.getDescripción());

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
