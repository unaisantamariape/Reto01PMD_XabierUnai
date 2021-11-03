package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private Button botonCancel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        botonCancel = (Button) findViewById(R.id.idBtnCancelarDetails);
        botonCancel.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, BaseActivity.class);
            finish();
        });
    }
}
