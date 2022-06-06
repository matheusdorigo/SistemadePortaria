package com.example.sistemadevendas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Transporte extends AppCompatActivity {

    EditText ed1, ed2;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporte);

        ed1 = findViewById(R.id.txtFabricante);
        ed2 = findViewById(R.id.txtDescriFabri);

        b1 = findViewById(R.id.btnCadastrarFabricante);
        b2 = findViewById(R.id.btnSair);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transporte.this,Main.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();

            }
        });
    }
    public void insert(){
        try {
            String fabricante = ed1.getText().toString();
            String fabdesc = ed2.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS fabricante(id INTEGER PRIMARY KEY AUTOINCREMENT, fabricante VARCHAR,fabdesc VARCHAR)");

            String sql = "insert into fabricante(fabricante, fabdesc) values (?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,fabricante);
            statement.bindString(2, fabdesc);
            statement.execute();
            Toast.makeText(this,"Fabricante adiconado com sucesso", Toast.LENGTH_SHORT).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        }catch (Exception ex){
            Toast.makeText(this,"Erro"+ex, Toast.LENGTH_SHORT).show();
        }
    }
    }

