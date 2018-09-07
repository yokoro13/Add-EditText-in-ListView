package com.i14yokoro.tecterminal;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * ListView内の処理を記述
 * 主にListの中身
 */

public class LineAdapter extends BaseAdapter {

    private final String clearCommand = "zl";

    private Context context;
    private ArrayList<MyListItem> items;
    LayoutInflater layoutInflater ;
    protected MyListItem myListItem;
    private InputNotify inputNotify;

    private class ViewHolder{
        EditText outputText;
    }

    public LineAdapter(Context context, ArrayList<MyListItem> items, InputNotify inputNotify){
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
        this.inputNotify = inputNotify;
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
            holder = new ViewHolder();

            view = layoutInflater.inflate(R.layout.row_output,parent,false);
            holder.outputText = (EditText) view.findViewById(R.id.outputText);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        //入力したものにはフォーカスが合わないように
        if(!myListItem.isFocus())
            holder.outputText.setFocusable(false);
        else {
            holder.outputText.setFocusable(true);
            holder.outputText.setFocusableInTouchMode(true);
        }

        holder.outputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d("debug*********","editAction");
                if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    Log.d("debug*********","inputEnter");
                    String strInputText = holder.outputText.getText().toString();
                    myListItem = new MyListItem(items.size() - 1, strInputText, false);
                    items.set(items.size()-1, myListItem);

                    //コマンドとかはこんな感じで実装
                    if(strInputText.equals(clearCommand)){
                        inputNotify.notifyInputClear();
                    }

                    myListItem = new MyListItem(items.size() , "", true);
                    items.add(myListItem);
                    inputNotify.notifyInputEnter();
                    holder.outputText.requestFocus();
                }
                return false;
            }
        });

        holder.outputText.setText(myListItem.getOutputText());

        return view;
    }
}
