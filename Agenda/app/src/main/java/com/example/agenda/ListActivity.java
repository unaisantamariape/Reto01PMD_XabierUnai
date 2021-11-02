package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import dataBase.DataManager;

public class ListActivity extends AppCompatActivity {
    private Button botonCancel = null;
    private Button botonRealizadas = null;
    private Button botonPendientes = null;
    private ArrayList<String> tareas = null;
    ArrayAdapter<String> adapter = null;
    private ListView listView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DataManager dbManager = new DataManager(ListActivity.this);

        botonCancel = (Button) findViewById(R.id.idBtnCancelarListado);
        botonCancel.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, BaseActivity.class);
            finish();
        });

        botonRealizadas = (Button) findViewById(R.id.idBtnTareasRealizadas);
        botonRealizadas.setOnClickListener(view -> {
            view.setEnabled(false);
            findViewById(R.id.idBtnTareasPendientes).setEnabled(true);
            tareas = dbManager.selectNombresRealizados();
            adapter = new ArrayAdapter<String>(ListActivity.this, R.layout.activity_adapter,
                    R.id.idTextViewTareasDB, tareas);
            listView.setAdapter(adapter);
        });

        botonPendientes = (Button) findViewById(R.id.idBtnTareasPendientes);
        botonPendientes.setOnClickListener(view -> {
            view.setEnabled(false);
            findViewById(R.id.idBtnTareasRealizadas).setEnabled(true);
            tareas = dbManager.selectNombresPendientes();
            adapter = new ArrayAdapter<String>(ListActivity.this, R.layout.activity_adapter,
                    R.id.idTextViewTareasDB, tareas);
            listView.setAdapter(adapter);
        });

        //De primeras enseña las que están pendientes
        tareas = dbManager.selectNombresPendientes();

        adapter = new ArrayAdapter<String>(ListActivity.this, R.layout.activity_adapter,
                R.id.idTextViewTareasDB, tareas);

        listView = (ListView) findViewById(R.id.idListViewTareas);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);
    }
}
