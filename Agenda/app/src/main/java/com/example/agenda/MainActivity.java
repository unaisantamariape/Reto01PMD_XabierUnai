package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dataBase.DataManager;
import dataBase.User;

public class MainActivity extends AppCompatActivity {
    private Button botonLogin = null;
    private Switch switchRecordar = null;
    private EditText editTextUser = null;
    static User usuario;
    public static final String PREFS_NAME ="REMEMBER ME";
    public static final String PREF_USERNAME= "nombre";
    public static final String PREF_SWITCH= "estado";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataManager dbManager = new DataManager(MainActivity.this);

        //get remenber
        SharedPreferences remenberLogin = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = remenberLogin.edit();

        //get user remenber
        String remenberUser = remenberLogin.getString(PREF_USERNAME, "");
        boolean remenberCheck = remenberLogin.getBoolean(PREF_SWITCH, false);

        switchRecordar = (Switch) findViewById(R.id.idSwitchRecordarUser);
        switchRecordar.setOnCheckedChangeListener((compoundButton, b) -> {
            if (switchRecordar.isChecked()){
                editor.putString(PREF_USERNAME, ((EditText) findViewById(R.id.idEditTextUsuario)).getText().toString());
                editor.putBoolean(PREF_SWITCH, ((Switch) findViewById(R.id.idSwitchRecordarUser)).isChecked());

                editor.commit();
            } else {
                editor.putString(PREF_USERNAME, "");
                editor.putBoolean(PREF_SWITCH, false);

                editor.commit();
            }

        });
        editTextUser = (EditText) findViewById(R.id.idEditTextUsuario);
        switchRecordar.setChecked(remenberCheck);
        editTextUser.setText(remenberUser);

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