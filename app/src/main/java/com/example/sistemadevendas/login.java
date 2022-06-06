package com.example.sistemadevendas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText ed1,ed2;
    Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ed1 = findViewById(R.id.user);
        ed2 = findViewById(R.id.pass);

        b1 = findViewById(R.id.btnLogar);
        b2 = findViewById(R.id.btnSairApp);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Login();
            }
        });
    }
    public  void Login(){
        String userName = ed1.getText().toString();
        String password = ed2.getText().toString();

        if(userName.equals("") || (password.equals(""))){
            Toast.makeText(this,"Os campos não podem  ser vazios", Toast.LENGTH_SHORT).show();

        }else if(userName.equals("borgo") && (password.equals("123"))){
            Intent i = new Intent(login.this,Main.class);
            startActivity(i);
            Toast.makeText(this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();
            ed1.setText("");
            ed2.setText("");
        }else{
            Toast.makeText(this, "Usuario ou senha invalidos", Toast.LENGTH_SHORT).show();
        }
    }
}
