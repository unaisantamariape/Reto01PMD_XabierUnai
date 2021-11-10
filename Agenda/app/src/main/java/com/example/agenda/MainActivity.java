package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dataBase.DataManager;
import dataBase.User;

public class MainActivity extends AppCompatActivity {
    private Button botonLogin = null;
    static User usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataManager dbManager = new DataManager(MainActivity.this);

        botonLogin = (Button) findViewById(R.id.idBtnLogin);
        botonLogin.setOnClickListener(view -> {
            String userIntroducido = ((EditText) findViewById(R.id.idEditTextUsuario)).getText().toString();
            String passIntroducido = ((EditText) findViewById(R.id.idEditTextPass)).getText().toString();


            if(dbManager.selectUserForLogin(userIntroducido,passIntroducido)){
                //Login correcto
                usuario = dbManager.selectByName(userIntroducido);
                Intent intent = new Intent(MainActivity.this,BaseActivity.class);
                startActivity(intent);
            }else{
                //Login incorrecto
                Toast.makeText(this,R.string.texto_toastLoginIncorrecto,Toast.LENGTH_LONG).show();
            }

        });

    }
}