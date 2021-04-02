package com.example.myapplication.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.Book;
import com.example.myapplication.BookAdapter;
import com.example.myapplication.DBHelper;
import com.example.myapplication.R;
import com.example.myapplication.Viewbook;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    GridView grd;
    DBHelper dbHelper;
    ArrayList<Book> list;
    BookAdapter adapter=null;
    Button bt;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        grd=root.findViewById(R.id.grid);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                list = new ArrayList<>();
                adapter = new BookAdapter(getContext(), R.layout.book_layout, list);
                grd.setAdapter(adapter);
                dbHelper=new DBHelper(getContext());
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
                        Intent t = new Intent(getContext(), Viewbook.class);
                        int san=list.get(position).getId();
                        t.putExtra("key", ""+san);
                        startActivity(t);
                    }
                });
            }
        });
        return root;
    }
}
