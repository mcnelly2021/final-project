package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Viewbook extends AppCompatActivity {
DBHelper dbHelper;
TextView tt,aa,ii,cc,cn,pri,ph;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewbook);
        tt=(TextView)findViewById(R.id.tit);
        aa=(TextView)findViewById(R.id.autho);
        ii=(TextView)findViewById(R.id.isb);
        cc=(TextView)findViewById(R.id.clas);
        cn=(TextView)findViewById(R.id.conditio);
        pri=(TextView)findViewById(R.id.pric);
        ph=(TextView)findViewById(R.id.phon);
        img=(ImageView)findViewById(R.id.imagev);
        dbHelper=new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        Intent inte=getIntent();
       try{
           if (extras != null) {
               String valu = extras.getString("key");
             //  Toast.makeText(this, ""+valu, Toast.LENGTH_SHORT).show();
               Cursor c=dbHelper.getDataind(valu);
               if(c.moveToFirst()){
                   String dat=c.getString(c.getColumnIndex("title"));
                  // Toast.makeText(this, ""+dat, Toast.LENGTH_SHORT).show();
                   tt.setText(dat);
               }else{
                   Toast.makeText(this, "data", Toast.LENGTH_SHORT).show();
               }
               aa.setText(c.getString(c.getColumnIndex("author")));
               ii.setText(c.getString(c.getColumnIndex("isbn")));
               cc.setText(c.getString(c.getColumnIndex("classs")));
               cn.setText(c.getString(c.getColumnIndex("condition")));
               pri.setText(c.getString(c.getColumnIndex("price")));
               ph.setText(c.getString(c.getColumnIndex("phone")));

             byte[] bookImage = c.getBlob(c.getColumnIndex("image"));
              Bitmap bitmap = BitmapFactory.decodeByteArray(bookImage, 0, bookImage.length);
              img.setImageBitmap(bitmap);
           }
       }catch (Exception e){
           Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
       }
    }

    public void buybook(View v){
Dialogg dialogg=new Dialogg();
dialogg.show(getSupportFragmentManager(),"Payment");
    }
}
