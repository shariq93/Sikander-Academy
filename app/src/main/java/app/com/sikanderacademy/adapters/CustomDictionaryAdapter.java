package app.com.sikanderacademy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.com.sikanderacademy.ModelClass.Dictionary;
import app.com.sikanderacademy.ModelClass.Search;
import app.com.sikanderacademy.R;

/**
 * Created by Muhammad Saim on 1/13/2018.
 */

public class CustomDictionaryAdapter extends BaseAdapter{

    Context context;
    private static LayoutInflater inflater=null;
    List<Dictionary> beanArrayList;

    public CustomDictionaryAdapter(Context context, List<Dictionary> list) {
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
        TextView tv_count,tv_word,tv_meaning;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.item_list_search, null);
        holder.tv_count=(TextView) rowView.findViewById(R.id.tv_count);
        holder.tv_word=(TextView) rowView.findViewById(R.id.tv_word);
        holder.tv_meaning=(TextView) rowView.findViewById(R.id.tv_meaning);

        holder.tv_count.setText(""+beanArrayList.get(position).getId());
        holder.tv_word.setText(""+beanArrayList.get(position).getWord());
        holder.tv_meaning.setText(""+beanArrayList.get(position).getMeaning());

        return rowView;
    }

}