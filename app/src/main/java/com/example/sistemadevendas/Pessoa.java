package com.example.sistemadevendas;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Pessoa extends AppCompatActivity {

    EditText ed1, ed2;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

        ed1 = findViewById(R.id.pessoaa);
        ed2 = findViewById(R.id.pessoaaDesc);

        b1 = findViewById(R.id.btnpessoa);
        b2 = findViewById(R.id.btnpessoasair);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pessoa.this,Main.class);
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
            String pessoa = ed1.getText().toString();
            String pessoadesc = ed2.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS categoria(id INTEGER PRIMARY KEY AUTOINCREMENT, categoria VARCHAR,catdesc VARCHAR)");

            String sql = "insert into categoria(categoria, catdesc) values (?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,pessoa);
            statement.bindString(2, pessoadesc);
            statement.execute();
            Toast.makeText(this,"Cadastro de pessoa feito com sucesso", Toast.LENGTH_SHORT).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        }catch (Exception ex){
            Toast.makeText(this,"Erro"+ex, Toast.LENGTH_SHORT).show();
        }
    }
}
