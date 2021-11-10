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
    private List<User> usuarios = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonLogin = (Button) findViewById(R.id.idBtnLogin);
        botonLogin.setOnClickListener(view -> {
            String userIntroducido = ((EditText) findViewById(R.id.idEditTextUsuario)).getText().toString();
            String passIntroducido = ((EditText) findViewById(R.id.idEditTextPass)).getText().toString();

            DataManager dbManager = new DataManager(MainActivity.this);
            SQLiteDatabase db = dbManager.getWritableDatabase();
            usuarios = dbManager.selectAllUsers();

            boolean login = false;
            //for (int i=0; i<usuarios.size();i++){
                //if(usuarios.get(i).getNombre().equals(userIntroducido) && usuarios.get(i).getPass().equals(passIntroducido)){
                if(userIntroducido.equals("admin") && passIntroducido.equals("1234")){
                    login = true;
                }
           // }

            if(login){
                //Login correcto
                Intent intent = new Intent(MainActivity.this,BaseActivity.class);
                startActivity(intent);
            }else{
                //Login incorrecto
                Toast.makeText(this,R.string.texto_toastLoginIncorrecto,Toast.LENGTH_LONG).show();

            }

        });

    }
}