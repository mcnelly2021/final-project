package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Altern extends AppCompatActivity {
    GridView grd;
    DBHelper dbHelper;
    ArrayList<Book> list;
    BookAdapter adapter=null;
//    String [] names={"Discrete Maths"," Softawre Eng","OS Systems","AI Systems"};
//    int [] pics={R.drawable.book,R.drawable.book,R.drawable.book,R.drawable.book,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altern);
        grd=(GridView)findViewById(R.id.grid);
        list = new ArrayList<>();
        adapter = new BookAdapter(this, R.layout.book_layout, list);

        //MainAdapter man=new MainAdapter(Altern.this,names,pics);
        grd.setAdapter(adapter);
dbHelper=new DBHelper(this);
        Cursor cursor = dbHelper.getData("SELECT * FROM books");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String price = cursor.getString(6);
            byte[] image = cursor.getBlob(8);

            list.add(new Book(title,price,image,id));
        }
        adapter.notifyDataSetChanged();


Cursor c=dbHelper.getdata();
//if(c.getCount()<1){
//    Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
//}else{
//    Toast.makeText(this, "iko", Toast.LENGTH_SHORT).show();
//
//
//}
        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              // Toast.makeText(Altern.this, "you clicked"+list.get(position).getId(), Toast.LENGTH_SHORT).show();
                Intent t = new Intent(getApplicationContext(), Viewbook.class);
                int san=list.get(position).getId();
                t.putExtra("key", ""+san);
                startActivity(t);
            }
        });
    }
    public void addbook(View v){
        Intent t = new Intent(Altern.this, Addbook.class);
        startActivity(t);
    }
}
