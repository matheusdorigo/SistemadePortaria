package com.example.sistemadevendas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoriaVIew extends AppCompatActivity {

    ListView listCategoria;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias_view);


        listCategoria = findViewById(R.id.listCategoria);
        SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE,null);
        final Cursor c = db.rawQuery("select * from categoria", null);
        int id = c.getColumnIndex("id");
        int Categoria = c.getColumnIndex("categoria");
        int catdesc = c.getColumnIndex("catdesc");

        titles.clear();

        arrayAdapter  = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, titles);
        listCategoria.setAdapter(arrayAdapter);
        final ArrayList<cate> categoria = new ArrayList<cate>();

        if(c.moveToNext()){
            do{
                cate ca = new cate();
                ca.id = c.getString(id);
                ca.categoria = c.getString(Categoria);
                ca.des = c.getString(catdesc);
                categoria.add(ca);

                titles.add(c.getString(id)+"\t"+c.getString(Categoria)+"\t" + c.getString(catdesc)+"\t" );

            }while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            listCategoria.invalidateViews();
        }

        listCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aaa = titles.get(position).toString();
                cate ca = categoria.get(position);
                Intent i = new Intent(getApplicationContext(), CategoriaEditar.class);
                i.putExtra("id",ca.id);
                i.putExtra("categoria",ca.categoria);
                i.putExtra("catdesc", ca.des);
                startActivity(i);
            }
        });
    }
}
