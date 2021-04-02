package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
EditText user,pas,pac,mail;
DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user=(EditText)findViewById(R.id.username);
        mail=(EditText)findViewById(R.id.email);
        pas=(EditText)findViewById(R.id.password);
        pac=(EditText)findViewById(R.id.passwordc);
        dbHelper=new DBHelper(this);

    }
    public void login(View v){
        Intent t = new Intent(Register.this, Login.class);
        startActivity(t);
    }
    public void regist(View v){
String pass,pasc,username,email;
email=mail.getText().toString().trim();
username=user.getText().toString().trim();
pass=pas.getText().toString().trim();
pasc=pac.getText().toString().trim();

if(pasc.equals("") || pass.equals("") || username.equals("")|| email.equals("")){
user.setError("Can't be Empty");
mail.setError("Can't be Empty");
pas.setError("Can't be Empty");
pac.setError("Can't be Empty");
    Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
}else{
    if(pass.equals(pasc)){
boolean b =dbHelper.register(email,username,pass);
if(b==true){
    Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
    Intent t = new Intent(Register.this, Login.class);
    startActivity(t);
}else{
    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
}
    }else{
        pas.setText("");
        pac.setText("");
        Toast.makeText(this, "Passwords dont match", Toast.LENGTH_SHORT).show();
    }

}

    }
}
