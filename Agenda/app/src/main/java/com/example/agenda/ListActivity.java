package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import dataBase.DataManager;

public class ListActivity extends AppCompatActivity {
    private Button botonCancel = null;
    private Button botonRealizadas = null;
    private Button botonPendientes = null;
    private ArrayList<String> nombres = null;
    ArrayAdapter<String> adapter = null;
    private ListView listView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        botonCancel = (Button) findViewById(R.id.idBtnCancelarListado);
        botonCancel.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, BaseActivity.class);
            finish();
        });

        botonRealizadas = (Button) findViewById(R.id.idBtnTareasRealizadas);
        botonRealizadas.setOnClickListener(view -> {
           view.setEnabled(false);
           findViewById(R.id.idBtnTareasPendientes).setEnabled(true);
        });

        botonRealizadas = (Button) findViewById(R.id.idBtnTareasPendientes);
        botonRealizadas.setOnClickListener(view -> {
            view.setEnabled(false);
            findViewById(R.id.idBtnTareasRealizadas).setEnabled(true);
        });


        DataManager dbManager = new DataManager(ListActivity.this);
        nombres = dbManager.selectNombres();

        adapter = new ArrayAdapter<String>(ListActivity.this, R.layout.activity_adapter,
                R.id.idTextViewTareasDB, nombres);

        listView = (ListView) findViewById(R.id.idListViewTareas);
        listView.setAdapter(adapter);
    }
}
