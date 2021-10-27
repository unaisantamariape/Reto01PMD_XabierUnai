package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dataBase.DataManager;

public class MainActivity extends AppCompatActivity {
    private Button botonLogin = null;

    private String user = "admin";
    private String pass = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonLogin = (Button) findViewById(R.id.idBtnLogin);
        botonLogin.setOnClickListener(view -> {
            String userIntroducido = ((EditText) findViewById(R.id.idEditTextUsuario)).getText().toString();
            String passIntroducido = ((EditText) findViewById(R.id.idEditTextPass)).getText().toString();

            if(userIntroducido.equals(user) && passIntroducido.equals(pass)){
                //Login correcto
                DataManager dbManager = new DataManager(MainActivity.this);
                SQLiteDatabase db = dbManager.getWritableDatabase();

                Intent intent = new Intent(MainActivity.this,BaseActivity.class);
                startActivity(intent);
            }else{
                //Login incorrecto
                Toast.makeText(this,R.string.texto_toastLoginIncorrecto,Toast.LENGTH_LONG).show();
            }

        });

    }
}