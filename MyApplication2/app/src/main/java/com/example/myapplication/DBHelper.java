package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "books1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("CREATE TABLE  USERS (ID INTEGER PRIMARY KEY , EMAIL TEXT,USERNAME TEXT, PASSWORD TEXT)");
db.execSQL("CREATE TABLE  books (ID INTEGER PRIMARY KEY , title TEXT,author TEXT, isbn TEXT,classs TEXT,condition TEXT, price TEXT, phone TEXT,image BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        db.execSQL("DROP TABLE IF EXISTS books");
        onCreate(db);
    }
    public boolean register(String email,String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("EMAIL",email);
        cv.put("USERNAME",username);
        cv.put("PASSWORD",password);
        long result=db.insert("USERS",null,cv);
        if(result==-1)
            return false;
         else
             return true;
    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    public Cursor getDataind(String arg){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery("SELECT * FROM books WHERE ID=?", new String[] {arg});
    }
    public Cursor getprofile(String arg){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery("SELECT * FROM USERS WHERE USERNAME= ?",new String[] {arg});
    }
    public boolean addbook(String title, String author, String isbn, String classs, String condition, String price, String phone, byte [] image){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("title",title);
        cv.put("author",author);
        cv.put("isbn",isbn);
        cv.put("classs",classs);
        cv.put("condition",condition);
        cv.put("price",price);
        cv.put("phone",phone);
        cv.put("image",image);
        long result=db.insert("books",null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean checkuser(String user, String pass){
SQLiteDatabase db =this.getWritableDatabase();
Cursor c= db.rawQuery("SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?",new String[] {user,pass});
if(c.getCount()<=0){
    return false;
}else{
    return true;
}
    }
    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM books WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }
    public Cursor getdata(){
        SQLiteDatabase db=this.getWritableDatabase();
       Cursor c= db.rawQuery("SELECT * FROM books",null);
       return c;
    }
}
