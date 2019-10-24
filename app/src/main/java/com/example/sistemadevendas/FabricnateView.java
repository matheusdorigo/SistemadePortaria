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

public class FabricnateView extends AppCompatActivity {

    ListView listFabricante;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabricnate_view);


        listFabricante = findViewById(R.id.listFabricante);
        SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE,null);
        final Cursor c = db.rawQuery("select * from fabricante", null);
        int id = c.getColumnIndex("id");
        int fabricante = c.getColumnIndex("fabricante");
        int fabdesc = c.getColumnIndex("fabdesc");

        titles.clear();

        arrayAdapter  = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, titles);
        listFabricante.setAdapter(arrayAdapter);
        final ArrayList<FabricanteModelo> Fabricante = new ArrayList<FabricanteModelo>();

        if(c.moveToNext()){
            do{
                FabricanteModelo fa = new FabricanteModelo();
                fa.id = c.getString(id);
                fa.fabricante = c.getString(fabricante);
                fa.fabDesc = c.getString(fabdesc);
                Fabricante.add(fa);

                titles.add(c.getString(id)+"\t"+c.getString(fabricante)+"\t" + c.getString(fabdesc)+"\t" );

            }while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            listFabricante.invalidateViews();
        }

        listFabricante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aaa = titles.get(position).toString();
                FabricanteModelo fa = Fabricante.get(position);
                Intent i = new Intent(getApplicationContext(),FabricanteEditar.class);
                i.putExtra("id",fa.id);
                i.putExtra("fabricante",fa.fabricante);
                i.putExtra("fabdesc", fa.fabDesc);
                startActivity(i);
            }
        });
    }
}
