package com.example.agenda;

import android.content.DialogInterface;
import static com.example.agenda.R.*;
import android.annotation.SuppressLint;
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
    private ArrayList<Integer> ids = null;
    ArrayAdapter<String> adapter = null;
    private ListView listView = null;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_list);

        DataManager dbManager = new DataManager(ListActivity.this);

        botonCancel = (Button) findViewById(id.idBtnCancelarListado);
        botonCancel.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, BaseActivity.class);
            finish();
        });

        botonRealizadas = (Button) findViewById(id.idBtnTareasRealizadas);
        botonRealizadas.setOnClickListener(view -> {
            view.setVisibility(View.INVISIBLE);
            findViewById(id.idBtnTareasPendientes).setVisibility(View.VISIBLE);
            tareas = dbManager.selectNombresRealizados();
            ids = dbManager.selectIdsRealizadas();
            adapter = new ArrayAdapter<String>(ListActivity.this, layout.activity_adapter,
                    id.idTextViewTareasDB, tareas);
            listView.setAdapter(adapter);
        });

        botonPendientes = (Button) findViewById(id.idBtnTareasPendientes);
        botonPendientes.setVisibility(View.INVISIBLE);
        botonPendientes.setOnClickListener(view -> {
            view.setVisibility(View.INVISIBLE);
            findViewById(id.idBtnTareasRealizadas).setVisibility(View.VISIBLE);
            tareas = dbManager.selectNombresPendientes();
            ids = dbManager.selectIdsPendientes();
            adapter = new ArrayAdapter<String>(ListActivity.this, layout.activity_adapter,
                    id.idTextViewTareasDB, tareas);
            listView.setAdapter(adapter);
        });

        //De primeras enseña las que están pendientes
        tareas = dbManager.selectNombresPendientes();
        ids = dbManager.selectIdsPendientes();

        adapter = new ArrayAdapter<String>(ListActivity.this, layout.activity_adapter,
                id.idTextViewTareasDB, tareas);

        listView = (ListView) findViewById(id.idListViewTareas);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int posicion = i;
                String tareaClicada = ids.get(i).toString();

                Intent intent = new Intent(ListActivity.this,DetailActivity.class);
                intent.putExtra("ID",tareaClicada);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                builder.setTitle(R.string.texto_tituloAlertDialog);
                builder.setMessage(R.string.texto_mensajeAlertDialog);
                builder.setPositiveButton(R.string.texto_aceptarAlertDialog,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                int posicion = i;
                                String idClicado = ids.get(i).toString();
                                tareas.remove(i);

                                dbManager.deleteById(idClicado);

                                adapter.notifyDataSetChanged();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
        listView.setAdapter(adapter);
    }
}
