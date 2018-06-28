package app.com.sikanderacademy.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.com.sikanderacademy.ModelClass.Search;
import app.com.sikanderacademy.R;

/**
 * Created by Muhammad Saim on 1/13/2018.
 */

public class CustomSearchAdapter extends BaseAdapter{

    Context context;
    private static LayoutInflater inflater=null;
    ArrayList<Search> beanArrayList;

    public CustomSearchAdapter(Context context, ArrayList<Search> list) {
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
        TextView tv_count,tv_title;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.item_list_search, null);
        holder.tv_count=(TextView) rowView.findViewById(R.id.tv_count);
        holder.tv_title=(TextView) rowView.findViewById(R.id.tv_title);

        holder.tv_count.setText(""+beanArrayList.get(position).getTypeName());
        holder.tv_title.setText(""+beanArrayList.get(position).getTitle());

//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//
//            }
//        });
        return rowView;
    }

}