package com.i14yokoro.tecterminal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private  final String BR = System.getProperty("line.separator");
    private ArrayList<MyListItem> items;
    private ListView mListView;
    protected MyListItem myListItem;

    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<>();
        final LineAdapter lineAdapter = new LineAdapter(MainActivity.this ,items);

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(lineAdapter);

        if (items.size() == 0){
            myListItem = new MyListItem(0, "boot\n",  false);
            items.add(myListItem);
            myListItem = new MyListItem(1, "",  false);
            items.add(myListItem);
            lineAdapter.notifyDataSetChanged();
        }

        if (lineAdapter.isNextFlag()){
            myListItem = new MyListItem(items.size()-1, "",  false);
            items.add(myListItem);
            lineAdapter.notifyDataSetChanged();
        }
    }

        /**
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("debug***********","debug開始");

                LinearLayout linear = (LinearLayout)view;
                EditText inputText = (EditText)linear.findViewById(R.id.input_text);
                String strInputText = inputText.getText().toString();
                Log.d("debug***********",strInputText);
                if(strInputText.lastIndexOf(BR) != -1){
                    myListItem.setUpText(strInputText);
                    items.add(myListItem);
                    lineAdapter.notifyDataSetChanged(); }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    } **/

}
