package com.example.myapplication.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.Addbook;
import com.example.myapplication.DBHelper;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    DBHelper dbHelper;
    List list;
    ListView lst;
    ArrayList<String> arr;
    ArrayAdapter adapter;
    Button btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        dbHelper=new DBHelper(getContext());
        arr=new ArrayList<>();

        lst=root.findViewById(R.id.listy);
        btn=root.findViewById(R.id.addme);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent y=new Intent(getContext(), Addbook.class);
                startActivity(y);
            }
        });
        viewDat();
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int sa=position;
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("DELETE?");
                alertDialog.setMessage("Are you sure you want to delete this?");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Confirm",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    dbHelper.deleteData(sa);
                                    Toast.makeText(getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                }catch (Exception e){
                                    Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();




            }
        });
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
    public void viewDat(){
        Cursor c=dbHelper.getdata();
        c.moveToFirst();
        if(c.getColumnCount()<1){
            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while(c.moveToNext()){
                arr.add(c.getString(c.getColumnIndex("title")));
            }
            adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arr);
            lst.setAdapter(adapter);
        }
    }
}
