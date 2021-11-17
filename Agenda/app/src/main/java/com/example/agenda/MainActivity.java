package com.example.agenda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dataBase.DataManager;
import dataBase.User;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchRecordar = null;
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

        @SuppressLint("CutPasteId") EditText editTextUser = (EditText) findViewById(R.id.idEditTextUsuario);
        switchRecordar.setChecked(remenberCheck);
        editTextUser.setText(remenberUser);

        Button botonLogin = (Button) findViewById(R.id.idBtnLogin);
        botonLogin.setOnClickListener(view -> {
            @SuppressLint("CutPasteId") String userIntroducido = ((EditText) findViewById(R.id.idEditTextUsuario)).getText().toString();
            String passIntroducido = ((EditText) findViewById(R.id.idEditTextPass)).getText().toString();

            if(dbManager.selectUserForLogin(userIntroducido,passIntroducido)){
                //Login correcto
                if (switchRecordar.isChecked()){
                    editor.putString(PREF_USERNAME, ((EditText) findViewById(R.id.idEditTextUsuario)).getText().toString());
                    editor.putBoolean(PREF_SWITCH, ((Switch) findViewById(R.id.idSwitchRecordarUser)).isChecked());

                } else {
                    editor.putString(PREF_USERNAME, "");
                    editor.putBoolean(PREF_SWITCH, false);

                }
                editor.apply();
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