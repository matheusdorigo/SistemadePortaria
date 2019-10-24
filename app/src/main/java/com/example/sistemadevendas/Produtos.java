package com.example.sistemadevendas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Produtos extends AppCompatActivity {

    EditText ed1, ed2;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        b2 = findViewById(R.id.btnSairProd);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Produtos.this,Main.class);
                startActivity(i);
            }
        });
    }
}
