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

public class Categoria extends AppCompatActivity {

    EditText ed1, ed2;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        ed1 = findViewById(R.id.categoria);
        ed2 = findViewById(R.id.categoriaDesc);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Categoria.this,Main.class);
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
            String categoria = ed1.getText().toString();
            String descricao = ed2.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS categoria(id INTEGER PRIMARY KEY AUTOINCREMENT, categoria VARCHAR,catdesc VARCHAR)");

            String sql = "insert into categoria(categoria, catdesc) values (?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,categoria);
            statement.bindString(2, descricao);
            statement.execute();
            Toast.makeText(this,"Categoria adiconada com sucesso", Toast.LENGTH_SHORT).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        }catch (Exception ex){
            Toast.makeText(this,"Erro"+ex, Toast.LENGTH_SHORT).show();
        }
    }
}
