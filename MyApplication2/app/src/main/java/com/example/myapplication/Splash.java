package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String name = sp.getString("username", "");
        if (name.equals("")) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(Splash.this, Login.class);
                    startActivity(i);
                }
            }, 3000);
        }else {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(Splash.this, Bot.class);
                    startActivity(i);
                }
            }, 3000);
        }
    }
}
