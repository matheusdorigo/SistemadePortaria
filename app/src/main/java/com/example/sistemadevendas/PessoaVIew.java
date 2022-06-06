package com.example.sistemadevendas;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PessoaVIew extends AppCompatActivity {

    ListView listPessoa;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_view);


        listPessoa = findViewById(R.id.listPessoa);
        SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE,null);
        final Cursor p = db.rawQuery("select * from categoria", null);
        int id = p.getColumnIndex("id");
        int pessoa = p.getColumnIndex("pessoa");
        int pessoadesc = p.getColumnIndex("pessoadesc");

        titles.clear();

        arrayAdapter  = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, titles);
        listPessoa.setAdapter(arrayAdapter);
        final ArrayList<pess> Pessoa = new ArrayList<pess>();

        if(p.moveToNext()){
            do{
                pess pa = new pess();
                pa.id = p.getString(id);
                pa.pes = p.getString(pessoa);
                pa.desc = p.getString(pessoadesc);
                Pessoa.add(pa);

                titles.add(p.getString(id)+"\t"+p.getString(pessoa)+"\t" + p.getString(pessoadesc)+"\t" );

            }while (p.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            listPessoa.invalidateViews();
        }

        listPessoa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aaa = titles.get(position).toString();
                pess pa = Pessoa.get(position);
                Intent i = new Intent(getApplicationContext(), EmpresaEditar.class);
                i.putExtra("id",pa.id);
                i.putExtra("pessoa",pa.pes);
                i.putExtra("pessoadesc", pa.desc);
                startActivity(i);
            }
        });
    }
}
