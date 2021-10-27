package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity{
    private ImageButton botonCrear = null;
    private ImageButton botonVerLista = null;
    private ImageButton botonVolver = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        botonCrear = (ImageButton) findViewById(R.id.idImageButtonNuevaTarea);
        botonVerLista = (ImageButton) findViewById(R.id.idImageButtonVerTareas);
        botonVolver = (ImageButton) findViewById(R.id.idImageButtonVolver);
        botonVolver.setOnClickListener(view -> {
            Intent intent = new Intent(BaseActivity.this, MainActivity.class);
            finish();
        });

    }
}
