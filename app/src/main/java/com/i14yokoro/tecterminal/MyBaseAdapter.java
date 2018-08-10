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

public class MyBaseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MyListItem> items;
    LayoutInflater layoutInflater = null;
    protected MyListItem myListItem;

    private class ViewHolder{
        TextView upText;
        EditText inputText;
    }

    public MyBaseAdapter(Context context,ArrayList<MyListItem> items){
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
            Log.d("items size",Integer.toString(items.size()));
            Log.d("position size",Integer.toString(position));


            if(items.size() == position){
             view = layoutInflater.inflate(R.layout.row_input,parent,false);
             holder.inputText = (EditText) view.findViewById(R.id.input_text);
             }
             else {
                view = layoutInflater.inflate(R.layout.row_list, parent, false);
                holder.upText = (TextView) view.findViewById(R.id.input_text);
            }
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        final MyListItem item = items.get(position);

        if(view == layoutInflater.inflate(R.layout.row_input,parent,false)) {
            if (holder.inputText.getTag() instanceof TextWatcher) {
                holder.inputText.removeTextChangedListener((TextWatcher) (holder.inputText.getTag()));
            }

            holder.inputText.setHint(position + ".");
            if(TextUtils.isEmpty(item.getUpText())){
                holder.inputText.setTextKeepState("");
            } else {
                holder.inputText.setTextKeepState(item.getUpText());
            }

            if(item.isFocus()){
                if(!holder.inputText.isFocused()){
                    holder.inputText.requestFocus();
                }
                CharSequence text = item.getUpText();

                holder.inputText.setSelection(TextUtils.isEmpty(text) ? 0 : text.length());
            } else {
                if(holder.inputText.isFocused()){
                    holder.inputText.clearFocus();
                }
            }

            holder.inputText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP){
                        final boolean focus = item.isFocus();
                        check(position);

                        if(!focus && !holder.inputText.isFocused()){
                            holder.inputText.requestFocus();
                            holder.inputText.onWindowFocusChanged(true);
                        }
                    }
                    return false;
                }
            });

            final TextWatcher watcher = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if(TextUtils.isEmpty(s)){
                        item.setUpText(null);
                    } else {
                        item.setUpText(String.valueOf(s));
                    }
                }
            };

            holder.inputText.addTextChangedListener(watcher);
            holder.inputText.setTag(watcher);
        }


        if(view != layoutInflater.inflate(R.layout.row_input,parent,false))
            holder.upText.setText(myListItem.getUpText());
        return view;
    }

    private void check(int position){
        for(MyListItem l : items){
            l.setFocus(false);
        }
        items.get(position).setFocus(true);
    }



}
