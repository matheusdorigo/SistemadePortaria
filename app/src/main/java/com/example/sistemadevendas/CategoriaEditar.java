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

public class CategoriaEditar extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_editar2);

        ed1 = findViewById(R.id.catid);
        ed2 = findViewById(R.id.categoria);
        ed3 = findViewById(R.id.categoriaDesc);


        b1 = findViewById(R.id.btnEditar);
        b2 = findViewById(R.id.btnExcluir);
        b3 = findViewById(R.id.btnCancelar);
        Intent i = getIntent();

        String id = i.getStringExtra("id").toString();
        String categoria = i.getStringExtra("categoria").toString();
        String catdesc = i.getStringExtra("catdesc").toString();

        ed1.setText(id);
        ed2.setText(categoria);
        ed3.setText(catdesc);

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
            String catId = ed1.getText().toString();
            String categoriaNome = ed2.getText().toString();
            String catdescricao = ed3.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE,null);
            String sql = "update categoria set categoria = ?,catdesc =? where id=?";

            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,categoriaNome);
            statement.bindString(2, catdescricao);
            statement.bindString(3, catId);
            statement.execute();
            Toast.makeText(this,"Categoria editada com sucesso", Toast.LENGTH_SHORT).show();

            Intent i  = new Intent(getApplicationContext(), Main.class);
            startActivity(i);
        }catch (Exception ex){
            Toast.makeText(this,"Erro"+ex, Toast.LENGTH_SHORT).show();
        }
    }
    public void Deletar(){
        try {
            String catId = ed1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE,null);
            String sql = "delete  from  categoria where id=?";

            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, catId);
            statement.execute();
            Toast.makeText(this,"Categoria deletada com sucesso", Toast.LENGTH_SHORT).show();

            Intent i  = new Intent(getApplicationContext(), Main.class);
            startActivity(i);
        }catch (Exception ex){
            Toast.makeText(this,"Erro"+ex, Toast.LENGTH_SHORT).show();
        }
    }
    }

