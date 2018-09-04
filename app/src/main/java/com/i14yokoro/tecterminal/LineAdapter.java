package com.i14yokoro.tecterminal;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LineAdapter extends BaseAdapter {

    private  final String BR = System.getProperty("line.separator");
    private Context context;
    private ArrayList<MyListItem> items;
    LayoutInflater layoutInflater = null;
    protected MyListItem myListItem;

    private class ViewHolder{
        TextView outputText;
    }

    public LineAdapter(Context context, ArrayList<MyListItem> items){
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public int getCount(){
        return items.size();
    }
    @Override
    public Object getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return items.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View view = convertView;
        final ViewHolder holder ;

        myListItem = items.get(position);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();

            view = layoutInflater.inflate(R.layout.row_output,parent,false);
            holder.outputText = (TextView) view.findViewById(R.id.outputText);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        holder.outputText.setText(myListItem.getOutputText());
        return view;
    }
}
