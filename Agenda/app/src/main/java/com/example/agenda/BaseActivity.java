package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import dataBase.DataManager;
import dataBase.Tarea;

public class BaseActivity extends AppCompatActivity{
    private ImageButton botonCrear = null;
    private ImageButton botonVerLista = null;
    private ImageButton botonVolver = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        botonCrear = (ImageButton) findViewById(R.id.idImageButtonNuevaTarea);
        botonCrear.setOnClickListener(view -> {
            Intent intent = new Intent(BaseActivity.this,RegisterActivity.class);
            startActivity(intent);
        });
        botonVerLista = (ImageButton) findViewById(R.id.idImageButtonVerTareas);
        botonVerLista.setOnClickListener(view -> {

            // Tarea tarea = new Tarea();
            // tarea.setNombre("Fregar cocina");
            // tarea.setRealizada(1);

            DataManager dbManager = new DataManager(BaseActivity.this);
            SQLiteDatabase db = dbManager.getWritableDatabase();
           // dbManager.insert(tarea);

            Intent intent = new Intent(BaseActivity.this,ListActivity.class);
            startActivity(intent);
                });
        botonVolver = (ImageButton) findViewById(R.id.idImageButtonVolver);
        botonVolver.setOnClickListener(view -> {
            Intent intent = new Intent(BaseActivity.this, MainActivity.class);
            finish();
        });

    }
}
