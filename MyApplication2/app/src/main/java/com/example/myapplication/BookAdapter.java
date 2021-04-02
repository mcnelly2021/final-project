package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Book> booklist;
    public BookAdapter(Context context, int layout, ArrayList<Book> booksList) {
        this.context = context;
        this.layout = layout;
        this.booklist = booksList;
    }
    @Override
    public int getCount() {
        return booklist.size();
    }

    @Override
    public Object getItem(int position) {
        return booklist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Book book = booklist.get(position);

        holder.txtName.setText(book.geTitle());
        holder.txtPrice.setText(book.getPrice());

        byte[] bookImage = book.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bookImage, 0, bookImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
