package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import dataBase.DataManager;
import dataBase.User;

public class BaseActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ImageButton botonCrear = findViewById(R.id.idImageButtonNuevaTarea);
        botonCrear.setOnClickListener(view -> {
            Intent intent = new Intent(BaseActivity.this,RegisterActivity.class);
            startActivity(intent);
        });
        ImageButton botonVerLista = findViewById(R.id.idImageButtonVerTareas);
        botonVerLista.setOnClickListener(view -> {
            Intent intent = new Intent(BaseActivity.this,ListActivity.class);
            startActivity(intent);
                });
        ImageButton botonVolver = findViewById(R.id.idImageButtonVolver);
        botonVolver.setOnClickListener(view -> finish());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       if(item.getItemId() == R.id.idCambiarPass) {
                AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
                alerDialog.setMessage(R.string.texto_escribeNuevaPass);
                final EditText editTextName = new EditText(this);
                alerDialog.setView(editTextName);

                alerDialog.setPositiveButton(android.R.string.ok, (dialog, whichButton) -> {
                    String passNueva = editTextName.getText().toString();
                    User usuarioEditado = MainActivity.usuario;
                    usuarioEditado.setPass(passNueva);

                    DataManager dbManager = new DataManager(BaseActivity.this);
                    dbManager.updatePassUser(usuarioEditado);
                    Toast.makeText(BaseActivity.this,R.string.texto_passCambiada,Toast.LENGTH_SHORT).show();
                    }
                );
                alerDialog.show();
        }
        return true;
    }



}
