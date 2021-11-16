package com.example.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import dataBase.DataManager;
import dataBase.Tarea;
import dataBase.User;

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

            DataManager dbManager = new DataManager(BaseActivity.this);
            SQLiteDatabase db = dbManager.getWritableDatabase();

            Intent intent = new Intent(BaseActivity.this,ListActivity.class);
            startActivity(intent);
                });
        botonVolver = (ImageButton) findViewById(R.id.idImageButtonVolver);
        botonVolver.setOnClickListener(view -> {
            Intent intent = new Intent(BaseActivity.this, MainActivity.class);
            finish();
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.idCambiarPass:
                AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
                alerDialog.setMessage("Escribe tu nueva contraseña");
                final EditText editTextName = new EditText(this);
                alerDialog.setView(editTextName);

                alerDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String passNueva = editTextName.getText().toString();

                                DataManager dbManager = new DataManager(BaseActivity.this);

                                User usuarioCambiado = MainActivity.usuario;
                                usuarioCambiado.setId(MainActivity.usuario.getId());
                                usuarioCambiado.setPass(passNueva);
                                dbManager.updatePassUser(usuarioCambiado);

                            }
                        }
                    );
                alerDialog.show();
                break;

        }
        return true;
    }




}
