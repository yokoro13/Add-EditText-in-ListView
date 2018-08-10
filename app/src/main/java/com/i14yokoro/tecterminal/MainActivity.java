package com.i14yokoro.tecterminal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MyListItem> items;
    private ListView mListView;
    protected MyListItem myListItem;

    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<>();
        final MyBaseAdapter myBaseAdapter = new MyBaseAdapter(MainActivity.this ,items);

        mListView = (ListView) findViewById(R.id.listView);
        inputText = (EditText) findViewById(R.id.input_text);
        mListView.setAdapter(myBaseAdapter);

        if (items.size() == 0){
            myListItem = new MyListItem(0, "boot",  false);
            items.add(myListItem);
            myBaseAdapter.notifyDataSetChanged();
        }

        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    String strInputText = inputText.getText().toString();
                    myListItem.setUpText(strInputText);
                    items.add(myListItem);
                    myBaseAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });

    }

}
