package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Addbook extends AppCompatActivity {
DBHelper dbHelper;
    EditText tt,aa,ii,cc,cn,pri,ph;
    ImageView imv;

    private ByteArrayOutputStream obj;
    private byte[] imageb;
    Bitmap cap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);
       // permision
        if(ContextCompat.checkSelfPermission(Addbook.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Addbook.this,new String[] {
                    Manifest.permission.CAMERA
            },100);
        }
        dbHelper=new DBHelper(this);
        tt=(EditText)findViewById(R.id.title);
        aa=(EditText)findViewById(R.id.author);
        ii=(EditText)findViewById(R.id.isbn);
        cc=(EditText)findViewById(R.id.classs);
        cn=(EditText)findViewById(R.id.condition);
        pri=(EditText)findViewById(R.id.price);
        ph=(EditText)findViewById(R.id.phone);
        imv=(ImageView)findViewById(R.id.img);

    }
    public void camera(View v){
        Intent i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,100);

    }


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100){
           Bitmap cap1=(Bitmap)data.getExtras().get("data");
            imv.setImageBitmap(cap1);
        }
    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    public void addbook(View v){
        String tt1,aa1,ii1,cc1,cn1,pri1,ph1;
        tt1=tt.getText().toString().trim();
        aa1=aa.getText().toString().trim();
        ii1=ii.getText().toString().trim();
        cc1=cc.getText().toString().trim();
        cn1=cn.getText().toString().trim();
        pri1=pri.getText().toString().trim();
        ph1=ph.getText().toString().trim();
//        cap.compress(Bitmap.CompressFormat.JPEG,100,obj);
//        obj=new ByteArrayOutputStream();
//        imageb=obj.toByteArray();

        if(tt1.equals("") || aa1.equals("") || ii1.equals("")|| cc1.equals("")|| cn1.equals("")|| pri1.equals("")|| ph1.equals("")){
            Toast.makeText(this, "Empty Fields", Toast.LENGTH_SHORT).show();
        }else{
            boolean b=dbHelper.addbook(tt1,aa1,ii1,cc1,cn1,pri1,ph1,imageViewToByte(imv));
            if(b==true){
                Toast.makeText(this, "Book Added Successfully", Toast.LENGTH_SHORT).show();
                Intent t = new Intent(Addbook.this, Bot.class);
                startActivity(t);
            }else{
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
