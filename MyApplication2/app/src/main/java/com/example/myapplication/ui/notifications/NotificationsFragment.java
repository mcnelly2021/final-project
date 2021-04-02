package com.example.myapplication.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.DBHelper;
import com.example.myapplication.Login;
import com.example.myapplication.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
TextView user,mai,phn;
DBHelper dbHelper;
String da,ds,de;
TextView btt;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        user= root.findViewById(R.id.user);
        mai= root.findViewById(R.id.mail);
        phn= root.findViewById(R.id.pon);
        btt=root.findViewById(R.id.logou);
        dbHelper=new DBHelper((getContext()));
        SharedPreferences sp = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String name = sp.getString("username", "");
        if(!name.equals("")){
            Cursor c=dbHelper.getprofile(name);
            c.moveToFirst();
            user.setText(c.getString(c.getColumnIndex("USERNAME")));
            mai.setText(c.getString(c.getColumnIndex("EMAIL")));
            //phn.setText(c.getString(c.getColumnIndex("PHONE")))
            phn.setText("+XXX-XXXXXXXX");
        }
        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.remove("username");
                ed.commit();
                Intent t= new Intent(getContext(), Login.class);
                startActivity(t);
            }
        });

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}
