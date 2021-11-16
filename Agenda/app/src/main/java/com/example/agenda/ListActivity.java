package com.example.agenda;

import static com.example.agenda.R.color;
import static com.example.agenda.R.id;
import static com.example.agenda.R.layout;
import static com.example.agenda.R.string;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import dataBase.DataManager;

public class ListActivity extends AppCompatActivity {
    private Button botonRealizadas = null;
    private Button botonPendientes = null;
    private ArrayList<String> tareas = null;
    private ArrayList<Integer> ids = null;
    ArrayAdapter<String> adapter = null;
    private ListView listView = null;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_list);

        DataManager dbManager = new DataManager(ListActivity.this);

        Button botonCancel = findViewById(id.idBtnCancelarListado);
        botonCancel.setOnClickListener(view -> finish());

        botonRealizadas = findViewById(id.idBtnTareasRealizadas);
        botonRealizadas.setOnClickListener(view -> {
            botonRealizadas.setEnabled(false);
            botonRealizadas.setBackgroundColor(color.grey);

            findViewById(id.idBtnTareasPendientes).setBackgroundColor(color.purple);
            findViewById(id.idBtnTareasPendientes).setEnabled(true);
            tareas = dbManager.selectNombresRealizados();
            ids = dbManager.selectIdsRealizadas();
            adapter = new ArrayAdapter<>(ListActivity.this, layout.activity_adapter,
                    id.idTextViewTareasDB, tareas);
            listView.setAdapter(adapter);
        });

        botonPendientes = findViewById(id.idBtnTareasPendientes);
        botonPendientes.setEnabled(false);
        botonPendientes.setOnClickListener(view -> {
            botonPendientes.setEnabled(false);
            botonPendientes.setBackgroundColor(color.grey);

            findViewById(id.idBtnTareasRealizadas).setBackgroundColor(color.purple);
            findViewById(id.idBtnTareasRealizadas).setEnabled(true);
            tareas = dbManager.selectNombresPendientes();
            ids = dbManager.selectIdsPendientes();
            adapter = new ArrayAdapter<>(ListActivity.this, layout.activity_adapter,
                    id.idTextViewTareasDB, tareas);
            listView.setAdapter(adapter);
        });

        //De primeras enseña las que están pendientes
        tareas = dbManager.selectNombresPendientes();
        ids = dbManager.selectIdsPendientes();

        adapter = new ArrayAdapter<>(ListActivity.this, layout.activity_adapter,
                id.idTextViewTareasDB, tareas);

        listView = findViewById(id.idListViewTareas);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            String tareaClicada = ids.get(i).toString();

            Intent intent = new Intent(ListActivity.this,DetailActivity.class);
            intent.putExtra("ID",tareaClicada);
            startActivity(intent);
        });
        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
            builder.setTitle(string.texto_tituloAlertDialog);
            builder.setMessage(string.texto_mensajeAlertDialog);
            builder.setPositiveButton(string.texto_aceptarAlertDialog,
                    (dialog, which) -> {
                        // TODO Auto-generated method stub
                        String idClicado = ids.get(i).toString();
                        tareas.remove(i);

                        dbManager.deleteById(idClicado);

                        adapter.notifyDataSetChanged();
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        });
        listView.setAdapter(adapter);
    }
}
