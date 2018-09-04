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
        inputText = (EditText) findViewById(R.id.inputText);

        if (items.size() == 0){
            myListItem = new MyListItem(0, "boot");
            items.add(myListItem);
            lineAdapter.notifyDataSetChanged();
        }

        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d("debug*********","editAction");
                if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    Log.d("debug*********","pushed Enter");
                    String strInputText = inputText.getText().toString();
                    myListItem = new MyListItem(items.size()-1, strInputText);
                    items.add(myListItem);
                    lineAdapter.notifyDataSetChanged();
                    int itemCount = mListView.getCount();
                    mListView.setSelection(itemCount - 1);
                    inputText.setText(null);
                }
                return false;
            }
        });
    }
}
