package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {
    private Button botonCancel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        botonCancel = (Button) findViewById(R.id.idBtnCancel);
        botonCancel.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, BaseActivity.class);
            finish();
        });
    }
}
