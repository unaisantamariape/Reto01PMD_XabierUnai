package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button botonLogin = null;
    private EditText editTextUsuario = null;
    private EditText editTextPass = null;

    private String user = "admin";
    private String pass = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsuario = (EditText) findViewById(R.id.idEditTextUsuario);
        editTextPass = (EditText) findViewById(R.id.idEditTextPass);

        botonLogin = (Button) findViewById(R.id.idBtnLogin);
        botonLogin.setOnClickListener(view -> {
            String userIntroducido = editTextUsuario.getText().toString();
            String passIntroducido = editTextPass.getText().toString();

            if(userIntroducido.equals(user) && passIntroducido.equals(pass)){
                //Login correcto
            }else{
                //Login incorrecto
                Toast.makeText(this,R.string.texto_toastLoginIncorrecto,Toast.LENGTH_LONG).show();
            }

        });

    }
}