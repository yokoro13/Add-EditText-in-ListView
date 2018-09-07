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
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Activity内の処理を記述
 * 主にViewの表示
 */
public class MainActivity extends AppCompatActivity implements InputListener{

    private ArrayList<MyListItem> items;
    private ListView mListView;
    protected MyListItem myListItem;

    private InputNotify inputNotify = null;

    private  LineAdapter  lineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<>();
        inputNotify = new InputNotify();
        lineAdapter = new LineAdapter(MainActivity.this ,items, inputNotify);

        inputNotify.setListener(this);

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(lineAdapter);

        myListItem = new MyListItem(0, "boot", false);
        items.add(myListItem);
        myListItem = new MyListItem(items.size()-1, "", true);
        items.add(myListItem);
        lineAdapter.notifyDataSetChanged();

    }

    @Override
    public void inputEnter() {
        Log.d("debug*********", "pushed Enter");

        lineAdapter.notifyDataSetChanged();
        int itemCount = mListView.getCount();
        mListView.setSelection(itemCount - 1);
        Log.d("debug******",Integer.toString(itemCount-1));
        //mListView.setSelection(items.size());
    }

    @Override
    public void inputClearCommand(){
        Log.d("debug*********", "input clear");
        items.clear();
        lineAdapter.notifyDataSetChanged();
    }

}
