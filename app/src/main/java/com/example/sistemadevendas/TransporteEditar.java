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

public class TransporteEditar extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporte_editar);

        ed1 = findViewById(R.id.fabId);
        ed2 = findViewById(R.id.txtEditaFabricante);
        ed3 = findViewById(R.id.fabricanteDesc);


        b1 = findViewById(R.id.btnEditarFabri);
        b2 = findViewById(R.id.btnExcluirFabri);
        b3 = findViewById(R.id.btnCancelarFabri);
        Intent i = getIntent();

        String id = i.getStringExtra("id").toString();
        String fabricante = i.getStringExtra("fabricante").toString();
        String fabdesc = i.getStringExtra("fabdesc").toString();

        ed1.setText(id);
        ed2.setText(fabricante);
        ed3.setText(fabdesc);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editar();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Deletar();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Main.class);
                startActivity(i);
            }
        });
    }

    public void Editar(){
        try {
            String fabId = ed1.getText().toString();
            String fabricanteNome = ed2.getText().toString();
            String fabricanteDescricao = ed3.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("", Context.MODE_PRIVATE,null);
            String sql = "update fabricante set fabricante = ?,fabdesc =? where id=?";

            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,fabricanteNome);
            statement.bindString(2, fabricanteDescricao);
            statement.bindString(3, fabId);
            statement.execute();
            Toast.makeText(this,"Transporte editado com sucesso", Toast.LENGTH_SHORT).show();

            Intent i  = new Intent(getApplicationContext(), Main.class);
            startActivity(i);
        }catch (Exception ex){
            Toast.makeText(this,"Erro"+ex, Toast.LENGTH_SHORT).show();
        }
    }
    public void Deletar(){
        try {
            String fabId = ed1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("", Context.MODE_PRIVATE,null);
            String sql = "delete  from  fabricante where id=?";

            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, fabId);
            statement.execute();
            Toast.makeText(this,"Transporte deletado com sucesso", Toast.LENGTH_SHORT).show();

            Intent i  = new Intent(getApplicationContext(), Main.class);
            startActivity(i);
        }catch (Exception ex){
            Toast.makeText(this,"Erro"+ex, Toast.LENGTH_SHORT).show();
        }
    }
    }

