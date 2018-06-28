package app.com.sikanderacademy.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.com.sikanderacademy.ModelClass.Book;
import app.com.sikanderacademy.ModelClass.Search;
import app.com.sikanderacademy.R;
import app.com.sikanderacademy.activities.WebViewActivity;

/**
 * Created by Muhammad Saim on 1/13/2018.
 */

public class CustomBookAdapter extends BaseAdapter{

    Context context;
    private static LayoutInflater inflater=null;
    List<Book> beanArrayList;

    public CustomBookAdapter(Context context, List<Book> list) {
        // TODO Auto-generated constructor stub
        this.context=context;
        beanArrayList=list;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return beanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv_title;
        ImageView icon_book;
        TextView btn_open_book;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.item_list_books, null);
        holder.tv_title= rowView.findViewById(R.id.tv_title);
        holder.icon_book= rowView.findViewById(R.id.icon_book);
        holder.btn_open_book= rowView.findViewById(R.id.btn_open_book);

        holder.tv_title.setText(""+beanArrayList.get(position).getTitle());
        Picasso.get().load(""+beanArrayList.get(position).getCoverImage()).into(holder.icon_book);
        Log.i("=======", "getView: "+beanArrayList.get(position).getCoverImage()+"\n"
        );

        holder.btn_open_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("pdf_url",beanArrayList.get(position).getUrl());
                intent.putExtra("title",beanArrayList.get(position).getTitle());

                context.startActivity(intent);
//                Toast.makeText(context, ""+beanArrayList.get(position).getUrl(),
//                        Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }

}