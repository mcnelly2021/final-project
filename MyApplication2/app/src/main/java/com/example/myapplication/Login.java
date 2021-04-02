package com.example.myapplication;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

public class Login extends AppCompatActivity {
EditText user,pass;
DBHelper dbHelper;
SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        dbHelper=new DBHelper(this);
        sp=getSharedPreferences("data", Context.MODE_PRIVATE);
    }
    public void login(View v){
        SharedPreferences.Editor editor=sp.edit();

        String usern,passn;
        usern=user.getText().toString().trim();
        passn=pass.getText().toString().trim();
        //Toast.makeText(this, ""+passn, Toast.LENGTH_SHORT).show();
        if(usern.equals("") || passn.equals("")){
            Toast.makeText(this, "Please input details", Toast.LENGTH_SHORT).show();
            user.setError("Empty");
            pass.setError("Empty");
            user.setText(null);
            pass.setText(null);
        }else{
            boolean b=dbHelper.checkuser(usern,passn);
            if(b==true){
                editor.putString("username",usern);
                editor.commit();
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                Intent t =new Intent(Login.this,Bot.class);
                startActivity(t);
            }else{
                user.setError("Wrong username");
                pass.setError("Wrong Password");
                user.setText(null);
                pass.setText(null);
                Toast.makeText(this, "Your Credetials are wrong", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void regist(View v){
        Intent t =new Intent(Login.this, Register.class);
        startActivity(t);
    }
}
