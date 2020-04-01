package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListView extends AppCompatActivity {

    android.widget.ListView listView;
    TextView textView;
    private static final String TEXT_VIEW_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        textView = (TextView) findViewById(R.id.textView);


        listView = (android.widget.ListView) findViewById(R.id.listview);

        ArrayList<String> arrayList = new ArrayList<>();
        final ArrayList<String> infoList = new ArrayList<>();

        arrayList.add("VW");
        infoList.add("VW power");
        arrayList.add("BMW");
        infoList.add("BMW power");
        arrayList.add("Audi");
        infoList.add("Audi power");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(infoList.get(position));

            }
        });
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        textView.setText(savedInstanceState.getString(TEXT_VIEW_KEY));
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        TextView text=findViewById(R.id.textView);
        outState.putString(TEXT_VIEW_KEY, text.getText().toString());
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("a","start");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("a","restart");
    }

    @Override
    protected void onResume() {
        Log.d("a","Resume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("a","pause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("a","stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("a","destroy");
        super.onDestroy();
    }
}
